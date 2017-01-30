(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .service('LanguageService', LanguageService);

  function LanguageService(backend_url, GenericRESTResource, $resource, $translate) {
    var service = {
      getLanguages: getLanguages,
      translate: translate
    };

    var resources = {
      //languages: $resource(backend_url + "languages", {}, GenericRESTResource)
      languages: $resource("./mocks/language/languages.json", {}, GenericRESTResource)
    };

    return service;

    function getLanguages() {
      return resources.languages.query().$promise;
    }

    function translate(translationId, defaultTranslationText) {
      var translatedString = $translate.instant(translationId);
      return (translatedString !== translationId) ? translatedString : defaultTranslationText;
    }
  }
})();
