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
        controllerAs: 'vm'
      })
      .when('/profile/edit', {
        templateUrl: 'app/profiles/edit/edit-profile.html',
        controller: 'EditProfileController',
        controllerAs: 'vm'
      });
  }

})();
