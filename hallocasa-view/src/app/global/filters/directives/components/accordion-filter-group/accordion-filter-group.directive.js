(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('accordionFilterGroup', accordionFilterGroup);

  function accordionFilterGroup(translateFilter, $log, $rootScope) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/filters/directives/components/accordion-filter-group/accordion-filter-group.html",
      scope: {
        groupTitle: "=?",
        filtersList: "=",
        filtersScope: "=?",
        filtersRootScope: "=?",
        options: "=?",
        additionalParameters: "=?"
      },
      link: function (scope, element) {
        var optionsData = _.isObject(scope.options) ? scope.options : {};
        var destroyWatcher = scope.$watch("groupTitle",renderTitle);

        scope.$on("$destroy",destroyWatcher);

        function renderTitle() {
          scope.renderedTitle = "";
          _.each(scope.groupTitle, function (titleSegment) {
            switch (titleSegment.type){
              case "translate_key":
                scope.renderedTitle += translateFilter(titleSegment.value);
                break;
              case "literal_string":
                scope.renderedTitle += titleSegment.value;
                break;
              default:
                $log.warn("Tipo de segmento de título desconocido: ",titleSegment);
                scope.renderedTitle += titleSegment.value;
            }
            scope.renderedTitle += " ";
          });
        }

        function detectConditionalShowComponent() {
          if(optionsData.showOnSelectedOption){
            internalDependencyShowHandler(optionsData.showOnSelectedOption);
          }
        }

        function internalDependencyShowHandler(filterId) {
          displayComponent(false);
          watchCleanFilter();

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

        function watchCleanFilter() {
          var watcher = $rootScope.$on("FilterSystem:clearFilters", function () {
            displayComponent(false);
          });
          scope.$on("$destroy", watcher);
        }

        detectConditionalShowComponent();
      }
    };
  }
})();
