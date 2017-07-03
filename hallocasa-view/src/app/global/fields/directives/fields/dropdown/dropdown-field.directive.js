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
      link: function (scope, element) {
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
              var translationManagement;
              if(scope.readonly){
                translationManagement = "NONE";
              }
              else{
                translationManagement = _.find(options, function(option){
                  return option.dependsOnLang}) ? "PARTIAL" : "NONE";
              }

              scope.options = FieldsService.processOptions(options, translationManagement);
              disableDefaultKeyDownHandler();
            })
            .catch(function () {
              toastr.warning(
                translateFilter("Error.whenloadingserviceoptions"), serviceId);
              scope.options = [];
            });
        }

        function loadOptions() {
          switch(optionsData.type){
            case "static_options":
              staticOptionsHandler();
              break;
            case "dynamic_options":
              dynamicOptionsHandler();
              break;
            case "binary_options":
              binaryOptionsHandler();
              break;
            case "internal_dependency_options":
              internalDependencyOptionsHandler();
              break;
            case "external_dependency_options":
              externalDependencyOptionsHandler();
              break;
            case "parameter_dependency_options":
              parameterDependencyOptionsHandler();
              break;

          }
        }

        function staticOptionsHandler(){
          var optionsList = staticOptionsGroup.dropdownOptionList;
          var translationManagement =  scope.readonly ? "NONE" : staticOptionsGroup.translationManagement;
          scope.options = FieldsService.processOptions(optionsList, translationManagement);
        }

        function dynamicOptionsHandler(){
          var serviceId = optionsData.serviceId;
          FieldsService.loadOptionsByServiceId(serviceId)
            .then(function (options) {
              scope.options = FieldsService.processOptions(options, "NONE");
            })
            .catch(function () {
              toastr.warning(
                translateFilter("Error.whenloadingserviceoptions"), serviceId);
              scope.options = [];
            });
        }

        function binaryOptionsHandler(){
          scope.options = [
            {name: translateFilter("global.yes"), value: true},
            {name: translateFilter("global.no"), value: false}
          ];
        }

        function internalDependencyOptionsHandler(){
          var fieldId = optionsData.fieldId;
          var fieldPath = FieldsService.getFieldPathByID(fieldId, scope.fieldRootScope);
          var field;

          if(!fieldPath) {
            return;
          }
          field = FieldsService.getFieldByPath(fieldPath, scope.fieldRootScope);
          if (field.fieldValueList) {
            scope.options = field.fieldValueList;
          }
          var destroyInDependencyWatcher = scope.$watch(function () {
            field = FieldsService.getFieldByPath(fieldPath, scope.fieldRootScope);
            var unlistedOptions = scope.options && scope.options.length > 0 && !scope.options[0].name;
            var fieldWithName = field.fieldValueList && field.fieldValueList.length > 0 && field.fieldValueList[0].name;

            if((!scope.options || unlistedOptions) && fieldWithName){
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

        function externalDependencyOptionsHandler(){
          var serviceId = optionsData.serviceId;
          var fieldId = optionsData.fieldId;
          var parameterName = optionsData.parameterPayload;
          var field;
          var fieldPath = FieldsService.getFieldPathByID(fieldId, scope.fieldRootScope);

          if(!fieldPath){
            return;
          }
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

        function parameterDependencyOptionsHandler(){
          var serviceId = optionsData.serviceId;
          var parameterName = optionsData.parameterPayload;
          var parameterValue = scope.additionalParameters[optionsData.parameterName];
          loadDependentOptions(serviceId, parameterName, parameterValue);
        }

        function disableDefaultKeyDownHandler() {
          element.find('input').on('keydown', function(ev) {
            ev.stopPropagation();
          });
        }
		
		function addWatchers(){
		  scope.$watch('fieldInformation.fieldValueList[0].identifier', function(identifier) {
            scope.fieldInformation.selectedOption = _.find(scope.options, function(option){
              return option.identifier === identifier;
            });
          });
        }

        loadOptions();
        applyValidations();
		addWatchers();
      }
    };
  }
})();
