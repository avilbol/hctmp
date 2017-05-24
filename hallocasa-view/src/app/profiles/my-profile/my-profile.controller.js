(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('myProfileController', myProfileController);

  /** @ngInject */
  function myProfileController(ProfilesService, toastr, translateFilter, $mdMedia, $mdDialog, $document,
      $location, SessionService, PropertyService) {
    var vm = this;
    vm.propertyShowOptions = {view: true, edit: true, delete: true};

    vm.editProfile = editProfile;

    vm.editProperty = editProperty;
    vm.viewProperty = viewProperty;
    vm.deleteProperty = deleteProperty;

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
          vm.userData.properties = PropertyService.generatePropertiesPreviewData(properties);
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
