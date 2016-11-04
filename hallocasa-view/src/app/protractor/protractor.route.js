(function() {
  'use strict';

  angular
    .module('HalloCasa.protractor')
    .config(routeConfig);

  function routeConfig($routeProvider) {
    $routeProvider
      .when('/protractor', {
        templateUrl: 'app/protractor/test/test.html',
        controller: 'TestController',
        controllerAs: 'vm'
      });
  }

})();
