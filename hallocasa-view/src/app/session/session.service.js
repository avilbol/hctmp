(function() {
  'use strict';

  angular
    .module('HalloCasa.session')
    .service('SessionService', SessionService);

  /** @ngInject */
  function SessionService(translateFilter, $mdMedia, $mdDialog, $document) {
    var service = {
      validateActiveSession: validateActiveSession
    };
    return service;

    function validateActiveSession(message) {
      if(!sessionStorage.session){
        var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'));
        return $mdDialog.show({
          controller: "LoginController",
          controllerAs: "vm",
          templateUrl: 'app/session/login/login.html',
          parent: $document.body,
          locals: {
            textArea: translateFilter(message),
            allowClose: false
          },
          clickOutsideToClose:false,
          fullscreen: useFullScreen
        })
      }
    }
  }
})();









