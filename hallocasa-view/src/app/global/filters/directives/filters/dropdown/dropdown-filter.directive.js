(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('dropdownFilter', dropdownFilter);

  function dropdownFilter(FieldsService) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/filters/directives/filters/dropdown/dropdown-filter.html",
      scope: {
        filtersScope: "=?",
        filterInformation: "=",
        filtersRootScope: "=?"
      },
      link: function (scope) {
        var optionsData = scope.filterInformation.filter.options;

        scope.fieldName = scope.$id;
        scope.title = scope.filterInformation.filter.usePropertyField ?
          scope.filterInformation.propertyField.lang : scope.filterInformation.filter.lang;

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

        loadOptions();
      }
    };
  }
})();
