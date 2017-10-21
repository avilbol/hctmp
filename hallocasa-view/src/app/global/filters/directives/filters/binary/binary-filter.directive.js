(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('binaryFilter', binaryFilter);

  function binaryFilter($rootScope) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/filters/directives/filters/binary/binary-filter.html",
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
            binaryFilterType: scope.binaryFilterType
          };
          $rootScope.$broadcast("FilterSystem:filterSelected", selectionPayload);
        }

        function detectBinaryFilterType() {
          var filterType = scope.filterInformation.filter.filterType;
          if(filterType.useYesNoDropdown){
            scope.binaryFilterType = "Dropdown";
            return;
          }
          if(filterType.useCheckbox){
            scope.binaryFilterType = "Checkbox";
            return;
          }
          if(filterType.useRadio){
            scope.binaryFilterType = "Radio";
            return;
          }
          if(filterType.useText){
            scope.binaryFilterType = "Text";
            return;
          }

          //Default type
          scope.binaryFilterType = "Dropdown";
        }

        function watchCleanFilter() {
          var watcher = $rootScope.$on("FilterSystem:clearFilters", function () {
            scope.filter.selected = false;
          });
          scope.$on("$destroy", watcher);
        }

        detectBinaryFilterType();
        watchCleanFilter();
      }
    };
  }
})();
