(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .config(routeConfig);

  function routeConfig($routeProvider, $locationProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'app/main/landing/landing.html',
        controller: 'LandingController',
        controllerAs: 'vm'
      })
      .when('/pages/:blogPage', {
        templateUrl: 'app/main/blog-redirection/blog-redirection.html',
        controller: 'BlogRedirectionController',
        controllerAs: 'vm'
      })
      .otherwise({
        redirectTo: '/'
      });

    $locationProvider.html5Mode(true);
  }

})();
