(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .directive('currencySelect', currencySelect);

  function currencySelect() {
    return {
      restrict: 'A',
      replace: true,
      template:
      "<div>"+
      "<md-menu layout-fill layout='row' layout-align='center stretch'>"+
      "<md-button ng-click='$mdOpenMenu($event)' aria-label='Open currency dropdown'>"+
      "<div>{{currentCurrency.lang | translate}}</div>"+
      "</md-button>"+
      "<md-menu-content>"+
      "<md-menu-item ng-repeat='currency in currencyList'>" +
      "<md-button ng-click='changeCurrency(currency)'>{{currency.lang | translate}}</md-button>" +
      "</md-menu-item>"+
      "</md-menu-content>"+
      "</md-menu>"+
      "</div>",
      controller: function ($scope, CurrencyService, toastr) {
        $scope.changeCurrency = changeCurrency;

        CurrencyService.loadCurrency()
          .then(function (data) {
            $scope.currencyList = data;
            $scope.currentCurrency = data[0];

            CurrencyService.setCurrentCurrency($scope.currentCurrency);
          })
          .catch(function (error) {
            //TODO: Traducción de mensaje de error
            toastr.info(error, "Error al cargar monedas");
          });

        function changeCurrency(newCurrency) {
          $scope.currentCurrency = newCurrency;
          CurrencyService.setCurrentCurrency(newCurrency);
        }

      }
    };
  }
})();
