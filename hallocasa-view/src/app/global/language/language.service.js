(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .service('LanguageService', LanguageService);

  function LanguageService(backend_url, GenericRESTResource, $resource) {
    var service = {
      getLanguages: getLanguages
    };

    var resources = {
      languages: $resource(backend_url + "languages", {}, GenericRESTResource)
    };

    return service;

    function getLanguages() {
      return resources.languages.query().$promise;
    }
  }
})();
