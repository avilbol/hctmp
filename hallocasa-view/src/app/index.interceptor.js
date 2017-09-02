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
        if(config.url === authRequest || !config.url.startsWith(backend_url)){
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
  function TokenUnauthorizedInterceptor($injector, $q, translateFilter, localStorageService) {
    var responseInterceptor = {
      responseError: function(response) {
        if (response.status == 401){
          var loginOptions = {};
          if(localStorageService.get("HalloCasa.currentUser")){
            loginOptions.description = translateFilter("Alert.sessionexpired");
          }
          else {
            loginOptions.description = "PublicProfile.PreAuthorize.loginNeeded";
          }
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
