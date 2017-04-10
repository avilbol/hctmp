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
      "<md-button ng-click='$mdMenu.open($event)' aria-label='Open currency dropdown' class='bold-hover'>"+
      "<div>{{currentCurrency.abbreviation}}</div>"+
      "</md-button>"+
      "<md-menu-content>"+
      "<md-menu-item ng-repeat='currency in currencyList'>" +
      "<md-button ng-click='changeCurrency(currency)'>{{currency.abbreviation}}</md-button>" +
      "</md-menu-item>"+
      "</md-menu-content>"+
      "</md-menu>"+
      "</div>",
      controller: function ($scope, CurrencyService, toastr, translateFilter) {
        $scope.changeCurrency = changeCurrency;

        CurrencyService.loadCurrency()
          .then(function (data) {
            $scope.currencyList = data;
            $scope.currentCurrency = data[0];

            CurrencyService.setCurrentCurrency($scope.currentCurrency);
          })
          .catch(function () {
            toastr.warning(translateFilter("Error.whenloadingmoney"));
          });

        function changeCurrency(newCurrency) {
          $scope.currentCurrency = newCurrency;
          CurrencyService.setCurrentCurrency(newCurrency);
        }

      }
    };
  }
})();
