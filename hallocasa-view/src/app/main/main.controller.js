(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .controller('MainController', MainController);

  /** @ngInject */
  function MainController($mdSidenav, $mdMedia, $scope, $mdDialog, $document, $location, $window, SessionService, LocaleService,
                          BlogLinks, $rootScope, $route, AppVersion, $translate, CurrencyService, IpInfoService,
                          PreferredSettingsService, LOCALES, $mdMenu) {
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

    var updateInfoUser = $rootScope.$on('updateInfoUser', function() {
      var currentUser = SessionService.getCurrentUser();
      return currentUser.firstName ? currentUser.firstName : currentUser.email;
    });

    $rootScope.$on('$destroy', updateInfoUser);

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
      **  order to know currency and language preferredm which it will load in system
    **/
    function loadGlobalPreferredSettings() {
      var locationFound;
      IpInfoService.getLocation()
        .then(function(location){
          locationFound = location;
          return PreferredSettingsService.getPreferredSettings();
        })
        .then(function(preferredSettings){
          var settingToUse = searchByCountryCode(preferredSettings,locationFound.countryCode);
          var currencyToUse = settingToUse ? settingToUse.firstCurrency : settingToUse;
          var localeToUse = settingToUse ? settingToUse.locale : LOCALES.defaultLocale;
          vm.currentCurrrency = currencyToUse;
          CurrencyService.setCurrentCurrency(currencyToUse);
          $translate.use(localeToUse);
        })
        .catch(function(){
          CurrencyService.setCurrentCurrency({"id":3, "abbreviation":"USD"});
          $translate.use(LOCALES.defaultLocale);
        });
    }

    function searchByCountryCode(preferredSettings, countryCode){
      return _.find(preferredSettings, function(preferredSetting){
        return preferredSetting.countryCode == countryCode;
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
