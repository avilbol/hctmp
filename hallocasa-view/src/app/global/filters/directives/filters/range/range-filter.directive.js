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
      link: function (scope, element) {
        var ngModelTimeOut;
        var optionsData = scope.filterInformation.filter.options ? scope.filterInformation.filter.options : {};
        var showingStepList = optionsData.showingStepList ?
          optionsData.showingStepList : scope.filterInformation.filter.showingStepList;

        scope.fieldName = scope.$id;
        scope.currentCurrency = CurrencyService.getCurrentCurrency;
        scope.viewValueProcessor = viewValueProcessor;
        scope.title = scope.filterInformation.filter.usePropertyField ?
          scope.filterInformation.propertyField.lang : scope.filterInformation.filter.lang;
        scope.filterInformation.filter.options = scope.filterInformation.filter.options ?
          scope.filterInformation.filter.options : {range: {
            floor: 0,
            ceiling: 100
          }};
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
            case "DATE":
            case "CURRENCY":
              if(scope.filterInformation.filter.options.range){
                scope.range.floor = scope.filterInformation.filter.options.range.floor;
                scope.range.ceiling = scope.filterInformation.filter.options.range.ceiling;
              }

              if(scope.filterInformation.filter.filterType.useSlider){
                scope.range.lowValue = scope.range.floor;
                scope.range.highValue = scope.range.ceiling;
              }
              else{
                scope.range.lowValue = undefined;
                scope.range.highValue = undefined;
              }
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

                if(scope.range.highValue !== scope.range.ceiling) {
                  selectionPayload.maxCrcyValue = {
                    currency: {id: currencyID},
                    ammount: scope.range.highValue
                  };
                }

                if(scope.range.lowValue !== scope.range.floor) {
                  selectionPayload.minCrcyValue = {
                    currency: {id: currencyID},
                    ammount: scope.range.lowValue
                  };
                }

                break;

              default:
                if(scope.range.highValue !== scope.range.ceiling) {
                  selectionPayload.maxValue = scope.range.highValue;
                }

                if(scope.range.lowValue !== scope.range.floor) {
                  selectionPayload.minValue = scope.range.lowValue;
                }
            }

            $rootScope.$broadcast("FilterSystem:filterSelected", selectionPayload);
            ngModelTimeOut = null;
          },500);
        }

        function watchCleanFilter() {
          var watcher = $rootScope.$on("FilterSystem:clearFilters", initialize);
          scope.$on("$destroy", watcher);
        }

        function viewValueProcessor(value) {
          var rangeConfig = scope.filterInformation.filter.options.range;
          if(!rangeConfig){ return value; }

          var prefixString = rangeConfig.prefixString ? rangeConfig.prefixString : "";
          var suffixString = rangeConfig.suffixString ? rangeConfig.suffixString : "";

          if(Number(value) === scope.range.ceiling){
            suffixString = suffixString + "+";
          }

          return prefixString + value + suffixString;
        }

        function detectConditionalHideFilter() {
          if(showingStepList.length){
            var filterId = _.first(showingStepList).filterCondition.filterId;
            if(_.isObject(optionsData) && optionsData.hideOnSpecificID){
              internalDependencyHideHandler(filterId, optionsData.hideOnSpecificID);
            }
          }
        }

        function internalDependencyHideHandler(filterId, dependentValue){
          displayFilter(true);

          var destroyListener = $rootScope.$on("FilterSystem:filterSelected", function (event, filterInformation) {
            if(filterInformation.propertyFilter.filter.id === filterId){
              var hideFilter = !_.find(filterInformation.selectedFilterOptions, function (selectedOption) {
                return selectedOption.optionId === dependentValue;
              });
              displayFilter(hideFilter);
            }
          });

          scope.$on("$destroy", destroyListener);
        }

        function displayFilter(show) {
          var displayValue = show ? "initial" : "none";
          element.closest(".filterContainer").css("display",displayValue);
          scope.$broadcast('refreshSlider');
        }

        detectConditionalHideFilter();
        initialize();
        watchCleanFilter();
      }
    };
  }
})();
