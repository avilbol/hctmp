(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .controller('LandingController', LandingController);

  /** @ngInject */
  function LandingController(ProfilesService, PropertyService, ImageValidatorService, user_images_url) {
    var vm = this;

    ProfilesService.loadPublicProfile()
      .then(function (profiles) {

        profiles = _.map(profiles, function (profile) {
          ImageValidatorService.validateOrFallback(user_images_url + profile.imageLink, "UserDefault")
            .then(function (image) {
              profile.userImage = image;
            });
          return profile;
        });

        vm.profiles = profiles;
      });

    PropertyService.loadProperties()
      .then(function (srcProperties) {
        vm.properties = PropertyService.generatePropertiesPreviewData(srcProperties);
      });

  }
})();
