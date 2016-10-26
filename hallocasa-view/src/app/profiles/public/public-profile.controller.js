(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('PublicProfileController', PublicProfileController);

  /** @ngInject */
  function PublicProfileController(ProfilesService, ImageValidatorService, $location, $window, $scope) {
    var vm = this;
    vm.validateImage = ImageValidatorService.validateBase64;
    vm.viewProfile = viewProfile;
    vm.loadedProfiles = [];


    function viewProfile(id) {
      $location.url("/profile");
      $location.search('id', id);
    }

    vm.getListHeight = function() {
      //TODO: ajustar calculo
      //document.getElementsByClassName("container")[0].clientHeight
      return {height: '' + ($window.innerHeight - 72) + 'px'};
    };
    $window.addEventListener('resize', onResize);
    function onResize() {
      $scope.$digest();
    }
    $scope.$on('$destroy', function() {
      $window.removeEventListener('resize', onResize);
    });

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
    //vm.profiles.fetchMoreItems_(10);
  }
})();
