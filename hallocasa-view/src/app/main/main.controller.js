(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .controller('MainController', MainController);

  /** @ngInject */
  function MainController($mdSidenav, $mdMedia, $scope, $mdDialog, $document) {
    var vm = this;

    vm.toggleMenu = toggleMenu;

    //Menu elements handlers
    vm.launchLoginDialog = launchLoginDialog;
    vm.launchRegisterDialog = launchRegisterDialog;

    $scope.$watch(function() { return $mdMedia('sm') || $mdMedia('xs'); }, function(small) {
      vm.screenIsSmall = small;
    });

    function toggleMenu() {
      $mdSidenav('left').toggle();
    }

    function launchLoginDialog(ev) {
      var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'));
      $mdDialog.show({
        controller: "LoginController",
        controllerAs: "vm",
        templateUrl: 'app/session/login/login.html',
        parent: $document.body,
        targetEvent: ev,
        clickOutsideToClose:true,
        fullscreen: useFullScreen
      });
    }

    function launchRegisterDialog(ev) {
      var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'));
      $mdDialog.show({
        controller: "RegisterController",
        controllerAs: "vm",
        templateUrl: 'app/session/register/register.html',
        parent: $document.body,
        targetEvent: ev,
        clickOutsideToClose:true,
        fullscreen: useFullScreen
      });
    }
  }
})();
