(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .config(config);

  /** @ngInject */
  function config($logProvider, toastrConfig, $translateProvider, tmhDynamicLocaleProvider, LOCALES, $mdIconProvider,
                  localStorageServiceProvider, paginationTemplateProvider, $compileProvider, $httpProvider, $authProvider,
                  $mdThemingProvider, uiGmapGoogleMapApiProvider) {

    $httpProvider.interceptors.push('AppAuthTokenInterceptor');

    // Pagination template
    paginationTemplateProvider.setPath('app/global/pagination/pagination.html');
    // Enable log
    $logProvider.debugEnabled(true);

    //LocalStorage prefix
    localStorageServiceProvider.setPrefix('HalloCasa');

    //Theme configuration
    var HalloCasaTheme = $mdThemingProvider.extendPalette('indigo', {
      '900': '#002d45'
    });

    $mdThemingProvider.definePalette('HalloCasaTheme', HalloCasaTheme);

    $mdThemingProvider.theme('default')
      .primaryPalette('HalloCasaTheme', {'default': '900'});

    // Set options third-party lib
    toastrConfig.allowHtml = true;
    toastrConfig.timeOut = 7000;
    toastrConfig.positionClass = 'toast-bottom-center';
    toastrConfig.progressBar = true;

    // OAuth2 config
    $authProvider.loginUrl = '/security/token';
    $authProvider.tokenHeader = 'O-Auth-Token';
    $authProvider.tokenType = '';

    // Show warnings in the developer console, regarding forgotten IDs in translations
    $translateProvider.useMissingTranslationHandlerLog();

    // Default sanitize value strategy
    $translateProvider.useSanitizeValueStrategy('escape');

    $translateProvider.useStaticFilesLoader({
      prefix: 'assets/locales/locale-',// path to translations files
      suffix: '.json'// suffix, currently- extension of the translations
    });
    $translateProvider.preferredLanguage(LOCALES.preferredLocale);// is applied on first load
    $translateProvider.useLocalStorage();// saves selected language to localStorage

    tmhDynamicLocaleProvider.localeLocationPattern('bower_components/angular-i18n/angular-locale_{{locale}}.js');

    //Icons configuration
    $mdIconProvider
      .icon("flipboard", "assets/icons/Logomark_DIGITAL_White_500X500.svg", 24);

    //URL Sanitization
    $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|skype|chrome-extension):/);

    //Gmaps configurations
    uiGmapGoogleMapApiProvider.configure({
      key: 'AIzaSyChfY95Vk-MYNJnW4Wik-tqdphVcruqb7Q',
      v: '3.25'
    });

  }

})();
