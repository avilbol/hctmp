(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('currencyField', currencyField);

  function currencyField(FieldsService, translateFilter, toastr) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/currency/currency-field.html",
      scope: {
        fieldInformation: "=",
        additionalParameters: "=?",
        form: "=?",
        readonly: "=?"
      },
      link: function (scope) {
        scope.fieldName = scope.$id;

        function applyValidations() {
          if (scope.fieldInformation.validations) {
            scope.required = scope.fieldInformation.validations.includes("required");
          }
        }

        function abbreviation(currencyID, options){
          var currencyItem = _.find(options, function(option){
            return option.id === currencyID;
          });
          return currencyItem && currencyItem.abbreviation;
        }

        function initCurrencyIDInFieldValueList(currencyID){
          if(!currencyID || scope.fieldInformation.fieldValueList){
            return;
          }
          scope.fieldInformation.fieldValueList = [
            {
              "data1": {
                "intVal":currencyID
              }
            }
          ];
        }

        function loadCurrencyID(fieldValueList){
          var crcyIdExists = fieldValueList && _.isArray(fieldValueList) && !_.isEmpty(fieldValueList);
          var crcyIdInWizard = scope.fieldInformation.fixCurrency && scope.additionalParameters.currencyToUse;
          return crcyIdExists ? fieldValueList[0].data1.intVal : (crcyIdInWizard ? scope.additionalParameters.currencyToUse : null);
        }

        function loadCurrencyData() {
          if(scope.readonly){
            scope.$watch("fieldInformation.fieldValueList[0].data2.doubleVal", function (amount) {
              if(amount){
                scope.price = {
                  amount: amount,
                  currencyID: scope.fieldInformation.fieldValueList[0].data1.intVal
                };
              }
            });
            return;
          }
          FieldsService.loadOptionsByServiceId("Currency")
            .then(function (options) {
              scope.options = options;
              var currencyID = loadCurrencyID(scope.fieldInformation.fieldValueList);
              initCurrencyIDInFieldValueList(currencyID);
              scope.abbreviation = abbreviation(currencyID, options);
            })
            .catch(function () {
              //TODO: Review message building
              toastr.warning(
                translateFilter("Error.whenloadingserviceoptions") + " Currency");
              scope.options = [];
            });
        }

        applyValidations();
        loadCurrencyData();
      }
    };
  }
})();
