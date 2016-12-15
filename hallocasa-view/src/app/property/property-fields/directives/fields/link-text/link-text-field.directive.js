(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .directive('field', Field);

  function Field() {
    return {
      restrict: 'EA',
      templateUrl: "app/property/property-fields/directives/.../.html",
      scope: {
      },
      link: function (scope) {

      }
    };
  }
})();
