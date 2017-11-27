(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('dateField', dateField);

  function dateField() {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/date/date-field.html",
      scope: {
        fieldScope: "=?",
        fieldInformation: "=",
        form: "=?",
        readonly: "=?",
        additionalParameters: "=?"
      },
      link: function (scope) {
        scope.fieldName = scope.$id;
        scope.fieldValue = new Date();

        function applyValidations() {
          if(scope.additionalParameters.isEditMode && scope.fieldInformation.excludeValidationsOnEdit){
            var validations = scope.fieldInformation.validations.split(",");
            var excludedValidations = scope.fieldInformation.excludeValidationsOnEdit.split(",");

            _.each(excludedValidations, function (excludeValidation) {
              var index = _.indexOf(validations, excludeValidation);
              if(index !== -1){
                validations = validations.splice(1, index);
              }
            });
            scope.fieldInformation.validations = validations.join();
          }

          if (scope.fieldInformation.validations) {
            scope.required = scope.fieldInformation.validations.includes("required");
            var noPastDate = scope.fieldInformation.validations.includes("noPastDate");
            if (noPastDate) {
              var currentDate = new Date();
              scope.dateFilter = function (date) {
                return date >= currentDate;
              }
            }
          }
        }

        function validateFieldModelValue() {
          var valueList = scope.fieldInformation.fieldValueList;
          if(!valueList){
            return;
          }
          var viewModel = scope.fieldInformation.fieldValueList[0].text.dateVal;
          if(!_.isDate(viewModel)){
            viewModel = new Date(viewModel);
          }
          scope.fieldValue = viewModel;
        }

        applyValidations();
        validateFieldModelValue();
      }
    };
  }
})();
