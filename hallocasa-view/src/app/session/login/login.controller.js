(function () {
  'use strict';

  angular
    .module('HalloCasa.session')
    .controller('LoginController', LoginController);

  function LoginController(SessionService, toastr, $mdDialog, description, allowClose, $log, translateFilter, $mdMedia, $document, $location) {
    var vm = this;
    vm.userData = {};
    vm.login = login;
    vm.closeDialog = closeDialog;
    vm.description = description;
    vm.allowClose = allowClose;
    vm.sendRecoveryRequest = sendRecoveryRequest;
    vm.showRecovery = showRecovery;
    vm.hideRecovery = hideRecovery;
    vm.signUp = signUp;

    function login() {
      SessionService.login(vm.userData)
        .then(function (response) {
          $log.debug(response);
          $mdDialog.hide();
        })
        .catch(function (error) {
          var msg = (error.status === 401) ? "Login.InvalidPassword.Message" :
            (error.status === 403 ? error.data.message : "Error.whenuseraccountaccess");
          toastr.error(translateFilter(msg), translateFilter('hallocasa.global.error'));
        });
    }

    function closeDialog() {
      $mdDialog.cancel();
    }

    function showRecovery() {
      vm.showPasswordRecovery = true;
    }

    function hideRecovery() {
      vm.showPasswordRecovery = false;
    }

    function signUp() {
      closeDialog();
      launchRegisterDialog();
    }

    function launchRegisterDialog() {
      var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'));
      $mdDialog.show({
        controller: "RegisterController",
        controllerAs: "vm",
        templateUrl: 'app/session/register/register.html',
        parent: $document.body,
        locals: {
          description: "",
          allowClose: vm.allowClose
        },
        clickOutsideToClose: vm.allowClose,
        escapeToClose: vm.allowClose,
        fullscreen: useFullScreen
      });
    }

    function sendRecoveryRequest() {
      SessionService.sendRecoveryRequest(vm.emailRecover)
        .then(function (response) {
          $log.debug(response);
          toastr.success(translateFilter("ForgotPassword.enterEmail.sent"));
          closeDialog();
          $location.url('/');
        })
        .catch(function (error) {
          if (error.status === 403) {
            toastr.error(
              translateFilter("ForgotPassword.enterEmail.errorNotFound"),
              translateFilter('hallocasa.global.error'));
          }
          else {
            toastr.error(
              translateFilter('Error.whenrecoveringpassword'),
              translateFilter('hallocasa.global.error'));
          }
          $location.url('/');
        });
    }

  }
})();
