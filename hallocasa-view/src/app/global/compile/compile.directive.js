(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .directive('compile', compile);

  function compile($compile) {
    return {
      restrict: 'EA',
      link: function(scope, element, attrs) {
        scope.$watch(
          function(scope) {
            return scope.$eval(attrs.compile);
          },
          function(value) {
            element.html(value);
            $compile(element.contents())(scope);
          }
        );
      }
    };
  }
})();
