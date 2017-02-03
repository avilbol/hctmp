(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .controller('PublicPropertyController', PublicPropertyController);

  /** @ngInject */
  function PublicPropertyController(PropertyService, ImageValidatorService, $mdSidenav) {
    var vm = this;
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
          data.properties = _.map(data.properties, function (property) {
            property.images = angular.isArray(property.images) ? property.images : [];
            ImageValidatorService.validateOrFallback(property.images[0], "PropertyDefault")
              .then(function (image) {
                property.images[0] = image;
              });
            return property;
          });

          vm.properties = data.properties;
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
