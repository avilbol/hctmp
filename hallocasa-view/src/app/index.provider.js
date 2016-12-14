(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .provider("appAuthProvider",appAuthProvider);

  function appAuthProvider() {

    this.$get = function ($http, backend_url, $log, $rootScope, localStorageService) {

      var provider = {
        getAuthToken: getAuthToken
      };

      return provider;

      function getAuthToken() {
        $http.get(backend_url+"security/auth")
          .then(function (data) {
            localStorageService.set("appAuthToken", data);
          })
          .catch(function (error) {
            localStorageService.set("appAuthToken","No_Auth_Token");
            $log.debug("Error al autorizar aplicación ante API: ",error);
          })
          .finally(function () {
            $rootScope.$emit("AppAuthFinish");
          })
      }

    }
  }

})();
