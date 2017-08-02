(function() {

	'use strict';

	angular
		.module('HalloCasa.property')
		.controller('PropertyFormController', PropertyFormController);

  /** @ngInject */
  function PropertyFormController($mdDialog, PropertyService, toastr, LocationService, $rootScope, property_images_url,
                                  FieldsService, SessionService, $mdToast, translateFilter, title, property, editMode,
                                  CurrencyService, DataCalcService) {

		var vm = this;
    var propertyBase = {
      languages: [],
      images: [],
      description: {},
      location: {}
    };

    CurrencyService.loadCurrency().then(function(currencies){
      vm.currencies = currencies;
    });

    vm.property = angular.equals({}, property) ? propertyBase : property;
    vm.title = title;
    vm.changeTab = changeTab;
    vm.previousDisabled = true;
    vm.showSubmit = editMode;
    var selectedTab = 0;

    vm.state = {
      "WIZARD_1": 1,
      "WIZARD_2": 2
    };
    vm.currentState = vm.state.WIZARD_1;

    vm.save = save;
    vm.closeDialog = closeDialog;
    vm.loadCountries = loadCountries;
    vm.handleTabLocation = handleTabLocation;

    function closeDialog(){
      var toast = $mdToast.simple()
        .textContent(translateFilter('Confirmation.ClosePropertyWizard'))
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

    function detectEditMode() {
      if(editMode){
        loadFieldsData();
        vm.currentState = vm.state.WIZARD_2;
        vm.editMode = true;
      }
    }

    function addCurrencyToUseInEditionMode(fieldList){
      var currencyToUse = DataCalcService.loadCurrentCurrency(fieldList);
      vm.formatedPropertyDeterminants.currencyToUse = currencyToUse;
      vm.property.propertyKey.currencyToUse = {id : currencyToUse};
    }

    function loadFieldsData(){
      vm.propertyDeterminants = _.pick(vm.property.propertyKey, "propertyType", "propertyLocation", "propertyProposal", "country", "currencyToUse");
      var propertyTypeGroup = vm.propertyDeterminants.propertyType.group.id;
      vm.formatedPropertyDeterminants = _.mapObject(vm.propertyDeterminants, function (val) { return val.id; });
      vm.formatedPropertyDeterminants.propertyImagesUrl = property_images_url;
      var payload = angular.copy(vm.propertyDeterminants);
      payload.propertyType.id = propertyTypeGroup;

      return PropertyService.loadFieldsData(payload)
        .then(function (fieldsData) {
          if(vm.property.fieldList){
            fieldsData.propertyFields = FieldsService.consolidateFields(vm.property.fieldList, fieldsData.propertyFields);
          }
          if(editMode){
            addCurrencyToUseInEditionMode(fieldsData.propertyFields);
          }
          vm.fieldsRender = FieldsService.generateFieldsRender(fieldsData.propertyFields, fieldsData.propertyFormRender.tabList);
          vm.currentState = vm.state.WIZARD_2;
        })
        .catch(function () {
          toastr.warning(
						translateFilter("Error.whenloadingpropertyattributes"));
        });
    }

    function save() {
      var propertyData = {
        user: SessionService.getCurrentUser(),
        propertyKey: vm.propertyDeterminants,
        fieldList: FieldsService.generateFieldValueList(vm.fieldsRender)
      };
      if(property && property.id){
        propertyData.id = property.id;
      }

      PropertyService.saveProperty(propertyData)
        .then(function () {
          toastr.success(translateFilter("property.wizard.create.succesful"));
          $mdDialog.hide();
        })
        .catch(function () {
          toastr.warning(
						translateFilter("Error.whensavingproperty"));
        });
    }

    function loadLocations() {
      PropertyService.getLocation()
        .then(function (locations) {
          vm.locations = locations;
        })
        .catch(function () {
          toastr.warning(
						translateFilter("Error.whenloadinglocations"));
        });
    }

    function loadProposals() {
      PropertyService.getProposals()
        .then(function (proposals) {
          vm.proposals = proposals;
        })
        .catch(function () {
          toastr.warning(
						translateFilter("Error.whenloadingbuyrentoptions"));
        });
    }

    function loadPropertyTypes() {
      PropertyService.getPropertyTypes()
        .then(function (propertyTypes) {
          vm.propertyTypes = _.filter(propertyTypes, function (propertyType) {
            return propertyType.isActive;
          });
        })
        .catch(function () {
          toastr.warning(
						translateFilter("Error.whenloadingpropertytypes"));
        });
    }

    function loadCountries() {
      LocationService.getCountries()
        .then(function (countries) {
          vm.countries = countries;
        })
        .catch(function () {
          toastr.warning(
						translateFilter("Error.whenloadingcountries"));
        });
    }

    function handleTabLocation(tabName, tabIndex) {
      selectedTab = tabIndex;
      validateNavigation();
      validateSubmit();
      switch (tabName){
        case "Location":
          $rootScope.$broadcast("RepaintMap");
          break;
      }
    }

    function changeTab(direction) {
      vm.fieldsRender[selectedTab].isActive = false;
      selectedTab = direction === "Next" ? selectedTab + 1: selectedTab - 1;
      vm.fieldsRender[selectedTab].isActive = true;
      validateNavigation();
      validateSubmit();
    }

    function validateNavigation() {
      vm.previousDisabled = selectedTab === 0;
      vm.nextDisabled = selectedTab === vm.fieldsRender.length - 1;
    }

    function validateSubmit() {
      vm.showSubmit = editMode ? true : vm.nextDisabled;
    }

    loadCountries();
    loadPropertyTypes();
    loadLocations();
    loadProposals();
    detectEditMode();
  }
})();
