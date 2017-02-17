(function() {

	'use strict';

	angular
		.module('HalloCasa.property')
		.controller('PropertyFormController', PropertyFormController);

  /** @ngInject */
  function PropertyFormController($mdDialog, PropertyService, toastr, $mdSidenav, $mdMedia, LocationService,
                                  FieldsService, SessionService, $log, title, property, readonly,
                                  $mdToast, translateFilter, $rootScope, property_images_url) {

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
    vm.handleTemplateLocation = handleTemplateLocation;

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
      vm.propertyDeterminants = _.pick(vm.property.propertyKey, "propertyType", "propertyLocation", "propertyProposal", "country");
      var propertyTypeGroup = vm.propertyDeterminants.propertyType.group.id;
      vm.formatedPropertyDeterminants = _.mapObject(vm.propertyDeterminants, function (val) { return val.id; });
      vm.formatedPropertyDeterminants.propertyImagesUrl = property_images_url;
      var payload = angular.copy(vm.propertyDeterminants);
      payload.propertyType.id = propertyTypeGroup;

      return PropertyService.loadFieldsData(payload)
        .then(function (fieldsData) {
          $log.debug(fieldsData);
          if(vm.property.fieldList){
            fieldsData.propertyFields = FieldsService.consolidateFields(vm.property.fieldList, fieldsData.propertyFields);
          }
          vm.fieldsRender = FieldsService.generateFieldsRender(fieldsData.propertyFields, fieldsData.fieldsRender.tabList);

          vm.currentState = vm.state.WIZARD_2;
        })
        .catch(function () {
          //TODO: Traducción de mensaje de error
          toastr.warning("Error al cargar atributos de la propiedad");
        });
    }

    function save() {
      var propertyData = {
        user: SessionService.getCurrentUser(),
        propertyKey: vm.propertyDeterminants,
        fieldList: FieldsService.generateFieldValueList(vm.fieldsRender)
      };

      PropertyService.saveProperty(propertyData)
        .then(function () {
          toastr.success(translateFilter("property.wizard.create.succesful"));
        })
        .catch(function () {
          //TODO: Traducción de mensaje de error
          toastr.warning("Error guardar propiedad");
        });
    }

    function loadLocations() {
      PropertyService.getLocation()
        .then(function (locations) {
          vm.locations = locations;
        })
        .catch(function () {
          //TODO: Traducción de mensaje de error
          toastr.warning("Error al cargar ubicaciones");
        });
    }

    function loadProposals() {
      PropertyService.getProposals()
        .then(function (proposals) {
          vm.proposals = proposals;
        })
        .catch(function () {
          //TODO: Traducción de mensaje de error
          toastr.warning("Error al cargar comprar/arrendar");
        });
    }

    function loadPropertyTypes() {
      PropertyService.getPropertyTypes()
        .then(function (propertyTypes) {
          vm.propertyTypes = propertyTypes;
        })
        .catch(function () {
          //TODO: Traducción de mensaje de error
          toastr.warning("Error al cargar tipos de propiedades");
        });
    }

    function loadCountries() {
      LocationService.getCountries()
        .then(function (countries) {
          vm.countries = countries;
        })
        .catch(function () {
          //TODO: Traducción de mensaje de error
          toastr.warning("Error al cargar tipos países");
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

    function handleTemplateLocation(templateURL) {
      switch (templateURL){
        case "Location":
          $rootScope.$broadcast("RepaintMap");
          break;
      }
    }

    loadCountries();
    loadPropertyTypes();
    loadLocations();
    loadProposals();
  }
})();
