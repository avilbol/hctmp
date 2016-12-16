(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('imageUploaderField', imageUploaderField);

  function imageUploaderField() {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/image-uploader/image-uploader-field.html",
      scope: {
      },
      link: function () {

      }
    };
  }
})();
