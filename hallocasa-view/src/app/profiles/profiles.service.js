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
      validateUserData: validateUserData,
      loadProfilesFilters: loadProfilesFilters,
      loadProfiles: loadProfiles,
      generateProfilesPreviewData: generateProfilesPreviewData
    };

    var resources = {
      profileSave: $resource(backend_url + "user", {}, GenericRESTResource),
      userTypes: $resource(backend_url + "user_types", {}, GenericRESTResource),
      profileLoad: $resource(backend_url + "user/detail/:id", {}, GenericRESTResource),
      profiles: $resource(backend_url + "user/fetch_random", {}, GenericRESTResource),
      profilePublic: $resource(backend_url + "user/search", {}, GenericRESTResource),
      // profileFilters: $resource(backend_url + "property_filters", {}, GenericRESTResource),
      profileFiltersRender: $resource("/app/profiles/profiles-fields/render-data/profile_filter_render.json", {}, GenericRESTResource)
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

    function loadProfiles(excludeIdList, amount, imageFallback) {
      excludeIdList = excludeIdList ? excludeIdList : [];
      imageFallback = imageFallback ? imageFallback : "UserDefault";

      var data = {
        excludeIdList: excludeIdList,
        userNumber: amount ? amount : 10
      };

      return $q(function (success, reject) {
        resources.profiles.consult(data).$promise
          .then(function (profiles) {
            profiles = _.map(profiles, function (profile) {
              ImageValidatorService.validateOrFallback(user_images_url + '/mini/' + profile.imageLink, imageFallback)
                .then(function (image) {
                  profile.userImage = image;
                });
              return profile;
            });
            success(profiles)
          })
          .catch(function () {
            reject();
          });
      });
    }

    function loadPublicProfiles(start, finish, filterList, imageFallback) {
      $log.log("Cargar rango de propiedades: ("+start+" - "+finish+")");
      imageFallback = imageFallback ? imageFallback : "UserDefault";

      var resultRequest = {
        pageFrom: start+1,
        pageTo: finish+1,
        orderByMostRecent: false,
        orderByLessRecent: false,
        loadCount: true
      }

      filterList['resultRequest'] = resultRequest;
      return resources.profilePublic.consultObj(filterList).$promise;
    }

    function generateProfilesPreviewData(profiles, imageFallback) {
      return _.map(profiles, function (profile) {
        ImageValidatorService.validateOrFallback(user_images_url + '/mini/' + profile.imageLink, imageFallback)
        .then(function (image) {
          profile.userImage = image;
        });
        var mainDescription = _.find(profile.userDescriptions, function (description) {
          return description.language.id === profile.mainSpokenLanguage.id;
        });
        profile.description = mainDescription ? mainDescription.value : undefined;  

        return profile;
      });  
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
      data.properties = PropertyService.generatePropertiesPreviewData(data.properties);
      return data;
    }

    function loadProfilesFilters() {
      return resources.profileFiltersRender.query().$promise
    }

  }
})();
