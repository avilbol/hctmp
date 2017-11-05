(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('repeaterFilterGroup', repeaterFilterGroup);

  function repeaterFilterGroup($rootScope, FiltersService) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/filters/directives/components/repeater-filter-group/repeater-filter-group.html",
      scope: {
        idFilter: "=?",
        filtersList: "=?",
        filtersRootScope: "=?",
        contentFlex: "=?",
        parentIdName: "=?"
      },
      link: function (scope) {
        var repeatedFilter = _.first(scope.filtersList);

        scope.valueList = [];

        function watchFilter() {
          var destroyListener = $rootScope.$on("FilterSystem:filterSelected", function (event, filterInformation) {
            if(filterInformation.propertyFilter.filter.id === scope.idFilter){

              FiltersService.loadFiltersOptions(repeatedFilter.filter.id, [filterInformation]).then(function (options) {
                updateFilterList(filterInformation, options);
              });
            }
          });

          scope.$on("$destroy", destroyListener);
        }

        function updateFilterList(filterInformation, options) {
          _.each(filterInformation.selectedFilterOptions, function (selectedOption) {
            var found = _.find(scope.valueList, function (filterData) {
              return filterData.filter.options.showOnSpecificID === selectedOption.optionId;
            });

            if(!found) {
              var filterData = angular.copy(repeatedFilter);
              var filterOptions = _.filter(options, function (option) {
                return option.parentInfo[scope.parentIdName] === selectedOption.optionId;
              });

              filterData.filter.options.showOnSpecificID = selectedOption.optionId;
              filterData.filter.options.conditionalFilter = true;
              filterData.propertyField.dropdownOptionGroup = {dropdownOptionList: filterOptions, translationManagement: "NONE"};

              scope.valueList.push(filterData);
            }
          });
        }

        watchFilter();
      }
    };
  }
})();
