(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('googleMapField', googleMapField);

  function googleMapField() {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/google-map/google-map-field.html",
      scope: {
      },
      link: function () {

      }
    };
  }
})();
