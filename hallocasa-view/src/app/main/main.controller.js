(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .controller('MainController', MainController);

  /** @ngInject */
  function MainController($mdSidenav, $mdMedia, $scope, $mdDialog, $document, $location, SessionService, $auth,
                          LocaleService, BlogLinks, $window, $rootScope, $route) {
    var vm = this;

    vm.toggleMenu = toggleMenu;
    vm.showToolbars = !$route.current.hideToolbars;

    //Menu elements handlers
    vm.launchLoginDialog = launchLoginDialog;
    vm.launchRegisterDialog = launchRegisterDialog;
    vm.isAuthenticated = isAuthenticated;
    vm.launchPrivacyStatementDialog = launchPrivacyStatementDialog;
    vm.logout = SessionService.logout;
    vm.goTo = goTo;
    vm.blogRedirection = blogRedirection;

    $scope.$watch(function() { return $mdMedia('sm') || $mdMedia('xs'); }, function(small) {
      vm.screenIsSmall = small;
    });

    function toggleMenu() {
      $mdSidenav('left').toggle();
    }

    function isAuthenticated() {
      return $auth.isAuthenticated();
    }

    function launchLoginDialog(ev) {
      var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'));
      $mdDialog.show({
        controller: "LoginController",
        controllerAs: "vm",
        templateUrl: 'app/session/login/login.html',
        parent: $document.body,
        locals: {
          description: "",
          allowClose: true
        },
        targetEvent: ev,
        clickOutsideToClose:true,
        fullscreen: useFullScreen
      });
    }

    function launchPrivacyStatementDialog(ev) {
      var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'));
      $mdDialog.show({
        controller: "PrivacyStatementController",
        controllerAs: "vm",
        templateUrl: 'app/main/privacy-statement/privacy-statement.html',
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

    function goTo(url, closeMenu) {
      $location.url(url);
      if(closeMenu){
        toggleMenu();
      }
    }

    function blogRedirection(section) {
      var currentLanguage = LocaleService.getLocaleDisplayName();
      $window.open( BlogLinks[currentLanguage][section], '_blank');
    }

    function toolbarsHideHandler() {
      var routeListener = $rootScope.$on('$routeChangeStart', function (event, toState) {
        vm.showToolbars = !toState.hideToolbars;
      });

      $rootScope.$on('$destroy',routeListener);
    }

    toolbarsHideHandler();
  }
})();
