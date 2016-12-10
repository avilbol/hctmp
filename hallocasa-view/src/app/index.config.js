(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .config(config);

  /** @ngInject */
  function config($logProvider, toastrConfig, $translateProvider, tmhDynamicLocaleProvider, LOCALES, $mdIconProvider,
                  $mdDateLocaleProvider, paginationTemplateProvider, $compileProvider, $httpProvider, $authProvider) {

    $httpProvider.interceptors.push('AppAuthTokenInterceptor');

    // Pagination template
    paginationTemplateProvider.setPath('app/global/pagination/pagination.html');
    // Enable log
    $logProvider.debugEnabled(true);

    // Set options third-party lib
    toastrConfig.allowHtml = true;
    toastrConfig.timeOut = 7000;
    toastrConfig.positionClass = 'toast-bottom-center';
    toastrConfig.progressBar = true;

    // OAuth2 path config
    $authProvider.loginUrl = '/security/token';

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


    $mdDateLocaleProvider.months = [
      "enero",
      "febrero",
      "marzo",
      "abril",
      "mayo",
      "junio",
      "julio",
      "agosto",
      "septiembre",
      "octubre",
      "noviembre",
      "diciembre"
    ];
    $mdDateLocaleProvider.shortMonths = [
      "ene.",
      "feb.",
      "mar.",
      "abr.",
      "may.",
      "jun.",
      "jul.",
      "ago.",
      "sept.",
      "oct.",
      "nov.",
      "dic."
    ];
    $mdDateLocaleProvider.days = [
      "domingo",
      "lunes",
      "martes",
      "mi\u00e9rcoles",
      "jueves",
      "viernes",
      "s\u00e1bado"
    ];
    $mdDateLocaleProvider.shortDays = [
      "dom.",
      "lun.",
      "mar.",
      "mi\u00e9.",
      "jue.",
      "vie.",
      "s\u00e1b."
    ];
  }

})();
