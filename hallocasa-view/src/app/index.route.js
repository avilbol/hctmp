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
      .when('/404', {
        templateUrl: 'app/main/common-pages/common-pages.html',
        controller: 'CommonPagesController',
        controllerAs: 'vm',
        hideToolbars: true
      })
      .when('/forbidden', {
        templateUrl: 'app/main/common-pages/common-pages.html',
        controller: 'CommonPagesController',
        controllerAs: 'vm',
        hideToolbars: true
      })
      .when('/coming-soon', {
        templateUrl: 'app/main/common-pages/common-pages.html',
        controller: 'CommonPagesController',
        controllerAs: 'vm',
        hideToolbars: true
      })
      .otherwise({
        redirectTo: '/404'
      });

    $locationProvider.html5Mode(true);
  }

})();
