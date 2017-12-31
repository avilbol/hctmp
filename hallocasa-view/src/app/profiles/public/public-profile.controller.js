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
    vm.additionalParameters = {filtersContext: "PublicProfile"};


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
        getFilters(filterInformation);
      });

      $scope.$on("$destroy", destroyListener);
    }

    function getFilters(filterInformation) {
      var filter = filterInformation.propertyFilter.filter;
      var queryName = filter.queryName;
      var filterTypeNature = filter.filterType.filterTypeNature;

      if (!queryName){return;}

      if(filterTypeNature === 'TEXT'){
        filterList[queryName] = filterInformation.apply;
      } else {
        var selections = [];
        _.each(filterInformation.selectedFilterOptions, function (option) {
          selections.push({id: option.optionId});
        });
        filterList[queryName] = selections;
      }
    }

    function closeFiltersDialog() {
      $mdDialog.hide();
    }

    function clearFilters() {
      filterList = {};
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
