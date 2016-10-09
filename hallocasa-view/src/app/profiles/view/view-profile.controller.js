(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('ViewProfileController', ViewProfileController);

  /** @ngInject */
  function ViewProfileController(ProfilesService, $location, SessionService) {
    var vm = this;

    vm.view = "view-profile";


    function loadProfile() {
      var profileID = $location.search().id;
      if(!profileID){
        $location.url("/profile/browser");
      }
      else{
        ProfilesService.loadProfile(profileID)
          .then(function (profile) {
            vm.profile = profile;
          });
      }
    }

    SessionService.validateActiveSession("PublicProfile.PreAuthorize.loginNeeded");

    loadProfile();
  }
})();
