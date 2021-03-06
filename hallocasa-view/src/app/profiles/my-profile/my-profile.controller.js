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

    vm.activePropertiesTab = function(){
      var tab = $location.search().tab;
      return tab === "my-properties";
    }

    vm.activeProfileTab = function(){
      var tab = $location.search().tab;
      return tab === "my-profile";
    }

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
      var activeSession = SessionService.validateActiveSession("PublicProfile.PreAuthorize.loginNeeded");
      if(!activeSession){
        return
      }
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
      var activeSession = SessionService.validateActiveSession("PublicProfile.PreAuthorize.loginNeeded");
      if(!activeSession){
        return
      }
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
            toastr.success(translateFilter("Properties.Delete.Succesful"));
            reloadProperties();
          })
          .catch(function () {
            toastr.warning(translateFilter("Error.whendeletingproperty"));
          });
      });
    }

    function createProperty(event) {
      var activeSession = SessionService.validateActiveSession("PublicProfile.PreAuthorize.loginNeeded");
      if(!activeSession){
        return
      }
      var locals = {title: "Properties.add.label"};
      launchPropertyFormDialog(event,locals)
        .then(function() {
          reloadProperties();
        });
    }

    function reloadProperties() {
      var profileID = SessionService.getCurrentUser().id;
      PropertyService.loadPropertiesByUserID(profileID)
        .then(function (properties) {
          vm.userData.properties = PropertyService.generatePropertiesPreviewData(properties);
        })
        .catch(function () {
          toastr.warning(translateFilter("Error.whenreloadingproperties"));
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
