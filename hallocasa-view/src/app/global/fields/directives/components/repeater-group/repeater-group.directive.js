(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('repeaterGroup', repeaterGroup);

  function repeaterGroup() {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/repeater-group/repeater-group.html",
      scope: {
      },
      link: function () {

      }
    };
  }
})();