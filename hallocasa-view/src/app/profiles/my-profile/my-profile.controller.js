(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('myProfileController', myProfileController);

  /** @ngInject */
  function myProfileController(ProfilesService, toastr, $mdMedia, $mdDialog, $document, $location, SessionService) {
    var vm = this;

    vm.viewProperty = viewProperty;
    vm.editProfile = editProfile;
    vm.goBack = goBack;

    function loadProfile(){
      var profileID = SessionService.getCurrentUser().id;
      ProfilesService.loadProfile(profileID)
        .then(function (data) {
          data = ProfilesService.validateUserData(data);
          vm.userData = data;
        })
        .catch(function () {
          //TODO: Traducción de mensaje de error
          toastr.warning("Hubo un error al cargar los datos de su perfil, intentelo más tarde");
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

    function goBack() {
      $location.url("/");
    }

    function editProfile() {
      $location.url("/profile/my-profile/edit");
    }

    loadProfile();
  }
})();
