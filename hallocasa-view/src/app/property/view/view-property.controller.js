(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .controller('ViewPropertyController', ViewPropertyController);

  /** @ngInject */
  function ViewPropertyController(PropertyService, $location, translateFilter, toastr, LanguageService, $timeout,
                                  FieldsService, Mailto, LocaleService, $translate) {
    var vm = this;
    vm.repaintMap = repaintMap;
    vm.openDialogRenren = openDialogRenren;
    vm.currentImage = 0;
    vm.mailInfo = '';
    vm.sharedMailInfo = '';
    vm.sharedURL = '';

    function loadEmailInfo() {
      var recepient = vm.profile.email;
      var newPathPropertyEn = $location.$$host + '/property?id=' + vm.property.id + '&lang=en';
      var newPathPropertyEs = $location.$$host + '/property?id=' + vm.property.id + '&lang=es';
      var newPathPropertyDe = $location.$$host + '/property?id=' + vm.property.id + '&lang=de';
      var options = {
        subject: "HalloCasa: " + vm.property.titles[vm.guidLanguage],
        body: "Hi, I am interested in your property: " + newPathPropertyEn + "\n" +
              "Hola, estoy interesado en su propiedad: " + newPathPropertyEs + "\n" +
              "Hallo, Ich bin an Ihrer Immobilie interessiert: " + newPathPropertyDe
      };

      vm.mailInfo = Mailto.url(recepient, options);
    }

    function sharedEmailInfo() {
      // var recepient = vm.profile.email;
      var newPathPropertyEn = $location.$$host + '/property?id=' + vm.property.id + '&lang=en';
      var newPathPropertyEs = $location.$$host + '/property?id=' + vm.property.id + '&lang=es';
      var newPathPropertyDe = $location.$$host + '/property?id=' + vm.property.id + '&lang=de';
      var options = {
        subject: "HalloCasa: " + vm.property.titles[vm.guidLanguage],
        body: "New Real Estate Object: " + newPathPropertyEn + "\n" +
              "Neues Immobilienobjekt" + newPathPropertyEs + "\n" +
              "Nueva Propiedad Inmobiliaria" + newPathPropertyDe
      };

      vm.sharedMailInfo = Mailto.url('', options);
    }

    function openDialogRenren(){
      var left = Math.round((screen.width/2)-(w/2));
      var top = Math.round((screen.height/2)-(h/2));
      var url = 'http://widget.renren.com/dialog/share?resourceUrl=' + vm.sharedURL + '&title=' + vm.property.titles[vm.guidLanguage] + '&description=' + vm.property.descriptions[vm.guidLanguage] + '&lang=' + LocaleService.getCurrentLenguage();
      window.open(url,'popup','width=600,height=600' + ', top=' + top + ', left=' + left); 
      return false;
      
    }

    function loadURLShared() {
      var url = 'http://www.hallocasa.com:64645' + '/property?id=' + vm.property.id + '&lang=' + LocaleService.getCurrentLenguage();
      vm.sharedURL = url;
    }

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
        
        loadURLShared();

        loadEmailInfo();

        sharedEmailInfo();
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
