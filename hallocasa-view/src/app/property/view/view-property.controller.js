(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .controller('ViewPropertyController', ViewPropertyController);

  /** @ngInject */
  function ViewPropertyController(PropertyService, $location, ImageValidatorService) {
    var vm = this;

    vm.validateImage = ImageValidatorService.validateBase64;
    vm.viewProfile = viewProfile;

    function loadProperty() {
      var propertyID = $location.search().id;
      if(!propertyID){
        $location.url("/property/browser");
      }
      else{
        PropertyService.loadProperty(propertyID)
          .then(function (property) {
            vm.property = property;
          });
      }
    }
    loadProperty();

    vm.optionsCarousel = {
      sourceProp: 'src',
      visible: 3,
      perspective: 35,
      startSlide: 0,
      border: 0,
      loop: true,
      controls: true,
      clicking: true
    };

    function viewProfile() {
      $location.url("/profile");
      $location.search('id', vm.property.contact.id);
    }
  }
})();
