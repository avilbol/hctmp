(function() {
  'use strict';

  angular
    .module('HalloCasa.session')
    .controller('PasswordRecoveryController', PasswordRecoveryController);

  /** @ngInject */
  function PasswordRecoveryController(SessionService, $mdDialog, translateFilter, toastr, $route, $location) {
    var vm = this;

    var tokenData;
    var recoveryToken = $route.current.params["password_recovery_token"];

    vm.sendRecoveryData = sendRecoveryData;

    function sendRecoveryData() {
      SessionService.sendRecoveryPassword(vm.password1, tokenData)
        .then(function(){
          toastr.success(translateFilter("ForgotPassword.recovery.newPassword.success"));
          closeDialog();
        })
        .catch(function(){
          toastr.error(
            translateFilter('Error when recovering password'),
            translateFilter('hallocasa.global.error'));
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
          toastr.error(
            translateFilter('Alert.tokenexpired'),
            translateFilter('hallocasa.global.error'));
        });
    }

    function closeDialog() {
      $mdDialog.cancel();
    }

    validateToken();
  }
})();
