(function() {
	'use strict';

	angular
		.module('HalloCasa.session')
		.controller('LoginController', LoginController);

	function LoginController(SessionService, toastr, $mdDialog, description, allowClose) {
		var vm = this;
		vm.userData = {};
		vm.login = login;
    vm.closeDialog = closeDialog;
    vm.description = description;
    vm.allowClose = allowClose;

		function login(){
      console.log("login",vm.userData);
      SessionService.login(vm.userData)
				.then(function(response){
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

	}
})();
