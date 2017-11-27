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
      link: function (scope, element) {
        scope.fieldName = scope.$id;
        scope.title = scope.filterInformation.filter.usePropertyField ?
          scope.filterInformation.propertyField.lang : scope.filterInformation.filter.lang;
        scope.emitSelectedOption = emitSelectedOption;
        scope.filter = {selected: false};

        var optionsData = scope.filterInformation.filter.options ? scope.filterInformation.filter.options : {};
        var showingStepList = optionsData.showingStepList ?
          optionsData.showingStepList : scope.filterInformation.filter.showingStepList;

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

        function detectConditionalShowFilter() {
          if(showingStepList.length){
            var filterId = _.first(showingStepList).filterCondition.filterId;
            if(_.isObject(optionsData) && optionsData.showOnSpecificID){
              internalDependencyShowHandler(filterId, optionsData.showOnSpecificID);
            }
          }
        }

        function internalDependencyShowHandler(filterId, dependentValue){
          if(!scope.conditionalFilter) {
            displayFilter(false);
          }

          var destroyListener = $rootScope.$on("FilterSystem:filterSelected", function (event, filterInformation) {
            if(filterInformation.propertyFilter.filter.id === filterId){
              var showFilter = _.find(filterInformation.selectedFilterOptions, function (selectedOption) {
                return selectedOption.optionId === dependentValue;
              });
              displayFilter(showFilter);
            }
          });

          scope.$on("$destroy", destroyListener);
        }

        function displayFilter(show) {
          var displayValue = show ? "initial" : "none";
          element.closest(".filterContainer").css("display",displayValue);
        }

        detectConditionalShowFilter();
        detectBinaryFilterType();
        watchCleanFilter();
      }
    };
  }
})();
