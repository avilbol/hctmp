(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('PublicProfileController', PublicProfileController);

  /** @ngInject */
  function PublicProfileController(ProfilesService, BrowserDetectionService, translateFilter, $rootScope, $scope,
                                   $mdDialog, toastr) {
    var vm = this;
    var excludeIdList = [];
    var amountProfiles = 5;
    var filtersDialog;
    var selectedFilters = [];
    var filterList = {
      countries: [],
      languages: [],
      userTypes: [],
      states: [],
      cities: []
    };

    vm.loadProfilesPage = loadProfilesPage;
    vm.openFiltersDialog = openFiltersDialog;
    vm.closeFiltersDialog = closeFiltersDialog;
    vm.clearFilters = clearFilters;
    vm.search = search;
    vm.profiles = [];
    vm.showLoading = true;
    vm.isLoading = false;
    vm.isSafari = BrowserDetectionService.detectBrowser().ISSAFARI;

    vm.totalProfiles = 0;
    vm.profilesPerPage = 3;
    vm.totalAmount = [3,8,12];
    vm.firstLoading = true;
    vm.filterList = filterList;
    

    vm.pagination = {
      current: 1
    };

    function loadProfilesPage(page, filterList) {
      ProfilesService.loadPublicProfiles((page-1)*vm.profilesPerPage, (page-1)*vm.profilesPerPage + vm.profilesPerPage-1, filterList)
        .then(function (profiles) {
          vm.profiles = ProfilesService.generateProfilesPreviewData(profiles.userList);
          vm.totalProfiles = profiles.count;
          vm.firstLoading = false;
        })
        .catch(function () {
          toastr.warning(
            translateFilter("hallocasa.global.error"));
        });
    }

    function loadFilters() {
      ProfilesService.loadProfilesFilters()
        .then(function (filtersData){
          vm.filters = filtersData;
          console.log(vm.filters);
        })
        .catch(function (){
          toastr.warning(
            translateFilter("hallocasa.global.error"));
        });
    }

    function openFiltersDialog($event){
      filtersDialog = $mdDialog.show({
        contentElement: "#profileFilters",
        targetEvent: $event,
        clickOutsideToClose: true,
        fullscreen: true
      });
      // filtersDialog.finally(function () {
        //TODO: Load profiles by filters
      // });
      filtersDialog.catch(function () {
        loadProfilesPage(1, filterList);
      });
    }

    function listenFiltersChanges() {
      var destroyListener = $rootScope.$on("FilterSystem:filterSelected", function (event, filterInformation) {
        var filterIndex =  _.findIndex(selectedFilters, function (selectedFilter) {
          return selectedFilter.propertyFilter.filter.id === filterInformation.propertyFilter.filter.id;
        });

        getFilters(filterInformation, filterIndex);

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

    function getFilters(filterInformation, filterIndex) {
      
      var filterObj = filterInformation;

      if(filterIndex != -1){
        filterObj = selectedFilters[filterIndex];
      }

      switch(filterObj.propertyFilter.filter.id){
        case 7:
          filterList.countries = [];
          _.each(filterObj.selectedFilterOptions, function (e) {
            filterList.countries.push({id: e.optionId});
          });
          break;
        case 4:
          filterList.languages = [];
          _.each(filterObj.selectedFilterOptions, function (e) {
            filterList.languages.push({id: e.optionId});
          });
          break;
        case 5:
          filterList.userTypes = [];
          _.each(filterObj.selectedFilterOptions, function (e) {
            filterList.userTypes.push({id: e.optionId});
          });
          break;
        case 8:
          filterList.states = [];
          _.each(filterObj.selectedFilterOptions, function (e) {
            filterList.states.push({id: e.optionId});
          });
          break; 
        case 9:
          filterList.cities = [];
          _.each(filterObj.selectedFilterOptions, function (e) {
            filterList.cities.push({id: e.optionId});
          });
          break;
      }
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
      filterList = {
        countries: [],
        languages: [],
        userTypes: [],
        states: [],
        cities: []
      };
      selectedFilters = [];
      $rootScope.$broadcast("FilterSystem:clearFilters");
    }

    function search() {
      closeFiltersDialog();
      loadProfilesPage(1, filterList);
    }

    function sendFilters(){
      ProfilesService.profilePublic()
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

    listenFiltersChanges();
    loadProfilesPage(1, filterList);
    loadFilters();

  }
})();
