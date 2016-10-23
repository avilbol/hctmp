(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('PublicProfileController', PublicProfileController);

  /** @ngInject */
  function PublicProfileController(ProfilesService, ImageValidatorService, $location) {
    var vm = this;
    vm.validateImage = ImageValidatorService.validateBase64;
    vm.viewProfile = viewProfile;
    vm.loadedProfiles = [];


    function viewProfile(id) {
      $location.url("/profile");
      $location.search('id', id);
    }

    vm.profiles = {
      toLoad_: 0,

      // Required.
      getItemAtIndex: function(index) {
        if (index > vm.loadedProfiles.length) {
          vm.profiles.fetchMoreItems_(index);
          return null;
        }

        return vm.loadedProfiles[index];
      },

      // Required.
      // For infinite scroll behavior, we always return a slightly higher
      // number than the previously loaded items.
      getLength: function() {
        return vm.loadedProfiles.length + 1;
      },

      fetchMoreItems_: function(index) {
        // For demo purposes, we simulate loading more items with a timed
        // promise. In real code, this function would likely contain an
        // $http request.

        if (vm.profiles.toLoad_ < index) {
          vm.profiles.toLoad_ += 10;
          ProfilesService.loadPublicProfiles(vm.loadedProfiles.length, vm.loadedProfiles.length + 10)
            .then(function (profiles) {
              vm.loadedProfiles = _.union(vm.loadedProfiles, profiles);
            });
        }
      }
    };

  }
})();
