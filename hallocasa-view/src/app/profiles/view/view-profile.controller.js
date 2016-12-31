(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('ViewProfileController', ViewProfileController);

  /** @ngInject */
  function ViewProfileController(ProfilesService, $location, ImageValidatorService, LocationService, toastr) {
    var vm = this;

    vm.validateImage = ImageValidatorService.validateBase64;

    function loadProfile() {
      var profileID = $location.search().id;
      if(!profileID){
        $location.url("/profile/browser");
      }
      else{
        ProfilesService.loadProfile(profileID)
          .then(function (userData) {
            vm.userData = userData;
            loadStates();
            loadCities();
          });
      }
    }

    function loadCities() {
      var id = vm.userData.profile.state;
      LocationService.getCityByID(id)
        .then(function (cities) {
          vm.cities = cities;
        })
        .catch(function () {
          //TODO: Traducción de mensaje de error
          toastr.warning("Error al cargar ciudades");
        });
    }

    function loadCountries() {
      LocationService.getCountries()
        .then(function (countries) {
          vm.countries = countries;
        })
        .catch(function () {
          //TODO: Traducción de mensaje de error
          toastr.warning("Error al cargar países");
        });
    }

    function loadStates() {
      var id = vm.userData.profile.country;
      LocationService.getStateByID(id)
        .then(function (states) {
          vm.states = states;
        })
        .catch(function () {
          //TODO: Traducción de mensaje de error
          toastr.warning("Error al cargar Estados");
        });
    }

    loadProfile();
    loadCountries();
  }
})();
