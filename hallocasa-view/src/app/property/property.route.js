(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .config(routeConfig);

  function routeConfig($routeProvider) {
    $routeProvider
      .when('/property', {
        templateUrl: 'app/property/view/view-property.html',
        controller: 'ViewPropertyController',
        controllerAs: 'vm',
        reloadOnSearch: false
      })
      .when('/property/browser', {
        templateUrl: 'app/property/public/public-property.html',
        controller: 'PublicPropertyController',
        controllerAs: 'vm',
        reloadOnSearch: false
      });
  }

})();
