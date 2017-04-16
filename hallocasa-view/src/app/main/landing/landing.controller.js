(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .controller('LandingController', LandingController);

  /** @ngInject */
  function LandingController(ProfilesService, PropertyService) {
    var vm = this;

    ProfilesService.loadPublicProfiles()
      .then(function (profiles) {
        vm.profiles = profiles;
      });

    PropertyService.loadProperties()
      .then(function (srcProperties) {
        vm.properties = PropertyService.generatePropertiesPreviewData(srcProperties);
      });

  }
})();
