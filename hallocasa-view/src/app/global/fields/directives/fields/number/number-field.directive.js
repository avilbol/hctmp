(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('numberField', numberField);

  function numberField() {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/number/number-field.html",
      scope: {
      },
      link: function () {

      }
    };
  }
})();
