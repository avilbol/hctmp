(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('textFilter', textFilter);

  function textFilter($rootScope, FiltersService) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/filters/directives/filters/text/text-filter.html",
      scope: {
        filtersScope: "=?",
        filterInformation: "=",
        filtersRootScope: "=?",
        additionalParameters: "=?"
      },
      link: function (scope) {
        scope.fieldName = scope.$id;
        scope.title = scope.filterInformation.filter.usePropertyField ?
          scope.filterInformation.propertyField.lang : scope.filterInformation.filter.lang;
        scope.emitSelectedOption = emitSelectedOption;
        scope.filter = {selected: ''};

        function emitSelectedOption(preventUpdateContext) {
          var selectionPayload = {
            propertyFilter: scope.filterInformation,
            apply: scope.filter.selected,
            textFilterType: scope.textFilterType
          };

          $rootScope.$broadcast("FilterSystem:filterSelected", selectionPayload);

          if(!preventUpdateContext){
            updateContext();
          }
        }

        function detectTextFilterType() {
          var filterType = scope.filterInformation.filter.filterType;

          if(filterType.useText){
            scope.textFilterType = "Text";
            return;
          }
          //Default type
          scope.textFilterType = "Text";
        }

        function watchCleanFilter() {
          var watcher = $rootScope.$on("FilterSystem:clearFilters", function () {
            scope.filter.selected = '';
            updateContext();
          });
          scope.$on("$destroy", watcher);
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
          var filterID = scope.filterInformation.filter.id;
          context.filtersModel = context.filtersModel ? context.filtersModel : {};
          context.filtersModel[filterID] = {};

          if(scope.filter.selected){
            context.filtersModel[filterID].apply = true;
          }
          else{
            delete context.filtersModel[filterID];
          }

          FiltersService.saveContext(scope.additionalParameters.filtersContext, context);
        }

        detectTextFilterType();
        watchCleanFilter();
        loadContext();
      }
    };
  }
})();
