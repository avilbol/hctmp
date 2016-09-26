(function() {
	'use strict';

	angular
		.module('HalloCasa.session')
		.controller('RegisterController', RegisterController);

	function RegisterController(RegisterService, toastr, $mdDialog, $log) {
		var vm = this;
		vm.userData = {};
		vm.register = register;
    vm.closeDialog = closeDialog;

		function register(){
			RegisterService.makeRegister(vm.userData)
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

    function closeDialog() {
      $mdDialog.cancel();
    }
	}
})();
