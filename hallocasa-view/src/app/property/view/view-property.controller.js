(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .controller('ViewPropertyController', ViewPropertyController);

  /** @ngInject */
  function ViewPropertyController(PropertyService, $location, translateFilter, toastr, LanguageService, $timeout,
                                  FieldsService) {
    var vm = this;
    vm.repaintMap = repaintMap;
    vm.currentImage = 0;

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
            loadPropertyDetailFields(property);
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

    function loadPropertyDetailFields(property) {
      var propertyDeterminants = _.pick(property.propertyKey, "propertyType", "propertyLocation", "propertyProposal", "country");
      propertyDeterminants.propertyType.id = propertyDeterminants.propertyType.group.id;
      vm.formatedPropertyDeterminants = _.mapObject(propertyDeterminants, function (val) { return val.id; });

      return PropertyService.loadPropertyDetailFieldsData(propertyDeterminants)
        .then(function (fieldsData) {
          if(property.fieldList){
            fieldsData.propertyFields = FieldsService.consolidateFields(property.fieldList, fieldsData.propertyFields);
          }
          vm.fieldsRender = FieldsService.generateFieldsRender(fieldsData.propertyFields, fieldsData.propertyFormRender);
          vm.propertyDetail = property;
        })
        .catch(function () {
          toastr.warning(
            translateFilter("Error.whenloadingpropertyattributes"));
        });
    }

    loadProperty();
  }
})();
