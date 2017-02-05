(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .factory("AppAuthTokenInterceptor",AppAuthTokenInterceptor)
    .factory("TokenUnauthorizedInterceptor",TokenUnauthorizedInterceptor);

  /** @ngInject */
  function AppAuthTokenInterceptor(backend_url, ApplicationCredentials) {
    var requestInterceptor = {
      request: function(config) {
        var authRequest = backend_url + "security/auth";
        if(config.url === authRequest){
          return config;
        }
        config.headers["O-Auth-Client-Id"] = ApplicationCredentials["client-id"];
        config.headers["O-Auth-Code"] = ApplicationCredentials["code"];
        return config;
      }
    };

    return requestInterceptor;
  }

  /** @ngInject */
  function TokenUnauthorizedInterceptor($injector, $q) {
    var responseInterceptor = {
      responseError: function(response) {
        if (response.status == 401){
          var loginOptions = {
            //TODO: traducción de mensaje de descripción
            description: "Su sesión ha expirado, para continuar por favor inicie sesión de nuevo"
          };
          var SessionService = $injector.get('SessionService');
          SessionService.logout();
          SessionService.launchLoginDialog(loginOptions);
        }
        return $q.reject(response);
      }
    };

    return responseInterceptor;
  }

})();
