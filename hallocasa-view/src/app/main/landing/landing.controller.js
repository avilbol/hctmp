(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .controller('LandingController', LandingController);

  /** @ngInject */
  function LandingController(ProfilesService, PropertyService) {
    var vm = this;
    var amountProfiles = 6;
    var amountProperties = 3;

    ProfilesService.loadProfiles([], amountProfiles)
      .then(function (profiles) {
        vm.profiles = profiles;
      });

    PropertyService.loadProperties(amountProperties)
      .then(function (srcProperties) {
        vm.properties = PropertyService.generatePropertiesPreviewData(srcProperties);
      });

  }
})();
