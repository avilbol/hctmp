(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('dateField', dateField);

  function dateField() {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/date/date-field.html",
      scope: {
        fieldScope: "=?",
        fieldInformation: "="
      },
      link: function () {

      }
    };
  }
})();
