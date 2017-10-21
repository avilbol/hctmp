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
        scope.range = {};

        scope.emitSelectedOption = emitSelectedOption;

        function initialize() {
          switch (scope.filterInformation.filter.filterType.rangeFieldPresentation){
            case "INTEGER":
            case "DOUBLE":
            case "CURRENCY":
              if(scope.filterInformation.filter.filterType.validateMin){
                scope.range.floor = scope.filterInformation.filter.options.range.floor;
              }
              if(scope.filterInformation.filter.filterType.validateMax){
                scope.range.ceiling = scope.filterInformation.filter.options.range.ceiling;
              }

              if(scope.filterInformation.filter.filterType.useSlider){
                scope.range.lowValue = scope.range.floor;
                scope.range.highValue = scope.range.ceiling;
              }

              break;
            case "DATE":
              scope.range.lowValue = new Date();
              scope.range.highValue = new Date();
              break;
          }
        }

        function emitSelectedOption() {
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
                selectionPayload.minDateValue = scope.range.lowValue;
                selectionPayload.maxDateValue = scope.range.highValue;
                break;

              case "CURRENCY":
                var currencyID = scope.currentCurrency().id;
                selectionPayload.minCrcyValue = {
                  currency: {id: currencyID},
                  ammount: scope.range.lowValue
                };
                selectionPayload.maxCrcyValue = {
                  currency: {id: currencyID},
                  ammount: scope.range.highValue
                };
                break;

              default:
                selectionPayload.minValue = scope.range.lowValue;
                selectionPayload.maxValue = scope.range.highValue;
            }

            $rootScope.$broadcast("FilterSystem:filterSelected", selectionPayload);
            ngModelTimeOut = null;
          },500);
        }

        function watchCleanFilter() {
          var watcher = $rootScope.$on("FilterSystem:clearFilters", initialize);
          scope.$on("$destroy", watcher);
        }

        initialize();
        watchCleanFilter();
      }
    };
  }
})();
