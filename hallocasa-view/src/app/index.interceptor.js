(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .factory("AppAuthTokenInterceptor",AppAuthTokenInterceptor);

  function AppAuthTokenInterceptor(backend_url, ApplicationCredentials) {
    var requestInterceptor = {
      request: function(config) {
        var authRequest = backend_url + "security/auth";
        if(config.url === authRequest){
          return config;
        }
        config.headers["O-Auth-Client-Id"] = ApplicationCredentials.ClientID;
        config.headers["O-Auth-Code"] = ApplicationCredentials.AuthToken;
        return config;
      }
    };

    return requestInterceptor;
  }

})();
