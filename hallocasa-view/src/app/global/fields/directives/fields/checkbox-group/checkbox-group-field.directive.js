(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('checkboxGroupField', checkboxGroupField);

  function checkboxGroupField(FieldsService, translateFilter, toastr, $log) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/checkbox-group/checkbox-group-field.html",
      scope: {
        fieldScope: "=?",
        fieldInformation: "=",
        form: "=?",
        readonly: "=?"
      },
      link: function (scope) {
        scope.fieldName = scope.$id;
        var optionsData = scope.fieldInformation.options;
        var staticOptionsGroup = scope.fieldInformation.dropdownOptionGroup;

        function applyValidations(){
          if(scope.fieldInformation.validations){
            scope.requireOnce = scope.fieldInformation.validations.includes("requireOnce");
            scope.requireAll = scope.fieldInformation.validations.includes("requireAll");
          }
        }

        function loadOptions() {
          switch(optionsData.type){
            case "static_options":
              scope.options = FieldsService.processOptions(staticOptionsGroup.dropdownOptionList, staticOptionsGroup.translationManagement);
              scope.optionsLabel = "data1";
              validateFieldModelValue();
              break;
            case "dynamic_options":
              var serviceId = optionsData.serviceId;
              FieldsService.loadOptionsByServiceId(serviceId)
                .then(function (options) {
                  scope.options = FieldsService.processOptions(options, "NONE");
                  scope.optionsLabel = "name";
                  validateFieldModelValue();
                })
                .catch(function () {
                  toastr.warning(
                    translateFilter("Error.whenloadingserviceoptions"), serviceId);
                  scope.options = [];
                });

              break;
          }
        }

        function validateFieldModelValue() {
          var fieldValueList = scope.fieldInformation.fieldValueList;
          if(!fieldValueList){
            return;
          }
          scope.fieldInformation.fieldValueList = _.map(fieldValueList, function (fieldValue) {
            var fieldOption = _.find(scope.options, function (option) {
              return option.identifier === fieldValue.identifier;
            });
            if(fieldOption){
              return fieldOption;
            }
            else {
              $log.warn("No se ha podido encontrar los datos de la opción:", fieldValue);
              return fieldValue;
            }

          });
        }

        loadOptions();
        applyValidations();
      }
    };
  }
})();
