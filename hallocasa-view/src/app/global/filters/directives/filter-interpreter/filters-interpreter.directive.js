(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .directive('filtersInterpreter', filtersInterpreter);

  function filtersInterpreter($timeout) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/filters/directives/filter-interpreter/filters-interpreter.html",
      scope: {
        filtersList: "=?",
        filtersRootScope: "=?",
        filtersRendered: "=?",
        additionalParameters: "=?"
      },
      link: function (scope) {
        /*
         * This code will run after
         * templateUrl has been loaded, cloned
         * and transformed by directives.
         * and properly rendered by the browser
         * http://lorenzmerdian.blogspot.com.co/2013/03/how-to-handle-dom-updates-in-angularjs.html
         * */
        function watchRender() {
          $timeout(function () {
            $timeout(function () {
              scope.filtersRendered = true;
            }, 0);
          }, 0);
        }
        watchRender();

      }
    };
  }
})();
