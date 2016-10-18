(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .service('ProfilesService', ProfilesService);

  function ProfilesService(GenericRESTResource, $resource, $q, $log) {
    var service = {
      getServices: getServices,
      saveProfile: saveProfile,
      loadProfile: loadProfile,
      loadPublicProfiles: loadPublicProfiles
    };

    var resources = {
      profileSave: $resource("/mocks/profile/saveProfile.json", {}, GenericRESTResource),
      profileLoad: $resource("/mocks/profile/loadProfile.json", {}, GenericRESTResource),
      profilePublic: $resource("/mocks/profile/publicProfiles.json", {}, GenericRESTResource)
    };

    return service;

    function getServices() {
      return $q(function (resolve) {
        resolve([
          "Corredor",
          "Notario",
          "Tasador",
          "Traductor",
          "Administrador",
          "Experto",
          "Abogado"
        ]);
      });
    }

    function saveProfile(data, formID) {
      $log.log("Guardar perfil: (Formulario: ",formID, ", Datos: ",data, ")");
      return resources.profileSave.show().$promise;
    }

    function loadProfile(profileID) {
      $log.log("Cargar perfil: (ID: "+profileID+")");
      return resources.profileLoad.show().$promise;
    }

    function loadPublicProfiles(start, finish) {
      $log.log("Cargar rango de perfiles: ("+start+" - "+finish+")");
      return resources.profilePublic.query().$promise;
    }
  }
})();
