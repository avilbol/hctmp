(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('currencyField', currencyField);

  function currencyField(FieldsService, toastr) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/currency/currency-field.html",
      scope: {
        fieldInformation: "=",
        form: "=?"
      },
      link: function (scope) {
        function applyValidations() {
          if (scope.fieldInformation.validations) {
            scope.required = scope.fieldInformation.validations.includes("required");
          }
        }
        
        FieldsService.loadOptionsByServiceId("Currency")
          .then(function (options) {
            scope.options = options
          })
          .catch(function () {
            //TODO: Traducción de mensaje de error
            toastr.warning("Error al cargar opciones del servicio: Currency");
            scope.options = [];
          });
        
        applyValidations();
      }
    };
  }
})();
