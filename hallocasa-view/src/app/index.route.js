(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .config(routeConfig);

  function routeConfig($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'app/main/landing/landing.html',
        controller: 'LandingController',
        controllerAs: 'vm'
      })
      .otherwise({
        redirectTo: '/'
      });
  }

})();
