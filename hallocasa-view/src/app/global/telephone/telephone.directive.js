(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('telephone', telephone);

  function telephone(LocationService) {
    return {
      restrict: 'EA',
      require: 'ngModel',
      templateUrl: "app/global/telephone/telephone.html",
      scope: {
        ngModel: '=',
        label: '@',
        isRequired: '=?'
      },
      link: function (scope, element) {

        scope.ngModel = scope.ngModel ? scope.ngModel : {};
        scope.isRequired = scope.isRequired ? scope.isRequired : false;
        scope.clearSearchTerm = clearSearchTerm;


        LocationService.getTelephonePrefixes()
          .then(function (prefixes) {
            scope.prefixList = prefixes;
          });

        scope.$watchCollection("[ngModel.telephonePrefix, ngModel.telephoneNumber]", function () {
          if(!scope.ngModel){
            scope.ngModel = {};
          }

          scope.validTelephone = (scope.ngModel.telephonePrefix && !scope.ngModel.telephoneNumber) || (!scope.ngModel.telephonePrefix && scope.ngModel.telephoneNumber);
        });

        function clearSearchTerm() {
          scope.searchTerm = '';
        }

        element.find('input').on('keydown', function(ev) {
          ev.stopPropagation();
        });

      }
    };
  }
})();
