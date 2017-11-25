(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('textFilter', textFilter);

  function textFilter($rootScope) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/filters/directives/filters/text/text-filter.html",
      scope: {
        filtersScope: "=?",
        filterInformation: "=",
        filtersRootScope: "=?"
      },
      link: function (scope) {
        scope.fieldName = scope.$id;
        scope.title = scope.filterInformation.filter.usePropertyField ?
          scope.filterInformation.propertyField.lang : scope.filterInformation.filter.lang;
        scope.emitSelectedOption = emitSelectedOption;
        scope.filter = {selected: false};

        function emitSelectedOption() {
          var selectionPayload = {
            propertyFilter: scope.filterInformation,
            apply: scope.filter.selected,
            textFilterType: scope.textFilterType
          };
          $rootScope.$broadcast("FilterSystem:filterSelected", selectionPayload);
        }

        function detectTextFilterType() {
          var filterType = scope.filterInformation.filter.filterType;
          // if(filterType.useYesNoDropdown){
          //   scope.textFilterType = "Dropdown";
          //   return;
          // }
          // if(filterType.useCheckbox){
          //   scope.textFilterType = "Checkbox";
          //   return;
          // }
          // if(filterType.useRadio){
          //   scope.textFilterType = "Radio";
          //   return;
          // }
          if(filterType.useText){
            scope.textFilterType = "Text";
            return;
          }

          //Default type
          scope.textFilterType = "Text";
        }

        function watchCleanFilter() {
          var watcher = $rootScope.$on("FilterSystem:clearFilters", function () {
            scope.filter.selected = false;
          });
          scope.$on("$destroy", watcher);
        }

        detectTextFilterType();
        watchCleanFilter();
      }
    };
  }
})();
