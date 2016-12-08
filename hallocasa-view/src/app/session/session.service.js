(function() {
  'use strict';

  angular
    .module('HalloCasa.session')
    .service('SessionService', SessionService);

  /** @ngInject */
  function SessionService($mdMedia, $mdDialog, $document, $auth, $q) {
    var service = {
      validateActiveSession: validateActiveSession,
      login: login,
      logout: logout,
      sendRecoveryRequest: sendRecoveryRequest,
      sendRecoveryPassword: sendRecoveryPassword
    };
    return service;

    function validateActiveSession(message) {
      if(!$auth.isAuthenticated()){
        var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'));
        return $mdDialog.show({
          controller: "LoginController",
          controllerAs: "vm",
          templateUrl: 'app/session/login/login.html',
          parent: $document.body,
          locals: {
            description: message,
            allowClose: false
          },
          clickOutsideToClose:false,
          fullscreen: useFullScreen
        })
      }
    }

    function login(userCredentials) {
      return $q(function (resolve, reject) {
        userCredentials.client_id = "hallocasa_frontend";
        userCredentials.client_secret = "12345";
        userCredentials.grant_type = "password";
        userCredentials.code = "qsxDcgYbFHuqPGZCMfWMrcElQgVLkELr";

        $auth.login(userCredentials, {
          headers: {'Content-Type': 'application/x-www-form-urlencoded'},
          transformRequest: function(obj) {
            var str = [];
            for(var p in obj)
              str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            return str.join("&");
          }
        })
          .then(function (userToken) {
            $auth.setToken(userToken);
            resolve();
          })
          .catch(function(error){
            reject(error);
          })
      });
    }

    function logout() {
      $auth.logout();
    }

    function sendRecoveryRequest() {
      //TODO: conectar al backend
      return $q(function (resolve) {resolve();});
    }

    function sendRecoveryPassword() {
      //TODO: conectar al backend
      return $q(function (resolve) {resolve();});
    }

  }
})();









