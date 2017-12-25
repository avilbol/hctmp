(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('PublicProfileController', PublicProfileController);

  /** @ngInject */
  function PublicProfileController(ProfilesService, BrowserDetectionService, translateFilter, $rootScope, $scope,
                                   $mdDialog, toastr) {
    var vm = this;
    var filtersDialog;
    var selectedFilters = [];
    var filterList = {};

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
    vm.profilesPerPage = 25;
    vm.totalAmount = [25,50,100];
    vm.firstLoading = true;
    vm.filterList = filterList;
    vm.additionalParameters = {filtersContext: "PublicProperty"};
    

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
      filtersDialog.catch(function () {
        loadProfilesPage(1, filterList);
      });
    }

    function listenFiltersChanges() {
      var destroyListener = $rootScope.$on("FilterSystem:filterSelected", function (event, filterInformation) {
        var filterIndex =  _.findIndex(selectedFilters, function (selectedFilter) {
          return selectedFilter.propertyFilter.filter.id === filterInformation.propertyFilter.filter.id;
        });

        
        var filterTypeNature = filterInformation.propertyFilter.filter.filterType.filterTypeNature;
        getFilters(filterInformation, filterIndex, filterTypeNature);        

        switch(filterTypeNature){
          case "DROPDOWN":
            processDropdownSelection(filterInformation, filterIndex);
            break;
          case "YESNO":
            processBinarySelection(filterInformation, filterIndex);
            break;
          case "RANGE":
            processRangeSelection(filterInformation, filterIndex);
            break;
          case "TEXT":
            break
        }
      });

      $scope.$on("$destroy", destroyListener);
    }

    function getFilters(filterInformation, filterIndex, filterTypeNature) {
      
      var filterObj = filterInformation;

      var arrayName = filterObj.propertyFilter.filter.arrayName;

      if(filterTypeNature === 'TEXT'){
        filterList[arrayName] = filterObj.apply;
      } else {
        if (arrayName) {
          var selections = []
          _.each(filterObj.selectedFilterOptions, function (e) {
            selections.push({id: e.optionId});
          });
          filterList[arrayName] = selections;
        }
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
      filterList = {};
      selectedFilters = [];
      $rootScope.$broadcast("FilterSystem:clearFilters");
    }

    function search() {
      closeFiltersDialog();
      loadProfilesPage(1, filterList);
    }

    listenFiltersChanges();
    loadProfilesPage(1, filterList);
    loadFilters();

  }
})();
