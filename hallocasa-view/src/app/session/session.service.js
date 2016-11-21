(function() {
  'use strict';

  angular
    .module('HalloCasa.session')
    .service('SessionService', SessionService);

  /** @ngInject */
  function SessionService($mdMedia, $mdDialog, $document, $auth, $q) {
    var service = {
      validateActiveSession: validateActiveSession,
      login: login
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
        $auth.login(userCredentials)
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
    
  }
})();









