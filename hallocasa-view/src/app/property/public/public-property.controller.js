(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .controller('PublicPropertyController', PublicPropertyController);

  /** @ngInject */
  function PublicPropertyController(PropertyService, $mdSidenav, translateFilter, toastr, FiltersService, $rootScope, $scope) {
    var vm = this;
    var filtersSidernavPromise = $mdSidenav('propertyFilters', true);
    var filtersSidernav;
    var mainContainer = angular.element("#mainContainer");
    var selectedFilters = [];

    vm.loadPropertiesPage = loadPropertiesPage;
    vm.toggleFilters = toggleFilters;

    vm.properties = [];
    vm.totalProperties = 0;
    vm.propertiesPerPage = 100;
    vm.totalAmount = [100,150,200];
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
        var selectedIndex =  _.findIndex(selectedFilters, function (selectedFilter) {
          return selectedFilter.propertyFilter.filter.id === filterInformation.propertyFilter.filter.id;
        });

        if(_.isEmpty(filterInformation.selectedFilterOptions)){
          selectedFilters.splice(selectedIndex, 1);
        }
        else{
          if(selectedIndex === -1){
            selectedFilters.push(filterInformation);
          }
          else{
            selectedFilters[selectedIndex] = filterInformation;
          }
        }
        loadPropertiesPage(1, selectedFilters);
      });

      $scope.$on("$destroy", destroyListener);
    }

    filtersSidernavPromise.then(function(instance) {
      filtersSidernav = instance;
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
