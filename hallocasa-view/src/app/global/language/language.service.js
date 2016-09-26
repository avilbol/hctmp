(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .service('LanguageService', LanguageService);

  function LanguageService($q) {
    var service = {
      getLanguages: getLanguages
    };

    return service;

    function getLanguages() {
      return $q(function (resolve) {
        resolve([
          "English",
          "Español",
          "Deutsch",
          "Africaans",
          "Algerian",
          "Arabic",
          "Armenian",
          "Bosnian",
          "Bulgarian",
          "Cantonese",
          "Catalan",
          "Creole",
          "Croatian",
          "Czech",
          "Danish",
          "Dutch",
          "Estonian",
          "Filipino",
          "Finnish",
          "Flemish",
          "French",
          "Gaelic",
          "Greek",
          "Hebrew",
          "Hindi",
          "Hungarian",
          "Icelandic",
          "Irish",
          "Italian",
          "Japanese",
          "Korean",
          "Latvian",
          "Lithuanian",
          "Mandarin",
          "Moldovian",
          "Moroccan",
          "Norwegian",
          "Polish",
          "Portuguese",
          "Romanian",
          "Russian",
          "Scottish",
          "Slovakian",
          "Slovene",
          "Swedish",
          "Swiss French",
          "Swiss German",
          "Thai",
          "Tunisian",
          "Turkish",
          "Ukrainian",
          "Vietnamese"
        ]);
      });
    }
  }
})();
