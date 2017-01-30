(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('EditProfileController', EditProfileController);

  /** @ngInject */
  function EditProfileController(ProfilesService, LocationService, LanguageService, toastr, $mdMedia, $mdDialog,
                                 $document, $location, translateFilter, ImageValidatorService, SessionService,
                                 FieldsService, user_images_url) {
    var vm = this;

    vm.loadStates = loadStates;
    vm.launchLoadImageDialog = launchLoadImageDialog;
    vm.loadCities = loadCities;
    vm.createProperty = createProperty;
    vm.editProperty = editProperty;
    vm.viewProperty = viewProperty;
    vm.deleteProperty = deleteProperty;
    vm.setMainLanguage = setMainLanguage;
    vm.goBack = goBack;
    vm.save = save;
    vm.propertyShowOptions = {view: true, edit: true, delete: true};

    function loadProfile(){
      var profileID = SessionService.getCurrentUser().id;
      ProfilesService.loadProfile(profileID)
        .then(function (data) {
          data = validateUserData(data);
          vm.userData = data;
          vm.profileForm = data.profile;
        })
        .catch(function () {
          //TODO: Traducción de mensaje de error
          toastr.warning("Error al datos del perfil");
          goBack();
        })
    }

    function validateUserData(data) {
      data.profile  = angular.isObject(data.profile) ? data.profile : {};
      data.profile.userTypes  = angular.isArray(data.profile.userTypes) ? data.profile.userTypes : [];
      data.profile.userLanguages  = angular.isArray(data.profile.userLanguages) ? data.profile.userLanguages : [];
      data.profile.userDescriptions  = angular.isArray(data.profile.userDescriptions) ? data.profile.userDescriptions : [];
      ImageValidatorService.validateOrFallback(user_images_url + data.profile.imageLink, "UserDefault")
        .then(function (image) {
          data.profile.base64Image = image;
        });
      data.properties  = angular.isArray(data.properties) ? data.properties : [];
      data.properties = _.map(data.properties, function (property) {
        property.images = angular.isArray(property.images) ? property.images : [];
        ImageValidatorService.validateOrFallback(property.images[0], "PropertyDefault")
          .then(function (image) {
            property.images[0] = image;
          });
        return property;
      });
      return data;
    }

    function launchLoadImageDialog(ev) {
      var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'));
      $mdDialog.show({
        controller: "ImageUploaderController",
        controllerAs: "vm",
        templateUrl: 'app/global/image-uploader/image-uploader.html',
        parent: $document.body,
        targetEvent: ev,
        clickOutsideToClose:true,
        fullscreen: useFullScreen
      }).then(function(result) {
        vm.profileForm.base64Image = result;
      }, function() {
        vm.profileForm.base64Image = undefined;
      });
    }

    function createProperty(event) {
      var locals = {title: "Properties.add.label"};
      launchPropertyFormDialog(event,locals)
        .then(function(property) {
          vm.userData.properties.push(property);
        });
    }

    function editProperty(event, property) {
      var locals = {
        title: "Properties.edit.label",
        property: property
      };
      launchPropertyFormDialog(event,locals)
        .then(function(editedProperty) {
          var index = vm.userData.properties.indexOf(property);
          vm.userData.properties[index] = editedProperty;
        });
    }

    function viewProperty(event, property) {
      var locals = {
        title: "Properties.view.label",
        property: property,
        readonly: true
      };
      launchPropertyFormDialog(event,locals);
    }

    function deleteProperty(event, property) {
      var confirm = $mdDialog.confirm()
        .title(translateFilter("Properties.title.modal"))
        .textContent(translateFilter("Properties.content.modal"))
        .ariaLabel('delete property')
        .targetEvent(event)
        .ok(translateFilter("Properties.delete.label"))
        .cancel(translateFilter("Properties.wizard.cancel.label"));

      $mdDialog.show(confirm).then(function() {
        var index = vm.userData.properties.indexOf(property);
        vm.userData.properties.splice(index,1);
      });
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
      var id = vm.profileForm.country.id;
      LocationService.getStateByID({country_id: id})
        .then(function (states) {
          vm.states = states;
        })
        .catch(function () {
          //TODO: Traducción de mensaje de error
          toastr.warning("Error al cargar Estados");
        });
    }

    function loadCities() {
      var id = vm.profileForm.state.id;
      LocationService.getCityByID({state_id: id})
        .then(function (cities) {
          vm.cities = cities;
        })
        .catch(function () {
          //TODO: Traducción de mensaje de error
          toastr.warning("Error al cargar ciudades");
        });
    }

    function loadLanguages(){
      LanguageService.getLanguages()
        .then(function (languages) {
          vm.languages = _.map(languages, function(language){
            return {
              language: language,
              isMainLanguage: false,
              value: ""
            };
          });
        })
        .catch(function () {
          //TODO: Traducción de mensaje de error
          toastr.warning("Error al cargar idiomas");
        });
    }

    function loadUserServices(){
      ProfilesService.getServices()
        .then(function (services) {
          vm.services = services;
        })
        .catch(function () {
          //TODO: Traducción de mensaje de error
          toastr.warning("Error al cargar servicios");
        });
    }

    function save(data, formID) {
      var formData = angular.copy(vm[data]);
      formData.userDescriptions = formData.userLanguages;
      ProfilesService.saveProfile(formData, formID)
        .then(function () {
          vm.userData[formID] = formData;
          //TODO: Traducción de mensaje de éxito
          toastr.success("Perfil guardado con éxito");
        })
        .catch(function () {
          //TODO: Traducción de mensaje de error
          toastr.warning("Error al guardar perfil");
        });

    }

    function setMainLanguage(languageId) {
      languageId = Number(languageId);
      _.each(vm.profileForm.userLanguages, function (languageObject) {
        languageObject.isMainLanguage = languageId === languageObject.language.id
      });
    }

    function goBack() {
      $location.url("/profile");
    }

    loadProfile();
    loadUserServices();
    loadLanguages();
    loadCountries();
  }
})();
