(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('PublicProfileController', PublicProfileController);

  /** @ngInject */
  function PublicProfileController(ProfilesService, ImageValidatorService) {
    var vm = this;
    vm.validateImage = ImageValidatorService.validateBase64;

    ProfilesService.loadPublicProfile()
      .then(function (profiles) {
        vm.profiles = profiles;
      });
  }
})();
