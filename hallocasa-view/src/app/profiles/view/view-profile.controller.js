(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('ViewProfileController', ViewProfileController);

  /** @ngInject */
  function ViewProfileController(ProfilesService, $location, toastr) {
    var vm = this;

    function loadProfile() {
      var profileID = $location.search().id;
      if(!profileID){
        $location.url("/profile/browser");
      }
      else{
        ProfilesService.loadProfile(profileID)
          .then(function (data) {
            data = ProfilesService.validateUserData(data);
            vm.userData = data;
          })
          .catch(function (error) {
            if(error.status === 404){
              //TODO: Traducción del mensaje de error
              toastr.error("No se ha podido encontrar el usuario");

            }
            if(error.status === 500){
              //TODO: Traducción del mensaje de error
              toastr.error("Hubo un error al intentar cargar el usuario");

            }

            $location.url("/profile/browser");
          });
      }
    }

    loadProfile();
  }
})();
