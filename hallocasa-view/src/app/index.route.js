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
        controllerAs: 'vm',
        reloadOnSearch: false
      })
      .when('/pages/:blogPage', {
        templateUrl: 'app/main/blog-redirection/blog-redirection.html',
        controller: 'BlogRedirectionController',
        controllerAs: 'vm',
        reloadOnSearch: false
      })
      .when('/404', {
        templateUrl: 'app/main/common-pages/common-pages.html',
        controller: 'CommonPagesController',
        controllerAs: 'vm',
        hideToolbars: true,
        reloadOnSearch: false
      })
      .when('/forbidden', {
        templateUrl: 'app/main/common-pages/common-pages.html',
        controller: 'CommonPagesController',
        controllerAs: 'vm',
        hideToolbars: true,
        reloadOnSearch: false
      })
      .when('/coming-soon', {
        templateUrl: 'app/main/common-pages/common-pages.html',
        controller: 'CommonPagesController',
        controllerAs: 'vm',
        hideToolbars: true,
        reloadOnSearch: false
      })
      .when('/password_recovery', {
        templateUrl: 'app/main/landing/landing.html',
        controller: 'LandingController',
        controllerAs: 'vm',
        isPassworRecovery: true,
        reloadOnSearch: false
      })
      .when('/user_activation', {
        templateUrl: 'app/main/landing/landing.html',
        controller: 'LandingController',
        controllerAs: 'vm',
        isUserActivarion: true,
        reloadOnSearch: false
      })
      .otherwise({
        redirectTo: '/404'
      });

    $locationProvider.html5Mode(true);
  }

})();
