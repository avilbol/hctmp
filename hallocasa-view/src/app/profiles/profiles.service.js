(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .service('ProfilesService', ProfilesService);

  function ProfilesService(GenericRESTResource, $resource, $q, $log, backend_url, PropertyService, user_images_url,
                           ImageValidatorService) {
    var service = {
      getServices: getServices,
      saveProfile: saveProfile,
      loadProfile: loadProfile,
      loadPublicProfiles: loadPublicProfiles,
      loadPublicProfile: loadPublicProfile,
      validateUserData: validateUserData
    };

    var resources = {
      profileSave: $resource(backend_url + "user", {}, GenericRESTResource),
      userTypes: $resource(backend_url + "user_types", {}, GenericRESTResource),
      profileLoad: $resource(backend_url + "user/detail/:id", {}, GenericRESTResource),
      profilePublic: $resource(backend_url + "user/fetch_random", {}, GenericRESTResource)
    };

    return service;

    function getServices() {
      return resources.userTypes.query().$promise
    }

    function saveProfile(data, formID) {
      $log.log("Guardar perfil: (Formulario: ",formID, ", Datos: ",data, ")");
      return resources.profileSave.save(data).$promise;
    }

    function loadProfile(profileID) {
      return $q.all({
        profile: resources.profileLoad.show({id: profileID}).$promise,
        properties: PropertyService.loadPropertiesByUserID(profileID)
      });
    }

    function loadPublicProfile() {
      return resources.profilePublic.consult({
        "userNumber": 7
      }).$promise;
    }

    function loadPublicProfiles(start, finish) {
      $log.log("Cargar rango de perfiles: ("+start+" - "+finish+")");
      return resources.profilePublic.consult({
        "userNumber": 7
      }).$promise;
    }

    function validateUserData(data) {
      data.profile  = angular.isObject(data.profile) ? data.profile : {};
      data.profile.userTypes  = angular.isArray(data.profile.userTypes) ? data.profile.userTypes : [];
      data.profile.userLanguages  = angular.isArray(data.profile.userLanguages) ? data.profile.userLanguages : [];
      data.profile.userDescriptions  = angular.isArray(data.profile.userDescriptions) ? data.profile.userDescriptions : [];
      var mainLanguage = _.find(data.profile.userLanguages, _.property("isMainLanguage"));
      data.profile.mainLanguage = mainLanguage ? mainLanguage.language : undefined;
      data.profile.telephoneNumber = data.profile.telephoneNumber ? Number(data.profile.telephoneNumber) : undefined;

      ImageValidatorService.validateOrFallback(user_images_url + data.profile.imageLink, "UserDefault")
        .then(function (image) {
          data.profile.image = image;
          data.profile.base64Image = image;
        });
      data.properties = PropertyService.generatePropertiesPreviewData(data.properties, data.profile.mainLanguage);
      return data;
    }
  }
})();
