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
        fieldInformation: "="
      },
      link: function (scope) {
        FieldsService.loadOptionsByServiceId("Currency")
          .then(function (options) {
            scope.options = options
          })
          .catch(function () {
            //TODO: Traducción de mensaje de error
            toastr.warning("Error al cargar opciones del servicio: Currency");
            scope.options = [];
          });
      }
    };
  }
})();
