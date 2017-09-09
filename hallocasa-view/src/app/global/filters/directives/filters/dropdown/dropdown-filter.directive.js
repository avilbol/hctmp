(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('dropdownFilter', dropdownFilter);

  function dropdownFilter(FieldsService, $rootScope, $timeout, translateFilter, unicodeFilter) {
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

        scope.fieldName = scope.$id;
        scope.title = scope.filterInformation.filter.usePropertyField ?
          scope.filterInformation.propertyField.lang : scope.filterInformation.filter.lang;
        scope.emitSelectedOption = emitSelectedOption;
        scope.search = {};
        scope.selected = {options: []};
        scope.selectAll = selectAll;
        scope.selectAllButtonTranstationKey = "placeholder.selectAll";

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
          var staticOptionsGroup, optionsList;
          if(scope.filterInformation.filter.usePropertyField){
            staticOptionsGroup= scope.filterInformation.propertyField.dropdownOptionGroup;
            optionsList = staticOptionsGroup.dropdownOptionList;
          }
          else{
            staticOptionsGroup= scope.filterInformation.filter.dropdownOptionGroup;
            optionsList = staticOptionsGroup.dropdownOptionList;
          }

          scope.options = FieldsService.processOptions(optionsList, staticOptionsGroup.translationManagement);
        }

        function dynamicOptionsHandler() {
          var serviceId = optionsData.serviceId;
          var translationManagement = optionsData.translationManagement ? optionsData.translationManagement : "NONE";
          FieldsService.loadOptionsByServiceId(serviceId)
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
            });
        }

        function emitSelectedOption(selectedOptions) {
          var selectedFilterOptions;
          if(_.isArray(selectedOptions)){
            selectedFilterOptions =  _.map(selectedOptions, _.partial(_.pick, _, "optionId"))
          }
          else{
            var option = _.pick(selectedOptions, "optionId");
            selectedFilterOptions = _.isEmpty(option) ? [] : [option];
          }

          var selectionPayload = {
            propertyFilter: scope.filterInformation,
            selectedFilterOptions: selectedFilterOptions
          };
          $rootScope.$broadcast("FilterSystem:filterSelected", selectionPayload);
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
          if(scope.selected.options.length === scope.options.length){
            scope.selected.options = [];
            scope.selectAllButtonTranstationKey = "placeholder.selectAll";
          }
          else{
            scope.selected.options = scope.options;
            scope.selectAllButtonTranstationKey = "placeholder.deselectAll";
          }
          emitSelectedOption(scope.selected.options);
        }

        loadOptions();
        watchRender();
      }
    };
  }
})();
