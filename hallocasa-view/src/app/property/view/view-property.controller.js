(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .controller('ViewPropertyController', ViewPropertyController);

  /** @ngInject */
  function ViewPropertyController(PropertyService, $location,
      ImageValidatorService, translateFilter, toastr, LanguageService, property_images_url, user_images_url) {
    var vm = this;

    vm.validateImage = ImageValidatorService.validateBase64;
    vm.viewProfile = viewProfile;
    vm.propertyImagesUrl = property_images_url;
    vm.userImagesUrl = user_images_url;

    function loadProperty() {
      var propertyID = $location.search().id;
      if(!propertyID){
        $location.url("/property/browser");
      }
      else{
        PropertyService.loadProperty(propertyID)
          .then(function (property) {
            vm.property = PropertyService.generatePropertyDetailData(property);
            LanguageService.getLanguages().then(function(langs){
              vm.guidLanguages = _.map(vm.property.languages, function(propertyLanguage){
                return _.extend({}, propertyLanguage,
                  {name : _.first(_.where(langs, {id: propertyLanguage.identifier})).name});
              });
              vm.guidLanguage = _.find(vm.guidLanguages, function(guidLanguage){
                return guidLanguage.identifier === vm.property.mainLanguage.id;
              })
            });
          })
          .catch(function (error) {
            if(error.status === 404){
              toastr.error(
                translateFilter("Alert.propertynotfound"));
            }
            if(error.status === 500){
              toastr.error(
                translateFilter("Error.whenloadingproperty"));
            }
            $location.url("/property/browser");
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
      $location.url("/profile?id="+vm.property.user.id);
    }
  }
})();
