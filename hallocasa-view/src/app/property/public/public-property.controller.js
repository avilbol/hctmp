(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .controller('PublicPropertyController', PublicPropertyController);

  /** @ngInject */
  function PublicPropertyController(PropertyService, $mdSidenav) {
    var vm = this;
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

    function loadPropertiesPage(page) {
      PropertyService.loadPublicProperties((page-1)*vm.propertiesPerPage, (page-1)*vm.propertiesPerPage + vm.propertiesPerPage-1)
        .then(function (data) {
          vm.properties = PropertyService.generatePropertiesPreviewData(data.propertyList);
          vm.totalProperties = data.totalProperties;
          vm.firstLoading = false;
        });
    }

    function toggleFilters() {
      $mdSidenav('propertyFilters').toggle();
    }

    loadPropertiesPage(1);
  }
})();
