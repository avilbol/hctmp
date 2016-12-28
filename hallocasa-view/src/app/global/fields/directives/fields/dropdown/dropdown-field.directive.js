(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('dropdownField', dropdownField);

  function dropdownField(FieldsService, toastr) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/dropdown/dropdown-field.html",
      scope: {
        fieldScope: "=?",
        fieldInformation: "=",
        additionalParameters: "=?",
        fieldRootScope: "=?"
      },
      link: function (scope) {
        var optionsData = scope.fieldInformation.options;
        var staticOptionsGroup = scope.fieldInformation.dropdownOptionGroup;

        function loadDependentOptions(serviceId, parameterName, parameterValue) {
          var serviceParameters = {};
          serviceParameters[parameterName] = parameterValue;
          FieldsService.loadOptionsByServiceId(serviceId, serviceParameters)
            .then(function (options) {
              scope.options = FieldsService.processOptions(options, "NONE");
            })
            .catch(function () {
              //TODO: Traducción de mensaje de error
              toastr.warning("Error al cargar opciones del servicio:", serviceId);
              scope.options = [];
            });
        }

        function loadOptions() {
          var serviceId, fieldId, field, parameterName, fieldPath;
          switch(optionsData.type){
            case "static_options":
              scope.options = FieldsService.processOptions(staticOptionsGroup.dropdownOptionList, staticOptionsGroup.translationManagement);
              break;
            case "dynamic_options":
              serviceId = optionsData.serviceId;
              FieldsService.loadOptionsByServiceId(serviceId)
                .then(function (options) {
                  scope.options = FieldsService.processOptions(options, "NONE");
                })
                .catch(function () {
                  //TODO: Traducción de mensaje de error
                  toastr.warning("Error al cargar opciones del servicio:", serviceId);
                  scope.options = [];
                });
              break;
            case "binary_options":
              scope.options = [
                {name: "Yes", value: true},
                {name: "No", value: false}
              ];
              break;
            case "internal_dependency_options":
              fieldId = optionsData.fieldId;
              fieldPath = FieldsService.getFieldPathByID(fieldId, scope.fieldRootScope);
              if(fieldPath) {
                field = FieldsService.getFieldByPath(fieldPath, scope.fieldRootScope);
                if (field.fieldValueList) {
                  scope.options = field.fieldValueList;
                }
                var destroyInDependencyWatcher = scope.$watch(function () {
                  field = FieldsService.getFieldByPath(fieldPath, scope.fieldRootScope);
                  if(field.fieldValueList){
                    return field.fieldValueList.length;
                  }
                },function (newValue, oldValue) {
                  if(newValue !== oldValue){
                    scope.options = field.fieldValueList;
                  }
                });
                scope.$on("$destroy",destroyInDependencyWatcher);
              }

              break;
            case "external_dependency_options":
              serviceId = optionsData.serviceId;
              fieldId = optionsData.fieldId;
              parameterName = optionsData.parameterPayload;
              fieldPath = FieldsService.getFieldPathByID(fieldId, scope.fieldRootScope);
              if(fieldPath){
                field = FieldsService.getFieldByPath(fieldPath, scope.fieldRootScope);
                if(field.fieldValueList){
                  var parameterValue = field.fieldValueList[0].text.intVal;
                  loadDependentOptions(serviceId, parameterName, parameterValue);
                }

                var destroyExDependencyWatcher = scope.$watch(function () {
                  var field = FieldsService.getFieldByPath(fieldPath, scope.fieldRootScope);
                  if(field.fieldValueList){
                    return field.fieldValueList[0].text.intVal;
                  }
                },function (newValue, oldValue) {
                  if(newValue !== oldValue){
                    loadDependentOptions(serviceId, parameterName, newValue);
                  }
                });

                scope.$on("$destroy",destroyExDependencyWatcher);
              }
              break;
            case "parameter_dependency_options":
              serviceId = optionsData.serviceId;
              parameterName = optionsData.parameterPayload;
              parameterValue = scope.additionalParameters[optionsData.parameterName];
              loadDependentOptions(serviceId, parameterName, parameterValue);
              break;

          }
        }

        loadOptions();
      }
    };
  }
})();
