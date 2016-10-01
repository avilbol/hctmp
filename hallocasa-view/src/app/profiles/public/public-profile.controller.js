(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('PublicProfileController', PublicProfileController);

  /** @ngInject */
  function PublicProfileController(LandingService) {
    var vm = this;

    vm.profiles = LandingService.getProfiles();
  }
})();
