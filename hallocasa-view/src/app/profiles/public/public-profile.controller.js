(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('PublicProfileController', PublicProfileController);

  /** @ngInject */
  function PublicProfileController(ProfilesService, ImageValidatorService, $scope, VirtualRepeatUtilsService,
                                   user_images_url, $q) {
    var vm = this;
    vm.getListHeight = VirtualRepeatUtilsService.heightCalculator(".container", $scope);
    vm.profiles = VirtualRepeatUtilsService.getVirtualRepeatInstance(loadProfiles, 10);

    function loadProfiles(start, finish) {
      return $q(function (success, reject) {
        ProfilesService.loadPublicProfiles(start, finish)
          .then(function (profiles) {
            profiles = _.map(profiles, function (profile) {
              var mainDescription = _.find(profile.userDescriptions, function (description) {
                return description.language.id === profile.mainSpokenLanguage.id;
              });
              profile.description = mainDescription ? mainDescription.value : undefined;

              ImageValidatorService.validateOrFallback(user_images_url + profile.imageLink, "UserDefault")
                .then(function (image) {
                  profile.userImage = image;
                });
              return profile;
            });
            success(profiles);
          })
          .catch(function () {
            reject();
          });
      });
    }

  }
})();
