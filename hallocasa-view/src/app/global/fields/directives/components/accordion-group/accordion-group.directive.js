(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('accordionGroup', accordionGroup);

  function accordionGroup() {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/components/accordion-group/accordion-group.html",
      scope: {
      },
      link: function () {

      }
    };
  }
})();
