(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .config(routeConfig);

  function routeConfig($routeProvider) {
    $routeProvider
      .when('/property/browser', {
        templateUrl: 'app/property/public/public-property.html',
        controller: 'PublicPropertyController',
        controllerAs: 'vm'
      });
  }

})();
