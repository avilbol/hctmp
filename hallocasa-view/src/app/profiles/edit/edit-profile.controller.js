(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('EditProfileController', EditProfileController);

  /** @ngInject */
  function EditProfileController(ProfilesService, LocationService, LanguageService, toastr, $mdMedia, $mdDialog,
                                 $document, $location, translateFilter,  SessionService, user_images_url) {
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
    var propertyList;

    function loadProfile(){
      var profileID = SessionService.getCurrentUser().id;
      ProfilesService.loadProfile(profileID)
        .then(function (data) {
          propertyList = data.properties;
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
        })
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
      property = getPropertyDataByID(property.id);
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
      property = getPropertyDataByID(property.id);
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

    function getPropertyDataByID(propertyID) {
      return _.find(propertyList, function (property) {
        return property.id === propertyID;
      })
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
