(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .controller('PublicPropertyController', PublicPropertyController);

  /** @ngInject */
  function PublicPropertyController(PropertyService, $mdSidenav, translateFilter, toastr, FiltersService, $rootScope, $scope, $mdComponentRegistry) {
    var vm = this;
    var filtersSidernavPromise = $mdComponentRegistry.when('propertyFilters');
    var filtersSidernav;
    var mainContainer = angular.element("#mainContainer");
    var selectedFilters = [];

    vm.loadPropertiesPage = loadPropertiesPage;
    vm.toggleFilters = toggleFilters;

    vm.properties = [];
    vm.totalProperties = 0;
    vm.propertiesPerPage = 10;
    vm.totalAmount = [10,20,50,100];
    vm.firstLoading = true;

    vm.pagination = {
      current: 1
    };

    function loadPropertiesPage(page, filterList) {
      PropertyService.loadPublicProperties((page-1)*vm.propertiesPerPage, (page-1)*vm.propertiesPerPage + vm.propertiesPerPage-1, filterList)
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

    function toggleFilters() {
      filtersSidernav.toggle();
      if(filtersSidernav.isOpen()){
        mainContainer.addClass("stop-scrolling");
        mainContainer.bind('touchmove', function(e){e.preventDefault()});
      }

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

        loadPropertiesPage(1, selectedFilters);
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

    filtersSidernavPromise.then(function() {
      if(filtersSidernav){
        filtersSidernav.destroy();
      }
      filtersSidernav = $mdSidenav("propertyFilters");
      $scope.$on("$destroy", filtersSidernav.destroy);
      filtersSidernav.onClose(function () {
        mainContainer.removeClass("stop-scrolling");
        mainContainer.unbind('touchmove');
      });
    });

    loadPropertiesPage(1);
    loadFilters();
    listenFiltersChanges();
  }
})();
