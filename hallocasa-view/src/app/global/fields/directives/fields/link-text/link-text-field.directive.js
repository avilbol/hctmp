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
        fieldInformation: "=",
        form: "=?",
        readonly: "=?"
      },
      link: function (scope) {
        scope.fieldName = scope.$id;

        function applyValidations() {
          if (scope.fieldInformation.validations) {
            scope.required = scope.fieldInformation.validations.includes("required");
          }
        }

        applyValidations();
      }
    };
  }
})();
