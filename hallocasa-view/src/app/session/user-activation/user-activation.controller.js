(function() {
  'use strict';

  angular
    .module('HalloCasa.session')
    .controller('UserActivationController', UserActivationController);

  /** @ngInject */
  function UserActivationController(SessionService, $mdDialog, toastr, $route, $location) {

    function activateUser() {
      var email = $route.current.params["email"];
      var activationKey = $route.current.params["user_activation_token"];

      SessionService.activateUser(email, activationKey)
        .then(function(){
          //TODO: mensaje de éxito
          toastr.success('Su perfil ha sido activado exitosamente, ya puede iniciar sesión');
          closeDialog();
        })
        .catch(function(){
          closeDialog();
          $location.url("/forbidden");

          //TODO: mensaje de error de token
          toastr.error('Token inválido o vencido!', 'Error!');
        });
    }

    function closeDialog() {
      $mdDialog.cancel();
    }

    activateUser();
  }
})();



