(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('myProfileController', myProfileController);

  /** @ngInject */
  function myProfileController(ProfilesService, toastr, translateFilter, $mdMedia, $mdDialog, $document, $location,
                               SessionService, PropertyService) {
    var vm = this;

    vm.viewProperty = viewProperty;
    vm.editProfile = editProfile;
    vm.createProperty = createProperty;
    vm.goBack = goBack;

    function loadProfile(){
      var profileID = SessionService.getCurrentUser().id;
      ProfilesService.loadProfile(profileID)
        .then(function (data) {
          data = ProfilesService.validateUserData(data);
          vm.userData = data;
        })
        .catch(function () {
          toastr.warning(
            translateFilter("Error.whenloadingprofiledata"));
          goBack();
        })
    }

    function viewProperty(event, property) {
      var locals = {
        title: "Properties.view.label",
        property: property,
        readonly: true
      };
      launchPropertyFormDialog(event,locals);
    }

    function createProperty(event) {
      var locals = {title: "Properties.add.label"};
      launchPropertyFormDialog(event,locals)
        .then(function() {
          reloadProperties();
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

    function goBack() {
      $location.url("/");
    }

    function editProfile() {
      $location.url("/profile/my-profile/edit");
    }

    loadProfile();
  }
})();
