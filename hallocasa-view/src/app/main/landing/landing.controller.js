(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .controller('LandingController', LandingController);

  /** @ngInject */
  function LandingController(ProfilesService, PropertyService, ImageValidatorService, $location) {
    var vm = this;

    vm.validateImage = ImageValidatorService.validateBase64;
    vm.viewProperty = viewProperty;

    ProfilesService.loadPublicProfile()
      .then(function (profiles) {
        vm.profiles = profiles;
      });

    PropertyService.loadProperties()
      .then(function (properties) {
        vm.properties = properties;
      });

    function viewProperty(id) {
      $location.url("/property");
      $location.search('id', id);
    }
    
  }
})();
