(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .controller('MainController', MainController);

  /** @ngInject */
  function MainController($mdSidenav, $mdMedia, $scope, $mdDialog, $document, $location, $window, SessionService, LocaleService,
                          BlogLinks, $rootScope, $route, AppVersion, $translate, CurrencyService, IpInfoService,
                          PreferredSettingsService, LOCALES, $mdMenu, localStorageService) {
    var vm = this;

    vm.toggleMenu = toggleMenu;
    vm.showToolbars = !$route.current.hideToolbars;

    //Menu elements handlers
    vm.launchLoginDialog = launchLoginDialog;
    vm.launchRegisterDialog = launchRegisterDialog;
    vm.isAuthenticated = SessionService.isAuthenticated;
    vm.isAdmin = isAdmin;
    vm.launchPrivacyStatementDialog = launchPrivacyStatementDialog;
    vm.logout = SessionService.logout;
    vm.goTo = goTo;
    vm.redirectAd = redirectAd;
    vm.goUp = goUp;
    vm.blogRedirection = blogRedirection;
    vm.getCurrentUserIdentifier = getCurrentUserIdentifier;

    loadGlobalPreferredSettings();

    $scope.$watch(function() { return $mdMedia('sm') || $mdMedia('xs'); }, function(small) {
      vm.screenIsSmall = small;
    });

    function getCurrentAppVersion() {
      vm.appVersion = AppVersion;
    }

    function goUp() {
      if ($window.navigator.userAgent.match(/(iPod|iPhone|iPad|Android)/)) {
        $window.scrollTo(0,0);
      }else{
        angular.element("#mainContainer").animate({ scrollTop: 0 }, "slow");
      }
    }

    function toggleMenu() {
      $mdSidenav('left').toggle();
    }

    function getCurrentUserIdentifier() {
      var currentUser = SessionService.getCurrentUser();
      return currentUser.firstName ? currentUser.firstName : currentUser.email;
    }

    function launchLoginDialog(ev) {
      var callback = function () {
        $location.path("/profile/my-profile");
        // remove custom backdrop class
      };

      var options = {
        allowClose: true,
        targetEvent: ev,
        clickOutsideToClose: true,
        callback: callback
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
      angular.element("#mainContainer").animate({ scrollTop: 0 }, "slow");

      if(closeMenu){
        toggleMenu();
        $mdMenu.hide();
      }
    }

    function redirectAd() {

      // console.log($location.host() + '/hc-admin');
      $window.open('http:///www.hallocasa.com:64645/hc-admin', '_blank');
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

    /**
      **  Query the back system for ip of machine requester which answer with his respective
      **  country and location details. Next, the system request the hallocasa back with that country in
      **  order to know currency and language preferred which it will load in system
    **/
    function loadGlobalPreferredSettings() {
      //If the user already selected a language and currency, then prevent the application to load a new one
      var selectedLanguage = localStorageService.get("SelectedUserLanguage");
      var selectedCurrency = localStorageService.get("SelectedUserCurrency");

      if(selectedLanguage && selectedCurrency){return;}

      var locationFound;
      IpInfoService.getLocation()
        .then(function(location){
          locationFound = location;
          return PreferredSettingsService.getPreferredSettings();
        })
        .then(function(preferredSettings){
          var settingToUse = searchByCountryCode(preferredSettings,locationFound.country);

          if(!selectedCurrency){
            var currencyToUse = settingToUse ? settingToUse.firstCurrency : settingToUse;
            vm.currentCurrrency = currencyToUse;
            CurrencyService.setCurrentCurrency(currencyToUse);
          }

          if(!selectedLanguage){
            var localeToUse = settingToUse ? settingToUse.locale : LOCALES.defaultLocale;
            $translate.use(localeToUse);
          }
        });
    }

    function searchByCountryCode(preferredSettings, countryCode){
      return _.find(preferredSettings, function(preferredSetting){
        return preferredSetting.countryCode === countryCode;
      });
    }

    function isAdmin(){
      return true;
    }

    toolbarsHideHandler();
    detectRedirectRoute();
    getCurrentAppVersion();
  }
})();
