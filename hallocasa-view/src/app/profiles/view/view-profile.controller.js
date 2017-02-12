(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('ViewProfileController', ViewProfileController);

  /** @ngInject */
  function ViewProfileController(ProfilesService, $location, ImageValidatorService, toastr, user_images_url) {
    var vm = this;

    vm.validateImage = ImageValidatorService.validateBase64;

    function loadProfile() {
      var profileID = $location.search().id;
      if(!profileID){
        $location.url("/profile/browser");
      }
      else{
        ProfilesService.loadProfile(profileID)
          .then(function (data) {
            data = validateUserData(data);
            vm.userData = data;
          })
          .catch(function (error) {
            if(error.status === 404){
              //TODO: Traducción del mensaje de error
              toastr.error("No se ha podido encontrar el usuario");

            }
            if(error.status === 500){
              //TODO: Traducción del mensaje de error
              toastr.error("Hubo un error al intentar cargar el usuario");

            }

            $location.url("/profile/browser");
          });
      }
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

    loadProfile();
  }
})();
