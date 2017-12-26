(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('repeaterFilterGroup', repeaterFilterGroup);

  function repeaterFilterGroup($rootScope) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/filters/directives/components/repeater-filter-group/repeater-filter-group.html",
      scope: {
        options: "=?",
        filtersList: "=?",
        filtersRootScope: "=?",
        contentFlex: "=?",
        additionalParameters: "=?"
      },
      link: function (scope) {
        var repeatedFilter = _.first(scope.filtersList);

        scope.valueList = [];

        function watchFilter() {
          var destroyListener = $rootScope.$on("FilterSystem:filterSelected", function (event, filterInformation) {
            if(filterInformation.propertyFilter.filter.id === scope.options.idField){
              updateFilterList(filterInformation);
            }
          });

          scope.$on("$destroy", destroyListener);
        }

        function updateFilterList(filterInformation) {
          _.each(filterInformation.selectedFilterOptions, function (selectedOption) {
            var found = _.find(scope.valueList, function (filterData) {
              return filterData.filter.options.showOnSpecificID === selectedOption.optionId;
            });

            if(!found) {
              enableConditionalFilter(filterInformation, selectedOption);
            }
          });
        }

        function enableConditionalFilter(filterInformation, selectedOption) {
          var filterData = angular.copy(repeatedFilter);
          filterData.filter.options.showOnSpecificID = selectedOption.optionId;
          filterData.filter.options.conditionalFilter = true;
          filterData.filter.options.type = "filter_options";
          filterData.filter.options.parentIDName = scope.options.parentIDName;
          filterData.filter.options.parentFilterInformation = filterInformation.propertyFilter;

          scope.valueList.push(filterData);
        }

        watchFilter();
      }
    };
  }
})();
