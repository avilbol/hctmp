(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('myProfileController', myProfileController);

  /** @ngInject */
  function myProfileController(ProfilesService, LocationService, LanguageService, toastr, $mdMedia, $mdDialog,
                                 $document, $location, ImageValidatorService) {
    var vm = this;

    vm.validateImage = ImageValidatorService.validateBase64;
    vm.viewProperty = viewProperty;
    vm.editProfile = editProfile;
    vm.goBack = goBack;

    function loadProfile(){
      var profileID = 0;
      ProfilesService.loadProfile(profileID)
        .then(function (data) {
          data = validateUserData(data);
          vm.userData = data;
          loadCountries();
          loadStates();
          loadCities();
        })
        .catch(function (error) {
          //TODO: Traducción de mensaje de error
          toastr.info(error, "Error al datos del perfil");
          goBack();
        })
    }

    function validateUserData(data) {
      data.profile  = angular.isObject(data.profile) ? data.profile : {};
      data.profile.services  = angular.isArray(data.profile.services) ? data.profile.services : [];
      data.profile.languages  = angular.isArray(data.profile.languages) ? data.profile.languages : [];
      data.profile.description  = angular.isObject(data.profile.description) ? data.profile.description : {};
      data.properties  = angular.isArray(data.properties) ? data.properties : [];
      data.properties = _.map(data.properties, function (property) {
        property.images = angular.isArray(property.images) ? property.images : [];
        return property;
      });
      return data;
    }

    function viewProperty(event, property) {
      var locals = {
        title: "Properties.view.label",
        property: property,
        readonly: true
      };
      launchPropertyFormDialog(event,locals);
    }

    function launchPropertyFormDialog(ev, locals) {
      locals.title = locals.title ? locals.title : "";
      locals.property = locals.property ? locals.property : {};
      locals.readonly = locals.readonly ? locals.readonly : false;

      var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'));
      return $mdDialog.show({
        controller: "PropertyFormController",
        controllerAs: "vm",
        templateUrl: 'app/property/form/property-form.html',
        parent: $document.body,
        locals: locals,
        targetEvent: ev,
        clickOutsideToClose:false,
        fullscreen: useFullScreen
      })
    }

    function loadCities() {
      var id = vm.userData.profile.state;
      LocationService.getCityByID(id)
        .then(function (cities) {
          vm.cities = cities;
        })
        .catch(function (error) {
          //TODO: Traducción de mensaje de error
          toastr.info(error, "Error al cargar ciudades");
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
      var id = vm.userData.profile.country;
      LocationService.getStateByID(id)
        .then(function (states) {
          vm.states = states;
        })
        .catch(function (error) {
          //TODO: Traducción de mensaje de error
          toastr.info(error, "Error al cargar Estados");
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

    function loadUserServices(){
      ProfilesService.getServices()
        .then(function (services) {
          vm.services = services;
        })
        .catch(function (error) {
          //TODO: Traducción de mensaje de error
          toastr.info(error, "Error al cargar servicios");
        });
    }

    function goBack() {
      $location.url("/");
    }

    function editProfile() {
      $location.url("/profile/my-profile/edit");
    }

    loadProfile();
    loadUserServices();
    loadLanguages();
  }
})();
