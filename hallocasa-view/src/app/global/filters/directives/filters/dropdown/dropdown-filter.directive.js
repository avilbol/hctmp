(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('dropdownFilter', dropdownFilter);

  function dropdownFilter(FieldsService, FiltersService, $rootScope, $timeout, translateFilter, unicodeFilter, toastr, $log) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/filters/directives/filters/dropdown/dropdown-filter.html",
      scope: {
        filtersScope: "=?",
        filterInformation: "=",
        filtersRootScope: "=?"
      },
      link: function (scope, element) {
        var optionsData = scope.filterInformation.filter.options;
        var showingStepList = scope.filterInformation.filter.showingStepList;
        var localFilterSelectedOptions = [];

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
            });
        }

        function setFilterOptions(processedOptions) {
          scope.options = processedOptions;

          if(scope.conditionalFilter){
            var filterInformation = FiltersService.getFilterById(scope.filterInformation.filter.id, scope.filtersRootScope);
            filterInformation.filter.processedOptions = _.isArray(filterInformation.filter.processedOptions) ?
              _.join(filterInformation.filter.processedOptions, processedOptions) : processedOptions;
          }
          else{
            scope.filterInformation.filter.processedOptions = processedOptions;
          }
        }

        function emitSelectedOption() {
          var selectedFilterOptions;
          var selectedOptions = scope.selected.options;

          if(_.isArray(selectedOptions)){
            selectedFilterOptions =  _.map(selectedOptions, _.partial(_.pick, _, "optionId"))
          }
          else{
            var option = _.pick(selectedOptions, "optionId");
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
          emitSelectedOption(scope.selected.options);
        }

        function watchCleanFilter() {
          var watcher = $rootScope.$on("FilterSystem:clearFilters", function () {
            scope.selected.options = [];
            localFilterSelectedOptions = [];
            if(showingStepList.length){
              displayFilter(false);
            }
          });
          scope.$on("$destroy", watcher);
        }

        function internalDependencyShowHandler(filterId, dependentValue){
          if(!scope.conditionalFilter) {
            displayFilter(false);
          }

          var destroyListener = $rootScope.$on("FilterSystem:filterSelected", function (event, filterInformation) {
            if(scope.conditionalFilter && filterInformation.propertyFilter.filter.id === scope.filterInformation.filter.id){
              localFilterSelectedOptions = filterInformation.selectedFilterOptions;
            }
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
          if(!show){
            scope.selected.options = [];
            if(scope.conditionalFilter){
              cleanLocalFilterSelections();
            }
            emitSelectedOption();
          }
        }
        function cleanLocalFilterSelections() {
          _.each(scope.options, function (option) {
            var optionIndex = _.findIndex(localFilterSelectedOptions, function (localSelected) {
              return localSelected.optionId === option.optionId;
            });

            if(optionIndex !== -1){
              localFilterSelectedOptions.splice(optionIndex, 1);
            }
          });

        }

        function detectConditionalTitle() {
          if(scope.conditionalFilter){
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
              $log.warn("No se podido encontrar la opción seleccionada para el filtro "+filterId, parentFilter);
              return;
            }
            scope.parentFilterOption = dependantOption.lang;
          }
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
