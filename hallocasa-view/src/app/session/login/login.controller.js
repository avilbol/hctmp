(function() {
	'use strict';

	angular
		.module('HalloCasa.session')
		.controller('LoginController', LoginController);

	function LoginController(LoginService, toastr, $mdDialog, $log, textArea, allowClose) {
		var vm = this;
		vm.userData = {};
		vm.login = login;
    vm.closeDialog = closeDialog;
    vm.textArea = textArea;
    vm.allowClose = allowClose;

		function login(){
			LoginService.makeLogin(vm.userData)
				.then(function(userInfo){
          $log(userInfo);
				})
				.catch(function(error){
					if(error.status === 403){
						toastr.error('Nombre de usuario o contraseña incorrectas!', 'Error!');
					}
					else{
						toastr.error('Hubo un error al intentar acceder a su cuenta!', 'Error!');
					}
				});
		}

    function closeDialog(){
      $mdDialog.cancel();
    }

	}
})();
