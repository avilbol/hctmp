(function() {
	'use strict';

	angular
		.module('HalloCasa.session')
		.controller('LoginController', LoginController);

	function LoginController(SessionService, toastr, $mdDialog, description, allowClose, $log, translateFilter) {
		var vm = this;
		vm.userData = {};
		vm.login = login;
    vm.closeDialog = closeDialog;
    vm.description = description;
    vm.allowClose = allowClose;
    vm.sendRecoveryRequest = sendRecoveryRequest;
    vm.showRecovery = showRecovery;
    vm.hideRecovery = hideRecovery;

		function login(){
      SessionService.login(vm.userData)
				.then(function(response){
          $log.debug(response);
          closeDialog();
				})
				.catch(function(error){
					if(error.status === 403){
            //TODO: traducción del mensaje de error
						toastr.error('Nombre de usuario o contraseña incorrectas!', 'Error!');
					}
					else{
            //TODO: traducción del mensaje de error
						toastr.error('Hubo un error al intentar acceder a su cuenta!', 'Error!');
					}
				});
		}

    function closeDialog(){
      $mdDialog.cancel();
    }

    function showRecovery() {
      vm.showPasswordRecovery = true;
    }

    function hideRecovery() {
      vm.showPasswordRecovery = false;
    }

    function sendRecoveryRequest() {
      SessionService.sendRecoveryRequest(vm.emailRecover)
        .then(function(response){
          $log.debug(response);
          toastr.success(translateFilter("ForgotPassword.enterEmail.sent"));
          closeDialog();
        })
        .catch(function(error){
          //TODO: confirmar código de estado
          if(error.status === 403){
            toastr.error(translateFilter("ForgotPassword.enterEmail.errorNotFound"), 'Error!');
          }
          else{
            //TODO: traducción del mensaje de error
            toastr.error('Hubo un error al intentar recuperar contraseña!', 'Error!');
          }
        });
    }

	}
})();
