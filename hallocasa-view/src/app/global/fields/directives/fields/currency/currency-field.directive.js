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
              scope.options = options
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
