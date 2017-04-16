(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('infinityScroll', infinityScroll);

  function infinityScroll() {
    return {
      restrict: 'EA',
      scope: {
        container: "@?",
        fetchMore: "&",
        distanceLoad: "@"
      },
      link: function (scope, element) {
        var distanceLoad = scope.distanceLoad ? scope.distanceLoad / 100 : 0.1;
        var waitLoad = false;

        var container = scope.container ? angular.element(scope.container) : element;
        container.off("scroll").scroll(function () {
          var scrollPercentageLeft = 1 - container[0].scrollTop / (container[0].scrollHeight - container[0].clientHeight);

          if(scrollPercentageLeft < distanceLoad && !waitLoad){
            scope.fetchMore();
            waitLoad = true;
          }
        });

        var watcher = scope.$watch(function () {
          return container.height();
        }, function () {
          waitLoad = false;
        });

        scope.$on("$destroy", watcher);

      }
    };
  }
})();
