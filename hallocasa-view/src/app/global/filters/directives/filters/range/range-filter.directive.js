(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('rangeFilter', rangeFilter);

  function rangeFilter($rootScope, $timeout) {
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
        scope.title = scope.filterInformation.filter.usePropertyField ?
          scope.filterInformation.propertyField.lang : scope.filterInformation.filter.lang;
        scope.emitSelectedOption = emitSelectedOption;
        /*
        scope.floor = scope.filterInformation.filter.minValue;
        scope.ceiling = scope.filterInformation.filter.maxValue;
        scope.lowValue = scope.filterInformation.filter.minValue;
        scope.highValue = scope.filterInformation.filter.maxValue;
        */

        /*
        * TODO: Temporal test values, delete when returned values from backend has valid values
        * */
        scope.floor = 0;
        scope.ceiling = 1000;
        scope.lowValue = 0;
        scope.highValue = 1000;

        function emitSelectedOption(range) {
          if(ngModelTimeOut){
            //if there is already a timeout in process cancel it
            $timeout.cancel(ngModelTimeOut);
          }
          ngModelTimeOut = $timeout(function(){
            var selectionPayload = {
              "minValue": range.lowValue,
              "maxValue": range.highValue,
              propertyFilter: scope.filterInformation,
              rangeFilterType: scope.rangeFilterType
            };
            $rootScope.$broadcast("FilterSystem:filterSelected", selectionPayload);
            ngModelTimeOut = null;
          },500);
        }
      }
    };
  }
})();
