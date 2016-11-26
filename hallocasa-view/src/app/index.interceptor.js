(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .factory("AppAuthTokenInterceptor",AppAuthTokenInterceptor);

  function AppAuthTokenInterceptor($q, $rootScope, backend_url) {
    var requestInterceptor = {
      request: function(config) {
        var authRequest = backend_url + "security/auth";
        if(config.url === authRequest){
          return config;
        }

        if(sessionStorage.appAuthToken){
          config.headers.code = sessionStorage.appAuthToken;
          return config;
        }

        var deferred = $q.defer();
        var destroyListener = $rootScope.$on("AppAuthFinish", function() {
          config.headers['code'] = sessionStorage.appAuthToken;
          deferred.resolve(config);
          destroyListener();
        });

        return deferred.promise;
      }
    };

    return requestInterceptor;
  }

})();
