(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('textLimiter', textLimiter);

  function textLimiter($mdMedia) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/text-limiter/text-limiter.html",
      scope: {
        text: "=",
        mobileLimit: "=?",
        tabletLimit: "=?",
        desktopLimit: "=?"
      },
      link: function (scope) {
        scope.mobileLimit = angular.isNumber(scope.mobileLimit) ? scope.mobileLimit : 50;
        scope.tabletLimit = angular.isNumber(scope.tabletLimit) ? scope.tabletLimit : 100;
        scope.desktopLimit = angular.isNumber(scope.desktopLimit) ? scope.desktopLimit : 200;

        scope.$watchCollection("[mobileLimit, tabletLimit, mobileLimit]", setLimit);
        scope.$watch(function() { return $mdMedia('xs'); }, setLimit);
        scope.$watch(function() { return $mdMedia('sm'); }, setLimit);
        scope.$watch(function() { return $mdMedia('gt-sm'); }, setLimit);

        function setLimit() {
          if($mdMedia('xs')){ //Mobile
            scope.limit = scope.mobileLimit;
          }
          if($mdMedia('sm')){ //Tablet
            scope.limit = scope.tabletLimit;
          }
          if($mdMedia('gt-sm')){ //Desktop
            scope.limit = scope.desktopLimit;
          }
        }
      }
    };
  }
})();
