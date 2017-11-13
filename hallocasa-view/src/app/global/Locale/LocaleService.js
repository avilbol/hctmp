(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .service('LocaleService', LocaleService);

  function LocaleService($translate, LOCALES, $rootScope, tmhDynamicLocale, $log, $document, $location) {
    $translate.onReady(function () {
      var currentLanguage = $translate.use();
      if(currentLanguage)
        tmhDynamicLocale.set($translate.use().toLowerCase().replace(/_/g, '-'));
    });


    var localesObj = LOCALES.locales;
    // locales and locales display names
    var _LOCALES = Object.keys(localesObj);
    if (!_LOCALES || _LOCALES.length === 0) {
      $log('There are no _LOCALES provided');
    }
    var _LOCALES_DISPLAY_NAMES = [];
    _LOCALES.forEach(function (locale) {
      _LOCALES_DISPLAY_NAMES.push(localesObj[locale]);
    });

    // STORING CURRENT LOCALE
    var currentLocale = $translate.proposedLanguage();// because of async loading

    // METHODS
    var checkLocaleIsValid = function (locale) {
      return _LOCALES.indexOf(locale) !== -1;
    };

    var setLocale = function (locale) {
      if (!checkLocaleIsValid(locale)) {
        $log.log('Locale name "' + locale + '" is invalid');
        return;
      }
      currentLocale = locale;// updating current locale

      // asking angular-translate to load and apply proper translations
      $translate.use(locale);
    };

    // set current language on URL as query string
    var updateURLQueryString = function () {
      currentLocale = currentLocale ? currentLocale : $translate.use();
      var language = LOCALES.locales[currentLocale];
      var minLocale = LOCALES.languages[language].locale;
      $location.search("lang", minLocale).replace();
    };

    // EVENTS
    // on successful applying translations by angular-translate
    var watch = $rootScope.$on('$translateChangeSuccess', function (event, data) {
      $document.prop("documentElement").setAttribute('lang', data.language);// sets "lang" attribute to html

      // asking angular-dynamic-locale to load and apply proper AngularJS $locale setting
      tmhDynamicLocale.set(data.language.toLowerCase().replace(/_/g, '-'));

      // set current language on URL as query string
      updateURLQueryString();
    });

    var watchLocation = $rootScope.$on('$locationChangeSuccess', updateURLQueryString);

    $rootScope.$on('$destroy',watch);
    $rootScope.$on('$destroy',watchLocation);

    return {
      getCurrentLenguage: function () {
        currentLocale = currentLocale ? currentLocale : $translate.use();
        var language = LOCALES.locales[currentLocale];
        var minLocale = LOCALES.languages[language].locale;
        return minLocale;
      },
      getLocaleDisplayName: function () {
        currentLocale = currentLocale ? currentLocale : $translate.use();
        return localesObj[currentLocale];
      },
      setLocaleByDisplayName: function (localeDisplayName) {
        setLocale(
          _LOCALES[
            _LOCALES_DISPLAY_NAMES.indexOf(localeDisplayName)// get locale index
            ]
        );
      },
      getLocalesDisplayNames: function () {
        return _LOCALES_DISPLAY_NAMES;
      },
      getLanguages: function() {
        return LOCALES.languages();
      }
    };
  }
})();
