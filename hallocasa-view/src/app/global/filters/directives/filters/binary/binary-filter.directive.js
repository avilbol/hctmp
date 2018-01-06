(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('binaryFilter', binaryFilter);

  function binaryFilter($rootScope, FiltersService) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/filters/directives/filters/binary/binary-filter.html",
      scope: {
        filtersScope: "=?",
        filterInformation: "=",
        filtersRootScope: "=?",
        additionalParameters: "=?"
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

        function emitSelectedOption(preventUpdateContext) {
          var selectionPayload = {
            propertyFilter: scope.filterInformation,
            apply: scope.filter.selected,
            binaryFilterType: scope.binaryFilterType
          };
          $rootScope.$broadcast("FilterSystem:filterSelected", selectionPayload);
          if(!preventUpdateContext){
            updateContext();
          }
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
            updateContext();
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

        function loadContext() {
          if(!scope.additionalParameters || !scope.additionalParameters.filtersContext) {return;}

          var context = FiltersService.loadContext(scope.additionalParameters.filtersContext);
          var filterID = scope.filterInformation.filter.id;
          var savedFilterModel = context.filtersModel ? context.filtersModel[filterID] : undefined;

          if(!savedFilterModel){return;}

          var apply = context.filtersModel[filterID].apply;

          if(!_.isUndefined(apply)){
            scope.filter.selected = true;
            emitSelectedOption(true);
          }
        }

        function updateContext() {
          var context = FiltersService.loadContext(scope.additionalParameters.filtersContext);
          var filter = scope.filterInformation.filter;
          var filterID = filter.id;
          var queryName = filter.queryName ? filter.queryName : filter.name;

          context.filtersModel = context.filtersModel ? context.filtersModel : {};
          context.filtersModel[filterID] = {};
          context.filtersModel[filterID].queryName = queryName;

          if(scope.filter.selected){
            context.filtersModel[filterID].apply = true;
          }
          else{
            delete context.filtersModel[filterID];
          }

          FiltersService.saveContext(scope.additionalParameters.filtersContext, context);
        }

        detectConditionalShowFilter();
        detectBinaryFilterType();
        watchCleanFilter();
        loadContext();
      }
    };
  }
})();
