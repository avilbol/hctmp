(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('telephone', telephone);

  function telephone() {
    return {
      restrict: 'EA',
      require: 'ngModel',
      templateUrl: "app/global/telephone/telephone.html",
      scope: {
        ngModel: '=',
        label: '@',
        isRequired: '=?'
      },
      link: function (scope) {

        scope.ngModel = scope.ngModel ? scope.ngModel : {};
        scope.isRequired = scope.isRequired ? scope.isRequired : false;

        scope.$watchCollection("[ngModel.extension, ngModel.number]", function () {
          if(!scope.ngModel){
            scope.ngModel = {};
          }

          scope.validTelephone = (scope.ngModel.extension && !scope.ngModel.number) || (!scope.ngModel.extension && scope.ngModel.number);
        });

      }
    };
  }
})();
