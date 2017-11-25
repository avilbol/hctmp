(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .controller('PublicPropertyController', PublicPropertyController);

  /** @ngInject */
  function PublicPropertyController(PropertyService, translateFilter, toastr, FiltersService, $rootScope, $scope, $mdDialog) {
    var vm = this;
    var selectedFilters = [];
    var filtersDialog;

    vm.openFiltersDialog = openFiltersDialog;
    vm.closeFiltersDialog = closeFiltersDialog;
    vm.loadPropertiesPage = loadPropertiesPage;
    vm.search = search;
    vm.properties = [];
    vm.totalProperties = 0;
    vm.order = {};
    vm.propertiesPerPage = 25;
    vm.totalAmount = [25,50,100];
    vm.firstLoading = true;
    vm.filtersRendered = false;
    vm.clearFilters = clearFilters;
    vm.sortProperties = sortProperties;

    vm.pagination = {
      current: 1
    };

    function loadPropertiesPage(page, filterList) {
      PropertyService.loadPublicProperties((page-1)*vm.propertiesPerPage, (page-1)*vm.propertiesPerPage + vm.propertiesPerPage-1, filterList, vm.order)
        .then(function (data) {
          vm.properties = PropertyService.generatePropertiesPreviewData(data.propertyList);
          vm.totalProperties = data.count;
          vm.firstLoading = false;
        })
        .catch(function () {
          toastr.warning(
            translateFilter("hallocasa.global.error"));
        });
    }

    function loadFilters() {
      PropertyService.loadPropertiesFilters()
        .then(function (filtersData) {
          vm.filters = FiltersService.generateFiltersRender(filtersData.propertyFilters, filtersData.propertyFiltersRender);
        })
        .catch(function () {
          toastr.warning(
            translateFilter("hallocasa.global.error"));
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
        if(filterIndex !== -1){
          selectedFilters.splice(filterIndex, 1);
        }
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
        var emptyFilter = _.isUndefined(filterInformation.maxValue) &&
          _.isUndefined(filterInformation.minValue) &&
          _.isUndefined(filterInformation.maxCrcyValue) &&
          _.isUndefined(filterInformation.minCrcyValue);

        if(emptyFilter){
          selectedFilters.splice(filterIndex, 1);
        }
        else {
          selectedFilters[filterIndex] = filterInformation;
        }
      }
    }

    function openFiltersDialog($event) {
      filtersDialog = $mdDialog.show({
        contentElement: "#propertyFilters",
        targetEvent: $event,
        clickOutsideToClose: true,
        fullscreen: true,
        onComplete: function () {
          $scope.$broadcast('refreshSlider');
        }
      });
      filtersDialog.catch(function () {
        loadPropertiesPage(1, selectedFilters);
      });
    }

    function closeFiltersDialog() {
      $mdDialog.hide();
    }

    function search() {
      closeFiltersDialog();
      loadPropertiesPage(1, selectedFilters);
    }

    function clearFilters() {
      selectedFilters = [];
      $rootScope.$broadcast("FilterSystem:clearFilters");
    }

    function sortProperties(){
      loadPropertiesPage(1, selectedFilters);
    }

    loadPropertiesPage(1);
    loadFilters();
    listenFiltersChanges();
  }
})();
