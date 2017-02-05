(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('myProfileController', myProfileController);

  /** @ngInject */
  function myProfileController(ProfilesService, toastr, $mdMedia, $mdDialog, $document, $location,
                                 ImageValidatorService, SessionService, user_images_url) {
    var vm = this;

    vm.viewProperty = viewProperty;
    vm.editProfile = editProfile;
    vm.goBack = goBack;

    function loadProfile(){
      var profileID = SessionService.getCurrentUser().id;
      ProfilesService.loadProfile(profileID)
        .then(function (data) {
          data = validateUserData(data);
          vm.userData = data;
        })
        .catch(function () {
          //TODO: Traducción de mensaje de error
          toastr.warning("Hubo un error al cargar los datos de su perfil, intentelo más tarde");
          goBack();
        })
    }

    function validateUserData(data) {
      data.profile  = angular.isObject(data.profile) ? data.profile : {};
      data.profile.userTypes  = angular.isArray(data.profile.userTypes) ? data.profile.userTypes : [];
      data.profile.userLanguages  = angular.isArray(data.profile.userLanguages) ? data.profile.userLanguages : [];
      data.profile.userDescriptions  = angular.isArray(data.profile.userDescriptions) ? data.profile.userDescriptions : [];
      var mainLanguage = _.find(data.profile.userLanguages, _.property("isMainLanguage"));
      data.profile.mainLanguage = mainLanguage ? mainLanguage.language.name : undefined;

      ImageValidatorService.validateOrFallback(user_images_url + data.profile.imageLink, "UserDefault")
        .then(function (image) {
          data.profile.image = image;
        });
      data.properties  = angular.isArray(data.properties) ? data.properties : [];
      data.properties = _.map(data.properties, function (property) {
        property.images = angular.isArray(property.images) ? property.images : [];
        property.images[0] = ImageValidatorService.validateOrFallback(property.images[0], "PropertyDefault");
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

    function goBack() {
      $location.url("/");
    }

    function editProfile() {
      $location.url("/profile/my-profile/edit");
    }

    loadProfile();
  }
})();
