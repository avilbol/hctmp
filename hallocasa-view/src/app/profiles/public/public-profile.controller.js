(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('PublicProfileController', PublicProfileController);

  /** @ngInject */
  function PublicProfileController(ProfilesService) {
    var vm = this;
    var excludeIdList = [];
    var amountProfiles = 5;

    vm.fetchRangeProfiles = fetchRangeProfiles;
    vm.profiles = [];
    vm.showLoading = true;

    function fetchRangeProfiles() {
      if(!vm.showLoading){
        return;
      }
      ProfilesService.loadPublicProfiles(excludeIdList, amountProfiles)
        .then(function (profiles) {
          _.each(profiles, function (profile) {
            excludeIdList.push(profile.id);
            var mainDescription = _.find(profile.userDescriptions, function (description) {
              return description.language.id === profile.mainSpokenLanguage.id;
            });
            profile.description = mainDescription ? mainDescription.value : undefined;
            vm.profiles.push(profile);
          });
          vm.showLoading = profiles.length > 0 && profiles.length === amountProfiles;
        });
    }

    fetchRangeProfiles();
  }
})();
