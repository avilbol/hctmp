(function() {
  'use strict';

  angular
    .module('HalloCasa.session')
    .controller('PasswordRecoveryController', PasswordRecoveryController);

  /** @ngInject */
  function PasswordRecoveryController(SessionService, $mdDialog, translateFilter, toastr, $route, $location) {
    var vm = this;

    var tokenData;
    var recoveryToken = $route.current.params.token;

    vm.sendRecoveryData = sendRecoveryData;

    function sendRecoveryData() {
      SessionService.sendRecoveryPassword(vm.password1, recoveryToken)
        .then(function(){
          toastr.success(translateFilter("ForgotPassword.recovery.newPassword.success"));
          closeDialog();
        })
        .catch(function(){
          //TODO: mensaje de recuperación de contraseña
          toastr.error('Hubo un error al intentar recuperar contraseña!', 'Error!');
        });
    }

    function validateToken() {
      SessionService.validateToken(recoveryToken)
        .then(function(data){
          vm.validatedRecovery = true;
          tokenData = data;
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

    validateToken();
  }
})();



