(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .controller('PublicPropertyController', PublicPropertyController);

  /** @ngInject */
  function PublicPropertyController(PropertyService, ImageValidatorService, $location, $mdSidenav) {
    var vm = this;
    vm.validateImage = ImageValidatorService.validateBase64;
    vm.viewProperty = viewProperty;
    vm.loadPropertiesPage = loadPropertiesPage;
    vm.toggleFilters = toggleFilters;

    vm.properties = [];
    vm.totalProperties = 0;
    vm.propertiesPerPage = 10;
    vm.firstLoading = true;

    vm.pagination = {
      current: 1
    };

    function loadPropertiesPage(page) {
      PropertyService.loadPublicProperties((page-1)*vm.propertiesPerPage, (page-1)*vm.propertiesPerPage + vm.propertiesPerPage-1)
        .then(function (data) {
          vm.properties = data.properties;
          vm.totalProperties = data.totalProperties;
          vm.firstLoading = false;
        });
    }

    function viewProperty(id) {
      $location.url("/property");
      $location.search('id', id);
    }
    
    function toggleFilters() {
      $mdSidenav('propertyFilters').toggle();
    }
    
    loadPropertiesPage(1);
  }
})();