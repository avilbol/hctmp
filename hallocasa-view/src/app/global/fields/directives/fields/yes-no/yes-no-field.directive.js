(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('yesNoField', yesNoField);

  function yesNoField() {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/yes-no/yes-no-field.html",
      scope: {
      },
      link: function () {

      }
    };
  }
})();
