(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .provider("appAuthProvider",appAuthProvider);

  function appAuthProvider() {

    this.$get = function ($http, backend_url, $log, $rootScope) {

      var provider = {
        getAuthToken: getAuthToken
      };

      return provider;

      function getAuthToken() {
        if(!sessionStorage.appAuthToken){
          sessionStorage.appAuthToken = "qsxDcgYbFHuqPGZCMfWMrcElQgVLkELr";
          sessionStorage.appAuthClientID = "hallocasa_frontend";
        }
        // $http.get(backend_url+"security/auth")
        //   .then(function (data) {
        //     sessionStorage.appAuthToken = data;
        //   })
        //   .catch(function (error) {
        //     //TODO: Traducción de mensaje de error
        //     sessionStorage.appAuthToken = "No_Auth_Token";
        //     $log.debug("Error al autorizar aplicación ante API: ",error);
        //   })
        //   .finally(function () {
        //     $rootScope.$emit("AppAuthFinish");
        //   })
      }

    }
  }

})();
