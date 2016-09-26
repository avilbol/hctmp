(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .controller('LandingController', LandingController);

  /** @ngInject */
  function LandingController(LandingService) {
    var vm = this;
    vm.profiles = LandingService.getProfiles();

  }
})();
