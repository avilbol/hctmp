(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .controller('ViewPropertyController', ViewPropertyController);

  /** @ngInject */
  function ViewPropertyController(PropertyService, $location, translateFilter, toastr, LanguageService, $timeout) {
    var vm = this;
    vm.repaintMap = repaintMap;

    function repaintMap() {
      vm.refresh = false;
      $timeout(function () {
        vm.refresh = true;
      },300);
    }

    function loadProperty() {
      var propertyID = $location.search().id;
      if(!propertyID){
        $location.url("/property/browser");
      }
      else{
        PropertyService.loadProperty(propertyID)
          .then(function (property) {
            vm.property = PropertyService.generatePropertyDetailData(property);
            vm.profile = vm.property.user;

            loadLanguages(vm.property.languages);
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

    function loadLanguages(languages) {
      LanguageService.getLanguages().then(function(langs){
        vm.guidLanguages = _.map(languages, function(propertyLanguage){
          return _.find(langs, function (languageData) {
            return propertyLanguage.identifier === languageData.id;
          });
        });
        vm.guidLanguage = vm.property.mainLanguage.id;
      });
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
  }
})();
