(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('dateMinorThan', dateMinorThan);

  function dateMinorThan(moment) {
    return {
      require: 'ngModel',
      restrict: 'A',
      scope: {
        validationParams: "=?"
      },
      link: function (scope, elm, attrs, ctrl) {
        ctrl.$validators.dateMinorThan = function() {
          if(!scope.validationParams ||!scope.validationParams.dateValidation ||
            !scope.validationParams.dateValidation.minorDate || !scope.validationParams.dateValidation.greaterDate){
            return true;
          }

          var validationsParams = scope.validationParams.dateValidation;
          var minorDate = moment(validationsParams.minorDate, validationsParams.dateFormat);
          var greaterDate = moment(validationsParams.greaterDate, validationsParams.dateFormat);

          scope.validationParams.dateValidation.greaterDateFormated = greaterDate.format(validationsParams.dateFormat);
          return minorDate.isSameOrBefore(greaterDate);
        };
      }
    };
  }
})();
