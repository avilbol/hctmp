(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .config(config)
    .run(run);

  var $filter;
  /** @ngInject */
  function config($logProvider, toastrConfig, $translateProvider, tmhDynamicLocaleProvider, LOCALES, $mdIconProvider,
                  localStorageServiceProvider, paginationTemplateProvider, $compileProvider, $httpProvider, $authProvider,
                  $mdThemingProvider, uiGmapGoogleMapApiProvider, $intercomProvider, INTERCOM_APPID, backend_url,
                  $mdDateLocaleProvider) {

    //Inject interceptors
    $httpProvider.interceptors.push('AppAuthTokenInterceptor');
    $httpProvider.interceptors.push('TokenUnauthorizedInterceptor');

    // Pagination template
    paginationTemplateProvider.setPath('app/global/pagination/pagination.html');
    // Enable log
    $logProvider.debugEnabled(true);

    //LocalStorage prefix
    localStorageServiceProvider.setPrefix('HalloCasa');

    //Theme configuration
    var HalloCasaTheme = $mdThemingProvider.extendPalette('indigo', {
      '50': '#fcfcfc',
      '600': '#07407b',
      '900': '#0c58a6'
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

    $translateProvider.useUrlLoader(backend_url+'/hallocasa-api/locales/translations');
    $translateProvider.useLocalStorage();// saves selected language to localStorage

    tmhDynamicLocaleProvider.localeLocationPattern('http://www.hallocasa.com/resources/js/angular-locale_{{locale}}.js');

    //Icons configuration
    $mdIconProvider
      .icon("flipboard", "assets/icons/Logomark_DIGITAL_White_500X500.svg", 24)
      .icon("squaremeters", "assets/icons/squaremeters.svg", 24)
      .icon("bed", "assets/icons/bed.svg", 24)
      .icon("toilet", "assets/icons/toilet.svg", 24);

    //URL Sanitization
    $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|skype|chrome-extension):/);

    //Gmaps configurations
    uiGmapGoogleMapApiProvider.configure({
      key: 'AIzaSyChfY95Vk-MYNJnW4Wik-tqdphVcruqb7Q',
      v: '3.25'
    });

    //Intercom configuration
    $intercomProvider.appID(INTERCOM_APPID);
    $intercomProvider.asyncLoading(true);

    //Translate loader, user for debug purposes, comment on production environment
    $translateProvider.useLoader('translateDebugger');

    //Localization for date fields

    $mdDateLocaleProvider.formatDate = function(date) {
      return $filter("date")(date);
    };

  }

  /** @ngInject */
  function run($injector) {
    $filter = $injector.get("$filter");
  }

})();
