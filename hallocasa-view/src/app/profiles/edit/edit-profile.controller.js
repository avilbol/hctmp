(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('EditProfileController', EditProfileController);

  /** @ngInject */
  function EditProfileController(ProfilesService, LocationService, LanguageService, toastr, $mdMedia, $mdDialog,
                                 $document, $location, translateFilter,  SessionService, user_images_url, PropertyService) {
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
    vm.urlRegex = "^[0-9a-zA-Z]([-.\w]*[0-9a-zA-Z])*(:(0-9)*)*(\/?)([a-zA-Z0-9\-\.\?\,\:\'\/\\\+=&amp;%\$#_]*)$";

    function loadProfile(){
      var profileID = SessionService.getCurrentUser().id;
      ProfilesService.loadProfile(profileID)
        .then(function (data) {
          data = ProfilesService.validateUserData(data);
          vm.languageId = data.profile.mainLanguage ? data.profile.mainLanguage.id : undefined;
          data.profile.userLanguages  = data.profile.userDescriptions;

          vm.userData = data;
          vm.profileForm = data.profile;
          if(vm.profileForm.country){
            loadStates();
            loadCities();
          }
          setMainLanguage();
        })
        .catch(function () {
          toastr.warning(
            translateFilter("Error.whenloadingprofiledata"));
          goBack();
        });
    }

    function reloadProperties() {
      var profileID = SessionService.getCurrentUser().id;
      var mainLanguage = vm.userData.profile.mainLanguage;
      PropertyService.loadPropertiesByUserID(profileID)
        .then(function (properties) {
          vm.userData.properties = PropertyService.generatePropertiesPreviewData(properties, mainLanguage);
        })
        .catch(function () {
          //TODO: Traducción de mensaje de error
          toastr.warning("Hubo un error al recargar sus propiedades, intentelo más tarde");
          goBack();
        });
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
        .then(function() {
          reloadProperties();
        });
    }

    function editProperty(event, property) {
      PropertyService.loadPropertyDetail(property.id)
        .then(function (propertyDetail) {
          var locals = {
            title: "Properties.edit.label",
            property: propertyDetail,
            editMode: true
          };
          launchPropertyFormDialog(event,locals)
            .then(function() {
              reloadProperties();
            });
        })
        .catch(function () {
          toastr.warning(translateFilter("Error.whenloadingproperty"));
        });
    }

    function viewProperty(event, property) {
      $location.url('/property?id='+property.id);
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
        PropertyService.deleteProperty(property.id)
          .then(function () {
            //TODO: Traducción de mensaje de éxito
            toastr.success("Propiedad eliminada con éxito");
            reloadProperties();
          })
          .catch(function () {
            //TODO: Traducción de mensaje de error
            toastr.warning("Error al eliminar propiedad");
          });
      });
    }

    function launchPropertyFormDialog(ev, locals) {
      locals.title = locals.title ? locals.title : "";
      locals.property = locals.property ? locals.property : {};
      locals.editMode = locals.editMode ? locals.editMode : false;

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
          toastr.warning(
            translateFilter("Error.whenloadingcountries"));
        });
    }

    function loadStates() {
      var id = vm.profileForm.country.id;
      LocationService.getStateByID({country_id: id})
        .then(function (states) {
          vm.states = states;
        })
        .catch(function () {
          toastr.warning(
            translateFilter("Error.whenloadingstates"));
        });
    }

    function loadCities() {
      var id = vm.profileForm.state.id;
      LocationService.getCityByID({state_id: id})
        .then(function (cities) {
          vm.cities = cities;
        })
        .catch(function () {
          toastr.warning(
            translateFilter("Error.whenloadingcities"));
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
          toastr.warning(
            translateFilter("Error.whenloadinglanguages"));
        });
    }

    function loadUserServices(){
      ProfilesService.getServices()
        .then(function (services) {
          vm.services = services;
        })
        .catch(function () {
          toastr.warning(
            translateFilter("Error.whenloadinguserservices"));
        });
    }

    function save(data, formID) {
      var formData = angular.copy(vm[data]);
      formData.userDescriptions = formData.userLanguages;
      var imageLink = user_images_url + formData.imageLink;
      formData.base64Image = imageLink !== formData.base64Image ? formData.base64Image : undefined;
      ProfilesService.saveProfile(formData, formID)
        .then(function () {
          vm.userData[formID] = formData;
          toastr.success(
            translateFilter("Alert.profilesavedsuccesfully"));
        })
        .catch(function () {
          toastr.warning(
            translateFilter("Error.whensavingprofile")
          );
        });

    }

    function setMainLanguage() {
      var languageId = Number(vm.languageId);
      _.each(vm.profileForm.userLanguages, function (languageObject) {
        languageObject.isMainLanguage = languageId === languageObject.language.id
      });
    }

    function goBack() {
      $location.url("/profile/my-profile");
    }

    loadProfile();
    loadUserServices();
    loadLanguages();
    loadCountries();
  }
})();
