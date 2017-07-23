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

        function abbreviation(options){
          var currencyItem = _.find(options, function(option){
            return option.id === scope.currencyID;
          });
          return currencyItem && currencyItem.abbreviation;
        }

        function addWatchers(){
          scope.$watch("fieldInformation.fieldValueList", function(fieldValueList){
            if(!(fieldValueList && fieldValueList[0])){
              return;
            }
            if(scope.currencyID){
              scope.fieldInformation.fieldValueList[0].data1 = {intVal : scope.currencyID};
            }
            if(!fieldValueList[0].data2.doubleVal){
              delete scope.fieldInformation.fieldValueList;
            }
          }, true);
        }

        function loadCurrencyID(fieldValueList){
          var crcyIdExists = fieldValueList && _.isArray(fieldValueList) && !_.isEmpty(fieldValueList);
          var crcyIdInWizard = scope.fieldInformation.fixCurrency && scope.additionalParameters.currencyToUse;
          return crcyIdExists ? fieldValueList[0].data1.intVal : (crcyIdInWizard ? scope.additionalParameters.currencyToUse : null);
        }

        function watchDestroyField() {
          scope.$on("$destroy", function () {
            delete scope.fieldInformation.fieldValueList;
          });
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
              scope.currencyID = loadCurrencyID(scope.fieldInformation.fieldValueList);
              addWatchers();
              scope.abbreviation = abbreviation(options);
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
        watchDestroyField();
      }
    };
  }
})();
