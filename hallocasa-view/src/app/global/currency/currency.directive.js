(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .directive('currencySelect', currencySelect);

  function currencySelect() {
    return {
      restrict: 'AE',
      replace: true,
      template:
      "<md-menu flex>"+
      "<md-button ng-click='$mdMenu.open($event)' aria-label='Open currency dropdown' class='bold-hover'>"+
      "<div>{{currentCurrency.abbreviation}}</div>"+
      "</md-button>"+
      "<md-menu-content>"+
      "<md-menu-item ng-repeat='currency in currencyList'>" +
      "<md-button ng-click='changeCurrency(currency)'>{{currency.abbreviation}}</md-button>" +
      "</md-menu-item>"+
      "</md-menu-content>"+
      "</md-menu>",
      controller: function ($scope, CurrencyService, CURRENCIES, toastr, translateFilter) {
        $scope.changeCurrency = changeCurrency;

        $scope.$watch('currencyState.currentCurrency', function(currentCurrency){
          if(!currentCurrency || ($scope.currentCurrency && currentCurrency.id == $scope.currentCurrency.id)){
            return;
          }
          var currencyId = currentCurrency ? currentCurrency.id : CURRENCIES.defaultCurrencyId;
          $scope.currentCurrency = loadSpecificCurrency(currencyId);
        });

        CurrencyService.loadCurrency()
          .then(function (data) {
            $scope.currencyList = data;
            $scope.currencyState =  CurrencyService.getCurrencyState();
          })
          .catch(function () {
            toastr.warning(translateFilter("Error.whenloadingmoney"));
          });

        function changeCurrency(newCurrency) {
          $scope.currentCurrency = newCurrency;
          CurrencyService.setCurrentCurrency(newCurrency);
        }

        function loadSpecificCurrency(idToSearch){
          return _.find($scope.currencyList, function(currencyItem){
            return currencyItem.id == idToSearch;
          });
        }

      }
    };
  }
})();
