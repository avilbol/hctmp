(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('ViewProfileController', ViewProfileController);

  /** @ngInject */
  function ViewProfileController(ProfilesService, $location,
      translateFilter, toastr) {
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
              toastr.error(
                translateFilter("Warning.usernotfound"));
            }
            if(error.status === 500){
              toastr.error(
                translateFilter("Error.whenloadinguser"));
            }
            $location.url("/profile/browser");
          });
      }
    }

    loadProfile();
  }
})();
