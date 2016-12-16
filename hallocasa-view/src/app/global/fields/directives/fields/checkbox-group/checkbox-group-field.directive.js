(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('checkboxGroupField', checkboxGroupField);

  function checkboxGroupField() {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/checkbox-group/checkbox-group-field.html",
      scope: {
      },
      link: function () {

      }
    };
  }
})();
