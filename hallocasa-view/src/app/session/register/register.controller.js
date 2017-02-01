(function() {
	'use strict';

	angular
		.module('HalloCasa.session')
		.controller('RegisterController', RegisterController);

	function RegisterController(RegisterService, toastr, $mdDialog, $log, LocaleService, LOCALES, $translate) {
		var vm = this;
		vm.userData = {};
		vm.register = register;
    vm.closeDialog = closeDialog;
    vm.showTerms = showTerms;
    vm.closeTerms = closeTerms;

		function register(){
      var currentLanguage = LocaleService.getLocaleDisplayName();
      vm.userData.language = LOCALES.languages[currentLanguage];
			RegisterService.makeRegister(vm.userData)
				.then(function(){
          toastr.success('Usuario creado con éxito');
          closeDialog();
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

    function showTerms() {
      vm.showTermsText = true;
      var currentLanguage = LocaleService.getLocaleDisplayName();
      var termsTemplateURL = "app/main/terms/text-templates/";
      switch (currentLanguage){
        case "Español":
          vm.templateURL = termsTemplateURL + "TermsText_es.html";
          break;

        case "English" :
          vm.templateURL = termsTemplateURL + "TermsText_en.html";
          break;

        case "Deutsch":
          vm.templateURL = termsTemplateURL + "TermsText_de.html";
          break;
      }
    }

    function closeTerms() {
      vm.showTermsText = false;
    }

    function closeDialog() {
      $mdDialog.cancel();
    }
	}
})();
