angular
  .module('HalloCasaAdmin')
  .config(routesConfig);

/** @ngInject */
function routesConfig($routeProvider, $locationProvider) {
  // $locationProvider.html5Mode(true).hashPrefix('!');
  // $urlRouterProvider.otherwise('/');

  $routeProvider
    .when('/', {
      templateUrl: 'app/main/main.html',
      controller: 'MainController',
      controllerAs: 'vm'
    })
    .when('/traductions', {
      templateUrl: 'app/translation/manageTranslations.html',
      controller: 'ManageTranslationsController',
      controllerAs: 'vm'
    })
    .otherwise({
      redirectTo: '/404'
    });

  $locationProvider.html5Mode(true);
}
