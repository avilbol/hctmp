(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('PublicProfileController', PublicProfileController);

  /** @ngInject */
  function PublicProfileController(ProfilesService, BrowserDetectionService, translateFilter, $rootScope, $scope,
                                   $mdDialog) {
    var vm = this;
    var excludeIdList = [];
    var amountProfiles = 5;
    var filtersDialog;
    var selectedFilters = [];

    vm.fetchRangeProfiles = fetchRangeProfiles;
    vm.openFiltersDialog = openFiltersDialog;
    vm.closeFiltersDialog = closeFiltersDialog;
    vm.clearFilters = clearFilters;
    vm.profiles = [];
    vm.showLoading = true;
    vm.isLoading = false;
    vm.isSafari = BrowserDetectionService.detectBrowser().ISSAFARI;

    function fetchRangeProfiles() {
      if(!vm.showLoading){
        return;
      }
      vm.isLoading = true;
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
        })
        .finally(function () {
          vm.isLoading = false;
        });
    }

    function loadFilters() {
      ProfilesService.loadProfilesFilters()
        .then(function (filtersData) {
          vm.filters = filtersData;
        })
        .catch(function () {
          toastr.warning(
            translateFilter("hallocasa.global.error"));
        });
    }

    function openFiltersDialog($event) {
      filtersDialog = $mdDialog.show({
        contentElement: "#profileFilters",
        targetEvent: $event,
        clickOutsideToClose: true,
        fullscreen: true
      });
      filtersDialog.finally(function () {
        //TODO: Load profiles by filters
      });
    }

    function listenFiltersChanges() {
      var destroyListener = $rootScope.$on("FilterSystem:filterSelected", function (event, filterInformation) {
        var filterIndex =  _.findIndex(selectedFilters, function (selectedFilter) {
          return selectedFilter.propertyFilter.filter.id === filterInformation.propertyFilter.filter.id;
        });

        switch(filterInformation.propertyFilter.filter.filterType.filterTypeNature){
          case "DROPDOWN":
            processDropdownSelection(filterInformation, filterIndex);
            break;
          case "YESNO":
            processBinarySelection(filterInformation, filterIndex);
            break;
          case "RANGE":
            processRangeSelection(filterInformation, filterIndex);
            break;
        }
      });

      $scope.$on("$destroy", destroyListener);
    }

    function processDropdownSelection(filterInformation, filterIndex) {
      if(_.isEmpty(filterInformation.selectedFilterOptions)){
        selectedFilters.splice(filterIndex, 1);
      }
      else{
        if(filterIndex === -1){
          selectedFilters.push(filterInformation);
        }
        else{
          selectedFilters[filterIndex] = filterInformation;
        }
      }
    }

    function processBinarySelection(filterInformation, filterIndex) {
      switch (filterInformation.binaryFilterType){
        case "Dropdown":
          if(filterIndex === -1){
            selectedFilters.push(filterInformation);
          }
          else{
            selectedFilters[filterIndex] = filterInformation;
          }
          break;
        case "Checkbox":
          if(filterIndex === -1 && filterInformation.apply){
            selectedFilters.push(filterInformation);
          }
          else{
            selectedFilters.splice(filterIndex, 1);
          }
          break;
      }
    }

    function processRangeSelection(filterInformation, filterIndex) {
      if(filterIndex === -1){
        selectedFilters.push(filterInformation);
      }
      else{
        selectedFilters[filterIndex] = filterInformation;
      }
    }

    function closeFiltersDialog() {
      $mdDialog.hide();
    }

    function clearFilters() {
      selectedFilters = [];
      $rootScope.$broadcast("FilterSystem:clearFilters");
    }

    listenFiltersChanges();
    fetchRangeProfiles();
    loadFilters();

  }
})();
