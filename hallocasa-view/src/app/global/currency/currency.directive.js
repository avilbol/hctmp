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
      controller: function ($scope, CurrencyService, CURRENCIES, toastr, translateFilter, localStorageService) {
        $scope.changeCurrency = changeCurrency;

        $scope.$on('$changedCurrency', loadCurrencyState);

        CurrencyService.loadCurrency()
          .then(function (data) {
            $scope.currencyList = data;
            loadCurrencyState();
          })
          .catch(function () {
            toastr.warning(translateFilter("Error.whenloadingmoney"));
          });

        function loadCurrencyState() {
          if(!$scope.currencyList){ return; }
          var selectedUserCurrency = localStorageService.get("currentCurrency");
          var currencyState;

          if(_.isNumber(selectedUserCurrency)){
            currencyState = loadSpecificCurrency(selectedUserCurrency);
          }
          else{
            currencyState = loadSpecificCurrency(CURRENCIES.defaultCurrencyId);
          }

          $scope.currentCurrency = currencyState;
          CurrencyService.setCurrentCurrency(currencyState);
        }

        function changeCurrency(newCurrency) {
          $scope.currentCurrency = newCurrency;
          CurrencyService.setCurrentCurrency(newCurrency);
          localStorageService.set("SelectedUserCurrency", true);
        }

        function loadSpecificCurrency(idToSearch){
          return _.find($scope.currencyList, function(currencyItem){
            return currencyItem.id === idToSearch;
          });
        }

      }
    };
  }
})();
