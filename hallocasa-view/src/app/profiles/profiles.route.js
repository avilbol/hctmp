(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .config(routeConfig);

  function routeConfig($routeProvider) {
    $routeProvider
      .when('/profile', {
        templateUrl: 'app/profiles/view/view-profile.html',
        controller: 'ViewProfileController',
        controllerAs: 'vm',
        // requiredLogin: true, // check session in ViewProfileController 
        reloadOnSearch: false
      })
      .when('/profile/my-profile', {
        templateUrl: 'app/profiles/my-profile/my-profile.html',
        controller: 'myProfileController',
        controllerAs: 'vm',
        reloadOnSearch: false
      })
      .when('/profile/my-profile/edit', {
        templateUrl: 'app/profiles/edit/edit-profile.html',
        controller: 'EditProfileController',
        controllerAs: 'vm',
        reloadOnSearch: false
      })
      .when('/profile/browser', {
        templateUrl: 'app/profiles/public/public-profile.html',
        controller: 'PublicProfileController',
        controllerAs: 'vm',
        reloadOnSearch: false
      });
  }

})();
