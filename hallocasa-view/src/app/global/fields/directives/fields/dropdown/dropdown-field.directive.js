(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('dropdownField', dropdownField);

  function dropdownField() {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/dropdown/dropdown-field.html",
      scope: {
        fieldScope: "=?",
        fieldInformation: "="
      },
      link: function (scope) {
        if(scope.fieldInformation.dropdownOptionGroup){
          scope.fieldType = {id: "standard_field"};
        }
        else{
          scope.fieldType = {id: "unimplemented_field"};
        }

      }
    };
  }
})();
