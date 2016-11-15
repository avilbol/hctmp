(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .controller('LandingController', LandingController);

  /** @ngInject */
  function LandingController(ProfilesService) {
    var vm = this;

    ProfilesService.loadPublicProfile()
      .then(function (profiles) {
        vm.profiles = profiles;
      });

  }
})();
