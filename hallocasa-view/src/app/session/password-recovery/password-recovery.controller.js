(function() {
  'use strict';

  angular
    .module('HalloCasa.session')
    .controller('PasswordRecoveryController', PasswordRecoveryController);

  /** @ngInject */
  function PasswordRecoveryController(SessionService, $mdDialog, translateFilter, toastr, $route) {
    var vm = this;

    vm.sendRecoveryData = sendRecoveryData;

    function sendRecoveryData() {
      var recoveryToken = $route.current.params.token;
      SessionService.sendRecoveryPassword(vm.password1, recoveryToken)
        .then(function(){
          toastr.success(translateFilter("ForgotPassword.recovery.newPassword.success"));
          closeDialog();
        })
        .catch(function(error){
          //TODO: mensaje de recuperación de contraseña
          toastr.error('Hubo un error al intentar recuperar contraseña!', 'Error!');
        });
    }

    function closeDialog() {
      $mdDialog.cancel();
    }
  }
})();



