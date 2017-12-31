(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('dropdownFilter', dropdownFilter);

  function dropdownFilter(FieldsService, FiltersService, $rootScope, $timeout, translateFilter, unicodeFilter, toastr,
                          idSearchFilter, $q) {
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
        scope.loadOptions = loadOptions;
        scope.options = [];
        scope.loadedOptions = false;


        scope.detectConditionalTitle = detectConditionalTitle;

        var selectionState = "selectAll";

        function loadOptions() {
          return $q(function (resolve) {
            if (!optionsData || scope.loadedOptions) {
              resolve();
              return;
            }

            scope.options = [];

            switch (optionsData.type) {
              case "static_options":
                staticOptionsHandler(resolve);
                break;
              case "dynamic_options":
                dynamicOptionsHandler(resolve);
                break;
              case "filter_options":
                filterOptionsHandler(resolve);
            }
          }).finally(function () {
            scope.loadedOptions = true;
          });
        }

        function staticOptionsHandler(resolve){
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
          resolve();
        }

        function dynamicOptionsHandler(resolve) {
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
              resolve();
            });
        }

        function filterOptionsHandler(resolve) {
          var parentIdName = scope.filterInformation.filter.options.parentIDName;
          var parentId = scope.filterInformation.filter.options.showOnSpecificID;

          var parentData = {
            propertyFilter: scope.filterInformation.filter.options.parentFilterInformation,
            selectedFilterOptions: [{optionId: parentId}]
          };

          FiltersService.loadFiltersOptions(scope.filterInformation.filter.id, [parentData]).then(function (options) {
            var filterOptions = _.filter(options, function (option) {
              return option.data2 === "true" || option.parentInfo[parentIdName] === parentId;
            });

            scope.filterInformation.propertyField.dropdownOptionGroup = {
              dropdownOptionList: filterOptions,
              translationManagement: scope.options.translationManagement ? scope.options.translationManagement : "TOTAL"
            };

            staticOptionsHandler(resolve);
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
              var synchronizedOption = _.pick(option, "optionId", "tmplTranslate", "lang", "parentInfo");
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
            scope.selected.options = _.map(scope.options, _.property("optionId"));
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

          var parentIdName = scope.filterInformation.filter.options.parentIDName;
          var parentId = scope.filterInformation.filter.options.showOnSpecificID;

          if(!savedFilterModel){return;}

          var selectedOptions = _.filter(context.filtersModel[filterID].options, function (option) {
            return scope.conditionalFilter ? option.parentInfo[parentIdName] === parentId : true;
          });

          scope.options = selectedOptions;

          if(scope.conditionalFilter){
            var filterData = FiltersService.getFilterById(filterID, scope.filtersRootScope);
            filterData.filter.processedOptions = context.filtersModel[filterID].options;
          }
          else{
            scope.filterInformation.filter.processedOptions = selectedOptions;
          }

          if(_.isArray(selectedOptions) && !_.isEmpty(selectedOptions)){
            scope.selected.options = _.map(selectedOptions, _.property("optionId"));
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

          if(_.isEmpty(scope.selected.options)){
            delete context.filtersModel[filterID];
          }
          else{
            var options;
            if(scope.conditionalFilter){
              options = getLocalFilterSelectedOptions();
            }
            else{
              options = _.map(scope.selected.options, function (optionId) {
                var option = idSearchFilter(scope.options, optionId, "", "optionId");
                return _.pick(option, "optionId", "tmplTranslate", "lang", "parentInfo");
              });
            }

            context.filtersModel[filterID].options = options;
          }

          FiltersService.saveContext(scope.additionalParameters.filtersContext, context);
        }

        loadContext();
        detectConditionalShowFilter();
        detectConditionalTitle();
        watchRender();
        watchCleanFilter();
      }
    };
  }
})();
