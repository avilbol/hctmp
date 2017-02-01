(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .service('ProfilesService', ProfilesService);

  function ProfilesService(GenericRESTResource, $resource, $q, $log, backend_url) {
    var service = {
      getServices: getServices,
      saveProfile: saveProfile,
      loadProfile: loadProfile,
      loadPublicProfiles: loadPublicProfiles,
      loadPublicProfile: loadPublicProfile
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
        profile: resources.profileLoad.show({id: profileID}).$promise
      });
    }

    function loadPublicProfile() {
      return resources.profilePublic.consult({
        "userNumber": 7
      }).$promise;
    }

    function loadPublicProfiles(start, finish) {
      $log.log("Cargar rango de perfiles: ("+start+" - "+finish+")");
      return resources.profilePublic.consult().$promise;
    }
  }
})();
