(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('linkTextField', linkTextField);

  function linkTextField() {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/link-text/link-text-field.html",
      scope: {
        fieldInformation: "="
      },
      link: function () {

      }
    };
  }
})();
