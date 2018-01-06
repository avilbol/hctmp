(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('dateField', dateField);

  function dateField(FieldsService) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/date/date-field.html",
      scope: {
        fieldScope: "=?",
        fieldInformation: "=",
        fieldRootScope: "=",
        form: "=?",
        readonly: "=?",
        additionalParameters: "=?"
      },
      link: function (scope) {
        var validationsParams = _.isObject(scope.fieldInformation.validationsParams) ? scope.fieldInformation.validationsParams : {};

        scope.fieldName = scope.$id;
        scope.fieldValue = new Date();

        function applyValidations() {
          if(scope.additionalParameters.isEditMode && scope.fieldInformation.excludeValidationsOnEdit){
            var validations = scope.fieldInformation.validations.split(",");
            var excludedValidations = scope.fieldInformation.excludeValidationsOnEdit.split(",");

            _.each(excludedValidations, function (excludeValidation) {
              var index = _.indexOf(validations, excludeValidation);
              if(index !== -1){
                validations.splice(index, 1);
              }
            });

            scope.fieldInformation.validations = validations.join();
          }

          if (scope.fieldInformation.validations) {
            interpreteValidations();
          }
        }

        function interpreteValidations() {
          scope.required = scope.fieldInformation.validations.includes("required");
          interpreteNoPastDate();
          interpreteDateMinorThan();
        }

        function interpreteNoPastDate() {
          var noPastDate = scope.fieldInformation.validations.includes("noPastDate");
          if (!noPastDate) {return;}

          var yesterdayDate = new Date((new Date()).valueOf() - 1000*60*60*24);

          scope.dateFilter = function (date) {
            return date > yesterdayDate;
          }
        }

        function interpreteDateMinorThan() {
          var dateMinorThan = scope.fieldInformation.validations.includes("dateMinorThan");
          var fieldID = validationsParams.greaterDateFieldID;

          if (!dateMinorThan || !_.isNumber(fieldID)){return;}

          var fieldPath = FieldsService.getFieldPathByID(fieldID, scope.fieldRootScope);
          var field = FieldsService.getFieldByPath(fieldPath, scope.fieldRootScope);

          scope.validationsParams = {dateValidation:{
            minorDate: scope.fieldValue,
            greaterDate: field.fieldValueList[0].text.dateVal
          }};
          scope.messagesParams = scope.fieldInformation.messagesParams;
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
