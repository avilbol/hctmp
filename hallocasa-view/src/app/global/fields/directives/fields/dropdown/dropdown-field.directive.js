(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('dropdownField', dropdownField);

  function dropdownField(FieldsService, translateFilter, toastr, idSearchFilter) {
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
        var validationsParams = _.isObject(scope.fieldInformation.validationsParams) ? scope.fieldInformation.validationsParams : {};

        function applyValidations() {
          if (scope.fieldInformation.validations) {
            interpreteValidations();
          }
        }

        function interpreteValidations() {
          scope.required = scope.fieldInformation.validations.includes("required");
          interpreteDateMinorThan();
        }

        function interpreteDateMinorThan() {
          var dateMinorThan = scope.fieldInformation.validations.includes("dateMinorThan");
          var fieldID = validationsParams.greaterDateFieldID;

          if (!dateMinorThan || !_.isNumber(fieldID)){return;}

          var fieldPath = FieldsService.getFieldPathByID(fieldID, scope.fieldRootScope);
          var updater = function () {
            var field = FieldsService.getFieldByPath(fieldPath, scope.fieldRootScope);
            var minorID = scope.fieldInformation.fieldValueList[0].identifier;
            var greaterID = field.fieldValueList ? field.fieldValueList[0].identifier : undefined;
            var optionsMinor = scope.options;
            var optionsGreater = field.options.list;

            updateDateValidationParams(minorID, greaterID, optionsMinor, optionsGreater);
          };

          var destroyWatcherModel = scope.$watch("fieldInformation.fieldValueList[0].identifier", updater);
          var destroyWatcherField = scope.$watch(function () {
            var field = FieldsService.getFieldByPath(fieldPath, scope.fieldRootScope);
            return field.fieldValueList ? field.fieldValueList[0].identifier : undefined;
          }, updater);

          scope.$on("$destroy",function () {
            destroyWatcherModel();
            destroyWatcherField();
          });

          scope.messagesParams = scope.fieldInformation.messagesParams;
        }

        function updateDateValidationParams(minorID, greaterID, optionsMinor, optionsGreater) {
          if(!_.isNumber(minorID) || !_.isNumber(greaterID)) {return;}

          var modelName = scope.fieldInformation.options.type === "static_options" ? "data1" : "name";

          var minorDate = idSearchFilter(optionsMinor, minorID, modelName, 'identifier');
          var greaterDate = idSearchFilter(optionsGreater, greaterID, modelName, 'identifier');

          scope.validationsParams = {dateValidation:{
            minorDate: minorDate,
            greaterDate: greaterDate,
            dateFormat: validationsParams.dateFormat
          }};
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
              scope.fieldInformation.options.list = scope.options;
              scope.fieldInformation.fieldOptions = scope.options;
              disableDefaultKeyDownHandler();
            })
            .catch(function () {
              toastr.warning(
                translateFilter("Error.whenloadingserviceoptions"), serviceId);
              scope.options = [];
              scope.fieldInformation.options.list = scope.options;
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
          scope.fieldInformation.options.list = scope.options;
        }

        function dynamicOptionsHandler(){
          var serviceId = optionsData.serviceId;
          FieldsService.loadOptionsByServiceId(serviceId)
            .then(function (options) {
              scope.options = FieldsService.processOptions(options, "NONE");
              scope.fieldInformation.options.list = scope.options;
            })
            .catch(function () {
              toastr.warning(
                translateFilter("Error.whenloadingserviceoptions"), serviceId);
              scope.options = [];
              scope.fieldInformation.options.list = scope.options;
            });
        }

        function binaryOptionsHandler(){
          scope.options = [
            {name: translateFilter("global.yes"), value: true},
            {name: translateFilter("global.no"), value: false}
          ];
          scope.fieldInformation.options.list = scope.options;
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
            scope.fieldInformation.options.list = scope.options;
          }
          var destroyInDependencyWatcher = scope.$watch(function () {
            field = FieldsService.getFieldByPath(fieldPath, scope.fieldRootScope);
            var unlistedOptions = scope.options && scope.options.length > 0 && !scope.options[0].name;
            var fieldWithName = field.fieldValueList && field.fieldValueList.length > 0 && field.fieldValueList[0].name;

            if((!scope.options || unlistedOptions) && fieldWithName){
              scope.options = field.fieldValueList;
              scope.fieldInformation.options.list = scope.options;
            }

            if(field.fieldValueList){
              return field.fieldValueList.length;
            }
          },function (newValue, oldValue) {
            if(newValue !== oldValue){
              scope.options = field.fieldValueList;
              scope.fieldInformation.options.list = scope.options;
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

        loadOptions();
        applyValidations();
      }
    };
  }
})();
