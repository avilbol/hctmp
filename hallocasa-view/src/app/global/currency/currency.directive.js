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
      "<md-menu>"+
      "<md-button ng-click='$mdOpenMenu($event)'>"+
      "<i class='material-icons'>attach_money</i> <div>{{currentCurrency.name}}</div>"+
      "</md-button>"+
      "<md-menu-content>"+
      "<md-menu-item ng-repeat='currency in currencyList'>" +
      "<md-button ng-click='changeCurrency(currency)'>{{currency.name}}</md-button>" +
      "</md-menu-item>"+
      "</md-menu-content>"+
      "</md-menu>",
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
