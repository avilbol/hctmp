(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('loading', loading);

  function loading($http) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/loading/loading.html",
      scope: { },
      link: function (scope) {
        scope.isLoading = function () {
          return $http.pendingRequests.length > 0;
        };

        scope.$watch(scope.isLoading, function (loading) {
          scope.loading = loading;
        });
      }
    };
  }
})();
