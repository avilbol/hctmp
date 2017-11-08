(function () {
  'use strict';

  angular
    .module('HalloCasaAdmin')
    .controller('IndexController', IndexController);

  /** @ngInject */
  function IndexController($scope, $location, $window, $rootScope, $route, $mdSidenav, AppVersion){
    var vm = this;

    vm.toggleMenu = toggleMenu;
    vm.goTo = goTo;
    vm.showToolbars = true;

    function goTo(url, closeMenu){
      $location.url(url);
      // angular.element('#indexContainer').animate({ scrollTop: 0 }, 'slow');
      if(closeMenu){
        toggleMenu();
      }
    }

    function toggleMenu() {
      $mdSidenav('left').toggle();
    }

    function getCurrentAppVersion(){
      vm.appVersion = AppVersion;
    }

    getCurrentAppVersion();
  }
})();