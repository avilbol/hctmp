(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('dropdownFilter', dropdownFilter);

  function dropdownFilter(FieldsService, FiltersService, $rootScope, $timeout, translateFilter, unicodeFilter, toastr) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/filters/directives/filters/dropdown/dropdown-filter.html",
      scope: {
        filtersScope: "=?",
        filterInformation: "=",
        filtersRootScope: "=?",
        additionalParameters: "=?"
      },
      link: function (scope, element) {
        var optionsData = scope.filterInformation.filter.options ? scope.filterInformation.filter.options : {};
        var showingStepList = optionsData.showingStepList ?
          optionsData.showingStepList : scope.filterInformation.filter.showingStepList;

        var ngModelTimeOut;

        scope.conditionalFilter = (_.isObject(optionsData) && optionsData.conditionalFilter);
        scope.filterName = scope.$id;
        scope.title = scope.filterInformation.filter.usePropertyField  && scope.filterInformation.propertyField ?
          scope.filterInformation.propertyField.lang : scope.filterInformation.filter.lang;
        scope.emitSelectedOption = emitSelectedOption;
        scope.search = {};
        scope.selected = {options: []};
        scope.selectAll = selectAll;
        scope.selectAllButtonTranstationKey = "placeholder.selectAll";
        var selectionState = "selectAll";

        function loadOptions() {
          if(!optionsData){
            return;
          }
          switch(optionsData.type){
            case "static_options":
              staticOptionsHandler();
              break;
            case "dynamic_options":
              dynamicOptionsHandler();
              break;
          }
        }

        function staticOptionsHandler(){
          var staticOptionsGroup, optionsList, processedOptions;
          if(scope.filterInformation.filter.usePropertyField){
            staticOptionsGroup = scope.filterInformation.propertyField.dropdownOptionGroup;
            optionsList = staticOptionsGroup.dropdownOptionList;
          }
          else{
            staticOptionsGroup= scope.filterInformation.filter.dropdownOptionGroup;
            optionsList = staticOptionsGroup.dropdownOptionList;
          }

          processedOptions = FieldsService.processOptions(optionsList, staticOptionsGroup.translationManagement);
          setFilterOptions(processedOptions);
          loadContext();
        }

        function dynamicOptionsHandler() {
          var serviceId = optionsData.serviceId;
          var translationManagement = optionsData.translationManagement ? optionsData.translationManagement : "NONE";
          var payload = optionsData.referredDependencyPayload ? optionsData.showOnSpecificID : undefined;
          FieldsService.loadOptionsByServiceId(serviceId, payload)
            .then(function (options) {
              options = FieldsService.processOptions(options, translationManagement);
              scope.options = _.map(options, function (option) {
                option.optionId = option.id;
                option.tmplTranslate = unicodeFilter(option.tmplTranslate);
                return option;
              });

              scope.optionsLabel = "name";
            })
            .catch(function () {
              toastr.warning(
                translateFilter("Error.whenloadingserviceoptions"), serviceId);
              scope.options = [];
            })
            .finally(function () {
              scope.filterInformation.filter.processedOptions = scope.options;
              loadContext();
            });
        }

        function setFilterOptions(processedOptions) {
          scope.options = processedOptions;

          if(scope.conditionalFilter){
            var filterInformation = FiltersService.getFilterById(scope.filterInformation.filter.id, scope.filtersRootScope);
            filterInformation.filter.processedOptions = _.isArray(filterInformation.filter.processedOptions) ?
              _.union(filterInformation.filter.processedOptions, processedOptions) : processedOptions;
          }
          else{
            scope.filterInformation.filter.processedOptions = processedOptions;
          }
        }

        function emitSelectedOption(preventUpdateContext) {
          if(ngModelTimeOut){
            //if there is already a timeout in process cancel it
            $timeout.cancel(ngModelTimeOut);
          }
          ngModelTimeOut = $timeout(function () {
            broadcastSelections();
            if(!preventUpdateContext){
              updateContext();
            }
          },500);
        }

        function broadcastSelections() {
          var selectedFilterOptions;
          var selectedOptions = scope.selected.options;

          if(_.isArray(selectedOptions)){
            selectedFilterOptions =  _.map(selectedOptions, function (option) {
              return {optionId: option};
            });

            if(selectedOptions.length === scope.options.length){
              selectionState = "deselectAll";
              scope.selectAllButtonTranstationKey = "placeholder.deselectAll";
            }
            if(!selectedOptions.length){
              selectionState = "selectAll";
              scope.selectAllButtonTranstationKey = "placeholder.selectAll";
            }
          }
          else{
            var option = {optionId: selectedOptions};
            selectedFilterOptions = _.isEmpty(option) ? [] : [option];
          }

          if(scope.conditionalFilter){
            selectedFilterOptions = synchronizeLocalFilterSelectedOptions(selectedFilterOptions);
          }

          var selectionPayload = {
            propertyFilter: scope.filterInformation,
            selectedFilterOptions: selectedFilterOptions
          };
          $rootScope.$broadcast("FilterSystem:filterSelected", selectionPayload);
        }

        function synchronizeLocalFilterSelectedOptions(selectedFilterOptions) {
          var localFilterSelectedOptions = getLocalFilterSelectedOptions();

          _.each(scope.options, function (option) {
            var localSelected = _.find(selectedFilterOptions, function (selectedOption) {
              return selectedOption.optionId === option.optionId;
            });

            var localOptionIndex = _.findIndex(localFilterSelectedOptions, function (localSelected) {
              return option.optionId === localSelected.optionId
            });

            if(localSelected && localOptionIndex === -1){
              var synchronizedOption = _.pick(option, "optionId");
              localFilterSelectedOptions.push(synchronizedOption);
            }
            if(!localSelected && localOptionIndex !== -1){
              localFilterSelectedOptions.splice(localOptionIndex, 1);
            }

          });
          setLocalFilterSelectedOptions(localFilterSelectedOptions);
          return localFilterSelectedOptions;
        }

        function watchRender() {
          $timeout(function () {
            $timeout(function () {
              postRender();
            }, 0);
          }, 0);
        }

        function postRender() {
          element.find("input").on("keydown", function(ev) {
            ev.stopPropagation();
          });
        }

        function selectAll() {
          if(selectionState === "deselectAll"){
            selectionState = "selectAll";
            scope.selected.options = [];
            scope.selectAllButtonTranstationKey = "placeholder.selectAll";
          }
          else{
            selectionState = "deselectAll";
            scope.selected.options = scope.options;
            scope.selectAllButtonTranstationKey = "placeholder.deselectAll";
          }
          emitSelectedOption();
        }

        function watchCleanFilter() {
          var watcher = $rootScope.$on("FilterSystem:clearFilters", function () {
            scope.selected.options = [];
            selectionState = "selectAll";
            scope.selectAllButtonTranstationKey = "placeholder.selectAll";
            setLocalFilterSelectedOptions([]);
            if(showingStepList.length){
              displayFilter(false);
            }
            updateContext();
          });
          scope.$on("$destroy", watcher);
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

        function detectConditionalShowFilter() {
          if(showingStepList.length){
            var filterId = _.first(showingStepList).filterCondition.filterId;
            if(_.isObject(optionsData) && optionsData.showOnSpecificID){
              internalDependencyShowHandler(filterId, optionsData.showOnSpecificID);
            }
          }
        }

        function displayFilter(show) {
          var displayValue = show ? "initial" : "none";
          element.closest(".filterContainer").css("display",displayValue);
          if (show) {
            detectConditionalTitle();
          }
          else {
            scope.selected.options = [];
            if (scope.conditionalFilter) {
              cleanLocalFilterSelections();
            }
            emitSelectedOption();
          }
        }

        function cleanLocalFilterSelections() {
          var localFilterSelectedOptions = getLocalFilterSelectedOptions();
          _.each(scope.options, function (option) {
            var optionIndex = _.findIndex(localFilterSelectedOptions, function (localSelected) {
              return localSelected.optionId === option.optionId;
            });

            if(optionIndex !== -1){
              localFilterSelectedOptions.splice(optionIndex, 1);
            }
          });
          setLocalFilterSelectedOptions(localFilterSelectedOptions);
        }

        function detectConditionalTitle() {
          if(scope.conditionalFilter && !scope.parentFilterOption){
            var filterId = _.first(showingStepList).filterCondition.filterId;
            var parentFilter = FiltersService.getFilterById(filterId, scope.filtersRootScope);
            if(!parentFilter){
              return;
            }

            var parentOptions = parentFilter.filter.processedOptions;
            var dependantOption = _.find(parentOptions, function (option) {
              var optionID = _.isUndefined(option.optionId) ? option.id : option.optionId;
              return optionID === optionsData.showOnSpecificID;
            });

            if(!dependantOption){
              return;
            }
            scope.parentFilterOption = dependantOption.lang;
          }
        }

        function getLocalFilterSelectedOptions() {
          var filterInformation = FiltersService.getFilterById(scope.filterInformation.filter.id, scope.filtersRootScope);
          var selectedOptions = filterInformation.filter.selectedOptions;
          return selectedOptions ? selectedOptions : [];
        }

        function setLocalFilterSelectedOptions(selectedOptions) {
          var filterInformation = FiltersService.getFilterById(scope.filterInformation.filter.id, scope.filtersRootScope);
          filterInformation.filter.selectedOptions = selectedOptions;
        }

        function loadContext() {
          if(!scope.additionalParameters || !scope.additionalParameters.filtersContext) {return;}

          var context = FiltersService.loadContext(scope.additionalParameters.filtersContext);
          var filterID = scope.filterInformation.filter.id;
          var savedFilterModel = context.filtersModel ? context.filtersModel[filterID] : undefined;

          if(!savedFilterModel){return;}

          var selectedOptions = context.filtersModel[filterID].options;

          if(_.isArray(selectedOptions) && !_.isEmpty(selectedOptions)){
            scope.selected.options = selectedOptions;
            emitSelectedOption(true);
          }
        }

        function updateContext() {
          var context = FiltersService.loadContext(scope.additionalParameters.filtersContext);
          var filterID = scope.filterInformation.filter.id;
          context.filtersModel = context.filtersModel ? context.filtersModel : {};
          context.filtersModel[filterID] = {};

          if(_.isEmpty(scope.selected.options)){
            delete context.filtersModel[filterID];
          }
          else{
            var options;
            if(scope.conditionalFilter){
              options = _.map(getLocalFilterSelectedOptions(), _.property("optionId"))
            }
            else{
               options = scope.selected.options;
            }
            context.filtersModel[filterID].options = options;
          }

          FiltersService.saveContext(scope.additionalParameters.filtersContext, context);
        }

        detectConditionalShowFilter();
        detectConditionalTitle();
        loadOptions();
        watchRender();
        watchCleanFilter();
      }
    };
  }
})();
