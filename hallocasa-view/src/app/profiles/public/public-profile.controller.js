(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('PublicProfileController', PublicProfileController);

  /** @ngInject */
  function PublicProfileController(ProfilesService, ImageValidatorService, $location, $scope, VirtualRepeatUtilsService) {
    var vm = this;
    vm.validateImage = ImageValidatorService.validateBase64;
    vm.viewProfile = viewProfile;
    vm.getListHeight = VirtualRepeatUtilsService.heightCalculator(".container", $scope);
    vm.profiles = VirtualRepeatUtilsService.getVirtualRepeatInstance(ProfilesService.loadPublicProfiles, 10);

    function viewProfile(id) {
      $location.url("/profile");
      $location.search('id', id);
    }
  }
})();
