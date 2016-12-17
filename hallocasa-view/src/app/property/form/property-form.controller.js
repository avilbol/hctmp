(function() {

	'use strict';

	angular
		.module('HalloCasa.property')
		.controller('PropertyFormController', PropertyFormController);

  /** @ngInject */
  function PropertyFormController($mdDialog, LocationService, LanguageService, PropertyService, toastr, $mdSidenav, $mdMedia,
                                  FieldsService, $timeout, FileReaderService, $log, title, property, readonly,
                                  $mdToast, translateFilter) {

		var vm = this;
    var propertyBase = {
      languages: [],
      images: [],
      description: {},
      location: {}
    };

    $log.log("Property: ",property);
    vm.isReadOnly = readonly ? readonly : false;
    vm.property = angular.equals({}, property) ? propertyBase : property;
    vm.title = title;

    vm.state = {
      "WIZARD_1": 1,
      "WIZARD_2": 2
    };
    vm.currentState = vm.state.WIZARD_1;

    vm.save = save;
    vm.closeDialog = closeDialog;
    vm.changeTemplate = changeTemplate;
    vm.toggleMenu = toggleMenu;
    vm.loadCountries = loadCountries;
    vm.loadStates = loadStates;
    vm.loadCities = loadCities;
    vm.handleTemplateLocation = handleTemplateLocation;
    vm.updateMapLocation = updateMapLocation;

    vm.smallDevice = ($mdMedia('sm') || $mdMedia('xs'));

    vm.templateURL = "app/property/form/tabs/basic-information.html";


    function closeDialog(){
      if(vm.isReadOnly){
        $mdDialog.cancel();
        return;
      }
      //TODO: Definir los textos para los otros idiomas
      var toast = $mdToast.simple()
        .textContent(translateFilter('Properties.wizard.close.confirm.text'))
        .action(translateFilter('Common.Label.Close'))
        .highlightAction(true)
        .hideDelay(5000);

      $mdToast.show(toast).then(function (response) {
        if (response === 'ok') {
          $mdDialog.cancel();
        }
      });
    }

    vm.changeState = function (state) {
      if(state === vm.state.WIZARD_2){
        loadFieldsData();
      }
      else {
        vm.currentState = state;
      }
    };

    function loadFieldsData(){
      var propertyDeterminants = _.pick(vm.property, "propertyType", "propertyLocation", "propertyProposal", "country");
      propertyDeterminants = _.mapObject(propertyDeterminants, function (val) { return {"id": val}; });

      return PropertyService.loadFieldsData(propertyDeterminants)
        .then(function (fieldsData) {
          $log.debug(fieldsData);
          vm.fieldsRender = FieldsService.generateFieldsRender(fieldsData.propertyFields, fieldsData.fieldsRender.tabList);

          vm.currentState = vm.state.WIZARD_2;
        })
        .catch(function () {
          //TODO: Traducción de mensaje de error
          toastr.info("Error al cargar atributos de la propiedad");
        });
    }

    function save() {
      var images = _.map(vm.property.images, function (image) {
        return image.lfFile;
      });

      FileReaderService.readFiles(images)
        .then(function (imagesEncoded) {
          vm.property.images = imagesEncoded;
          //TODO: Enviar datos de la propiedad al backend
          $mdDialog.hide(vm.property);
        })
        .catch(function (error) {
          //TODO: Traducción de mensaje de error
          toastr.info(error, "Error al cargar imágenes");
        });
    }

    function loadCountries() {
      LocationService.getCountries()
        .then(function (countries) {
          vm.countries = countries;
        })
        .catch(function (error) {
          //TODO: Traducción de mensaje de error
          toastr.info(error, "Error al cargar países");
        });
    }

    function loadStates() {
      var id = vm.property.location.country;
      LocationService.getStateByID(id)
        .then(function (states) {
          vm.states = states;
        })
        .catch(function (error) {
          //TODO: Traducción de mensaje de error
          toastr.info(error, "Error al cargar Estados");
        });
    }

    function loadPropertyTypes() {
      PropertyService.getPropertyTypes()
        .then(function (propertyTypes) {
          vm.propertyTypes = propertyTypes;
        })
        .catch(function (error) {
          //TODO: Traducción de mensaje de error
          toastr.info(error, "Error al cargar tipos de propiedades");
        });
    }

    function loadLocations() {
      PropertyService.getLocation()
        .then(function (locations) {
          vm.locations = locations;
        })
        .catch(function (error) {
          //TODO: Traducción de mensaje de error
          toastr.info(error, "Error al cargar ubicaciones");
        });
    }

    function loadProposals() {
      PropertyService.getProposals()
        .then(function (proposals) {
          vm.proposals = proposals;
        })
        .catch(function (error) {
          //TODO: Traducción de mensaje de error
          toastr.info(error, "Error al cargar comprar/arrendar");
        });
    }

    function loadLanguages(){
      LanguageService.getLanguages()
        .then(function (languages) {
          vm.languages = languages;
        })
        .catch(function (error) {
          //TODO: Traducción de mensaje de error
          toastr.info(error, "Error al cargar idiomas");
        });
    }

    function loadCurrencies(){
      PropertyService.getCurrencies()
        .then(function (currencies) {
          vm.currencies = currencies;
        })
        .catch(function (error) {
          //TODO: Traducción de mensaje de error
          toastr.info(error, "Error al cargar moneads");
        });
    }

    function changeTemplate(template){
      handleTemplateLocation(template);
      vm.templateURL = "app/property/form/tabs/" + template + ".html";
      toggleMenu();
    }

    function toggleMenu() {
      $mdSidenav('addPropertyMenu').toggle();
    }

    function updateMapLocation(city){
      vm.property.location.center.latitude = city.lat;
      vm.property.location.center.longitude = city.lng;
      vm.property.location.zoom = city.zoom;
    }

    function loadCities() {
      var id = vm.property.location.state;
      LocationService.getCityByID(id)
        .then(function (cities) {
          vm.cities = cities;
        })
        .catch(function (error) {
          //TODO: Traducción de mensaje de error
          toastr.info(error, "Error al cargar ciudades");
        });
    }

    function loadSavedLocation() {
      if(vm.property.location.country && vm.property.location.state){
        loadStates();
        loadCities();
      }
    }

    function loadSavedPictures() {
      //TODO: Cargar imágenes guardadas
    }

    function handleTemplateLocation(templateURL) {
      switch (templateURL){
        case "Location":
          $timeout(function () {
            vm.refresh = true;
          },300);
          break;
        default:
          vm.refresh = false;
      }
    }


    vm.print = function () {
      console.log(vm.fieldsRender);
    };

    loadCountries();
    loadPropertyTypes();
    loadLocations();
    loadProposals();
    loadLanguages();
    loadCurrencies();
    loadSavedLocation();
    loadSavedPictures();
  }
})();
