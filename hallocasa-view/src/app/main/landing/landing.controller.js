(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .controller('LandingController', LandingController);

  /** @ngInject */
  function LandingController(ProfilesService, PropertyService, ImageValidatorService, $location) {
    var vm = this;

    vm.viewProperty = viewProperty;

    ProfilesService.loadPublicProfile()
      .then(function (profiles) {
        vm.profiles = profiles;
      });

    PropertyService.loadProperties()
      .then(function (properties) {
        properties = _.map(properties, function (property) {
          property.images = angular.isArray(property.images) ? property.images : [];
          property.images[0] = ImageValidatorService.validateOrFallback(property.images[0], "PropertyDefault");
          return property;
        });

        vm.properties = properties;
      });

    function viewProperty(id) {
      $location.url("/property");
      $location.search('id', id);
    }

  }
})();
