(function() {
	'use strict';

	angular
		.module('HalloCasa.session')
		.controller('RegisterController', RegisterController);

	function RegisterController(RegisterService, toastr, translateFilter, $mdDialog, allowClose, LocaleService, LOCALES,
                              $log) {
		var vm = this;
		vm.userData = {};
		vm.register = register;
    vm.closeDialog = closeDialog;
    vm.showTerms = showTerms;
    vm.closeTerms = closeTerms;
    vm.allowClose = allowClose;
    vm.currentLanguage = LOCALES.languages[LocaleService.getLocaleDisplayName()].locale;

		function register(){
      var currentLanguage = LocaleService.getLocaleDisplayName();
      vm.userData.language = LOCALES.languages[currentLanguage];
			RegisterService.makeRegister(vm.userData)
				.then(function(){
          //Disabled due to MailChimp confirmation message
				  //subscribeUser(vm.userData);
          toastr.success(
            translateFilter("SignUp.Form.Success.Message"),
            translateFilter("SignUp.Form.Success.EmailSent")
          );
          closeDialog();
				})
				.catch(function(error){
          if(error.status === 409){
            toastr.error(
							translateFilter('Error.emailalreadyexists'),
							translateFilter('hallocasa.global.error'));
          }
          else{
            toastr.error(
							translateFilter('Error.whenuseraccountaccess'),
							translateFilter('hallocasa.global.error'));
          }
				});
		}

		function subscribeUser(userData) {
      RegisterService.subscribeNewUser(userData)
        .catch(function (error) {
          $log.error("Unable to register user to subscribers list:", error);
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
      if(vm.showTermsText){
        closeTerms();
      }
      else {
        $mdDialog.cancel();
      }

    }
	}
})();
