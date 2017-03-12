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
        fieldInformation: "=",
        form: "=?"
      },
      link: function (scope) {
        scope.fieldName = scope.$id;

        function applyValidations() {
          if (scope.fieldInformation.validations) {
            scope.required = scope.fieldInformation.validations.includes("required");
            var noPastDate = scope.fieldInformation.validations.includes("noPastDate");
            if (noPastDate) {
              var currentDate = new Date();
              scope.dateFilter = function (date) {
                return date >= currentDate;
              }
            }
          }
        }

        applyValidations();
      }
    };
  }
})();
