(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .controller('MainController', MainController);

  /** @ngInject */
  function MainController($mdSidenav, $mdMedia, $scope, $mdDialog, $document, $location, SessionService, LocaleService,
                          BlogLinks, $rootScope, $route, AppVersion) {
    var vm = this;

    vm.toggleMenu = toggleMenu;
    vm.showToolbars = !$route.current.hideToolbars;

    //Menu elements handlers
    vm.launchLoginDialog = launchLoginDialog;
    vm.launchRegisterDialog = launchRegisterDialog;
    vm.isAuthenticated = SessionService.isAuthenticated;
    vm.launchPrivacyStatementDialog = launchPrivacyStatementDialog;
    vm.logout = SessionService.logout;
    vm.goTo = goTo;
    vm.goUp = goUp;
    vm.blogRedirection = blogRedirection;
    vm.getCurrentUserIdentifier = getCurrentUserIdentifier;

    $scope.$watch(function() { return $mdMedia('sm') || $mdMedia('xs'); }, function(small) {
      vm.screenIsSmall = small;
    });

    function getCurrentAppVersion() {
      vm.appVersion = AppVersion;
    }

    function goUp() {
      angular.element("#mainContainer").animate({ scrollTop: 0 }, "slow");
    }

    function toggleMenu() {
      $mdSidenav('left').toggle();
    }

    function getCurrentUserIdentifier() {
      var currentUser = SessionService.getCurrentUser();
      return currentUser.firstName ? currentUser.firstName : currentUser.email;
    }

    function launchLoginDialog(ev) {
      var options = {
        allowClose: true,
        targetEvent: ev,
        clickOutsideToClose: true
      };
      SessionService.launchLoginDialog(options);
    }

    function launchPasswordRecoveryDialog() {
      var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'));
      $mdDialog.show({
        controller: "PasswordRecoveryController",
        controllerAs: "vm",
        templateUrl: 'app/session/password-recovery/password-recovery.html',
        parent: $document.body,
        clickOutsideToClose: false,
        fullscreen: useFullScreen
      });
    }

    function launchUserActivationDialog() {
      var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'));
      $mdDialog.show({
        controller: "UserActivationController",
        controllerAs: "vm",
        templateUrl: 'app/session/user-activation/user-activation.html',
        parent: $document.body,
        clickOutsideToClose: false,
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
        allowClose:true,
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
      return BlogLinks[currentLanguage][section];
    }

    function toolbarsHideHandler() {
      var routeListener = $rootScope.$on('$routeChangeStart', function (event, toState) {
        vm.showToolbars = !toState.hideToolbars;
      });

      $rootScope.$on('$destroy',routeListener);
    }

    function detectRedirectRoute() {
      if($route.current.isPassworRecovery){
        launchPasswordRecoveryDialog();
      }
      if($route.current.isUserActivarion){
        launchUserActivationDialog();
      }
    }

    toolbarsHideHandler();
    detectRedirectRoute();
    getCurrentAppVersion();
  }
})();
