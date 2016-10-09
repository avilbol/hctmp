(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('PublicProfileController', PublicProfileController);

  /** @ngInject */
  function PublicProfileController(ProfilesService, ImageValidatorService, $location) {
    var vm = this;
    vm.validateImage = ImageValidatorService.validateBase64;
    vm.viewProfile = viewProfile;

    ProfilesService.loadPublicProfile()
      .then(function (profiles) {
        vm.profiles = profiles;
      });

    function viewProfile(id) {
      $location.url("/profile");
      $location.search('id', id);
    }
  }
})();
