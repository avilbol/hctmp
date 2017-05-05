(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('dropdownField', dropdownField);

  function dropdownField(FieldsService, translateFilter, toastr) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/dropdown/dropdown-field.html",
      scope: {
        fieldScope: "=?",
        fieldInformation: "=",
        additionalParameters: "=?",
        fieldRootScope: "=?",
        form: "=?",
        readonly: "=?"
      },
      link: function (scope) {
        scope.fieldName = scope.$id;

        var optionsData = scope.fieldInformation.options;
        var staticOptionsGroup = scope.fieldInformation.dropdownOptionGroup;

        function applyValidations() {
          if (scope.fieldInformation.validations) {
            scope.required = scope.fieldInformation.validations.includes("required");
          }
        }

        function loadDependentOptions(serviceId, parameterName, parameterValue) {
          var serviceParameters = {};
          serviceParameters[parameterName] = parameterValue;
          FieldsService.loadOptionsByServiceId(serviceId, serviceParameters)
            .then(function (options) {
              scope.options = FieldsService.processOptions(options, "NONE");
            })
            .catch(function () {
              toastr.warning(
                translateFilter("Error.whenloadingserviceoptions"), serviceId);
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
                  toastr.warning(
                    translateFilter("Error.whenloadingserviceoptions"), serviceId);
                  scope.options = [];
                });
              break;
            case "binary_options":
              scope.options = [
                {name: translateFilter("global.yes"), value: true},
                {name: translateFilter("global.no"), value: false}
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
                  if(scope.options > 0 && !scope.options[0].name && field.fieldValueList[0].name){
                    scope.options = field.fieldValueList;
                  }

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
                  var parameterValue = field.fieldValueList[0].identifier;
                  loadDependentOptions(serviceId, parameterName, parameterValue);
                }

                var destroyExDependencyWatcher = scope.$watch(function () {
                  var field = FieldsService.getFieldByPath(fieldPath, scope.fieldRootScope);
                  if(field.fieldValueList){
                    return field.fieldValueList[0].identifier;
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
        applyValidations();
      }
    };
  }
})();
