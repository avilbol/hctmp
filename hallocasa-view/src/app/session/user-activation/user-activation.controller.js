(function() {
  'use strict';

  angular
    .module('HalloCasa.session')
    .controller('UserActivationController', UserActivationController);

  /** @ngInject */
  function UserActivationController(SessionService, $mdDialog, toastr,
      translateFilter, $route, $location) {

    function activateUser() {
      var email = $route.current.params["email"];
      var activationKey = $route.current.params["user_activation_token"];

      SessionService.activateUser(email, activationKey)
        .then(function(){
          toastr.success(translateFilter('Alert.profileactivated'));
          closeDialog();
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

    activateUser();
  }
})();
