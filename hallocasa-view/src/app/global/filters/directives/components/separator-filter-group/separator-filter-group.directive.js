(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('separatorFilterGroup', separatorGroup);

  function separatorGroup($rootScope) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/filters/directives/components/separator-filter-group/separator-filter-group.html",
      scope: {
        filtersList: "=",
        filtersScope: "=?",
        filtersRootScope: "=?",
        filterInformation: "=?",
        additionalParameters: "=?"
      },
      link: function (scope, element) {
        var optionsData = scope.filterInformation.options;

        function detectConditionalShowComponent() {
          if(_.isObject(optionsData) && optionsData.showOnSelectedOption){
            internalDependencyShowHandler(optionsData.showOnSelectedOption);
          }
        }

        function internalDependencyShowHandler(filterId) {
          displayComponent(false);

          var destroyListener = $rootScope.$on("FilterSystem:filterSelected", function (event, filterInformation) {
            if(filterInformation.propertyFilter.filter.id === filterId){
              var showFilter = filterInformation.selectedFilterOptions.length > 0;
              displayComponent(showFilter);
            }
          });

          scope.$on("$destroy", destroyListener);
        }

        function displayComponent(show) {
          var displayValue = show ? "initial" : "none";
          element.closest(".filterContainer").css("display",displayValue);
        }

        detectConditionalShowComponent();
      }
    };
  }
})();
