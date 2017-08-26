(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .factory('translateDebugger', translateDebugger);

  function translateDebugger($http, backend_url) {
    sessionStorage.debugTranslate = sessionStorage.debugTranslate ? sessionStorage.debugTranslate : "disable";

    return function (options) {
      return $http.get(backend_url+"locales/translations?lang="+options.key)
        .then(function successCallback(translate) {
          if(sessionStorage.debugTranslate === "enable"){
            translate.data = _.mapObject(translate.data, function (val, key) {
              return key + ": " + val;
            });
          }
          return translate.data;
        });
    };
  }
})();
