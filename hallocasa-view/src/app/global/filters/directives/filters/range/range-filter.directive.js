(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('rangeFilter', rangeFilter);

  function rangeFilter($rootScope, $timeout, CurrencyService) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/filters/directives/filters/range/range-filter.html",
      scope: {
        filtersScope: "=?",
        filterInformation: "=",
        filtersRootScope: "=?"
      },
      link: function (scope) {
        var ngModelTimeOut;

        scope.fieldName = scope.$id;
        scope.currentCurrency = CurrencyService.getCurrentCurrency;
        scope.title = scope.filterInformation.filter.usePropertyField ?
          scope.filterInformation.propertyField.lang : scope.filterInformation.filter.lang;
        scope.filterInformation.filter.options = scope.filterInformation.filter.options ?
          scope.filterInformation.filter.options : {};
        scope.filterInformation.filter.options.step = scope.filterInformation.filter.options.step ?
          scope.filterInformation.filter.options.step : 1;
        scope.filterInformation.filter.options.buffer = scope.filterInformation.filter.options.buffer ?
          scope.filterInformation.filter.options.buffer : 10;

        scope.emitSelectedOption = emitSelectedOption;

        function initialize() {
          switch (scope.filterInformation.filter.filterType.rangeFieldPresentation){
            case "INTEGER":
            case "DOUBLE":
            case "CURRENCY":
              // if(scope.filterInformation.filter.filterType.validateMin){
              //   scope.floor = scope.filterInformation.filter.minValue;
              //   scope.lowValue = scope.filterInformation.filter.minValue;
              // }
              // if(scope.filterInformation.filter.filterType.validateMax){
              //   scope.ceiling = scope.filterInformation.filter.maxValue;
              //   scope.highValue = scope.filterInformation.filter.maxValue;
              // }

              /*
              * TODO: Temporal test values, delete when returned values from backend has valid values
              * */
              scope.floor = 0;
              scope.ceiling = 1000;
              scope.lowValue = 0;
              scope.highValue = 1000;

              break;
            case "DATE":
              // if(scope.filterInformation.filter.filterType.validateMin){
              //   scope.lowValue = new Date(scope.filterInformation.filter.minValue);
              // }
              // if(scope.filterInformation.filter.filterType.validateMax){
              //   scope.highValue = new Date(scope.filterInformation.filter.maxValue);
              // }

              /*
              * TODO: Temporal test values, delete when returned values from backend has valid values
              * */
              scope.lowValue = new Date();
              scope.highValue = new Date();
              break;
          }
        }

        function emitSelectedOption(range) {
          if(ngModelTimeOut){
            //if there is already a timeout in process cancel it
            $timeout.cancel(ngModelTimeOut);
          }
          ngModelTimeOut = $timeout(function(){
            var selectionPayload = {
              propertyFilter: scope.filterInformation,
              rangeFilterType: scope.rangeFilterType
            };

            switch (scope.filterInformation.filter.filterType.rangeFieldPresentation){
              case "DATE":
                selectionPayload.minDateValue = range.lowValue;
                selectionPayload.maxDateValue = range.highValue;
                break;

              case "CURRENCY":
                var currencyID = scope.currentCurrency().id;
                selectionPayload.minCrcyValue = {
                  currency: {id: currencyID},
                  ammount: range.lowValue
                };
                selectionPayload.maxCrcyValue = {
                  currency: {id: currencyID},
                  ammount: range.highValue
                };
                break;

              default:
                selectionPayload.minValue = range.lowValue;
                selectionPayload.maxValue = range.highValue;
            }

            $rootScope.$broadcast("FilterSystem:filterSelected", selectionPayload);
            ngModelTimeOut = null;
          },500);
        }

        initialize();
      }
    };
  }
})();
