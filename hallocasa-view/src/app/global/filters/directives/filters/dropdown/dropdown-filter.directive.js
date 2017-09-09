(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('dropdownFilter', dropdownFilter);

  function dropdownFilter(FieldsService, $rootScope, $timeout) {
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
        scope.selectAll = selectAll;
        scope.selectedOptions = [];
        scope.selectAllButtonTranstationKey = "placeholder.selectAll";

        function loadOptions() {
          if(!optionsData){
            return;
          }
          switch(optionsData.type){
            case "static_options":
              staticOptionsHandler();
              break;
          }
        }

        function staticOptionsHandler(){
          var staticOptionsGroup = scope.filterInformation.propertyField.dropdownOptionGroup;
          var optionsList = staticOptionsGroup.dropdownOptionList;
          scope.options = FieldsService.processOptions(optionsList, staticOptionsGroup.translationManagement);
        }

        function emitSelectedOption(selectedOptions) {
          var selectedFilterOptions = _.isArray(selectedOptions) ?
            _.map(selectedOptions, _.partial(_.pick, _, "optionId")) :
            [_.pick(selectedOptions, "optionId")];

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
          if(scope.selectedOptions.length === scope.options.length){
            scope.selectedOptions = [];
            scope.selectAllButtonTranstationKey = "placeholder.selectAll";
          }
          else{
            scope.selectedOptions = scope.options;
            scope.selectAllButtonTranstationKey = "placeholder.deselectAll";
          }
          emitSelectedOption(scope.selectedOptions);
        }

        loadOptions();
        watchRender();
      }
    };
  }
})();
