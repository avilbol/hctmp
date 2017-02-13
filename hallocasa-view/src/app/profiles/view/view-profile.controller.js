(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('ViewProfileController', ViewProfileController);

  /** @ngInject */
  function ViewProfileController(ProfilesService, $location, ImageValidatorService, toastr, user_images_url, property_images_url,
                                 idSearchFilter) {
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

    function generatePropertiesPreviewData(properties, mainLanguage) {
      return _.map(properties, function (property) {
        var propertyPreview = {};
        propertyPreview.title = _.find(getFieldByID(2, property), function(title){
          return mainLanguage.id === title.data1.intVal
        });
        propertyPreview.type = property.propertyKey.propertyType.lang;
        var price = _.first(getFieldByID(5, property));
        price = price ? price : {};
        price.data1 = price.data1 ? price.data1 : {};
        price.data2 = price.data2 ? price.data2 : {};
        propertyPreview.price = {
          "amount": price.data1.doubleVal,
          "currencyID": price.data2.intVal
        };

        var meters = _.first(getFieldByID(6, property));
        meters = meters ? meters : {text:{}};

        propertyPreview.squareMeters = meters.text.intVal;
        propertyPreview.description = _.find(getFieldByID(3, property), function(description){
          return mainLanguage.id === description.data1.intVal
        });

        var image = _.find(getFieldByID(12, property), function (imageData) {
          return imageData.data2.boolVar;
        });

        image = image && image.data1 ? image.data1.strVal : undefined;

        ImageValidatorService.validateOrFallback(property_images_url + image, "PropertyDefault")
          .then(function (image) {
            propertyPreview.image = image;
          });

        return propertyPreview;
      });
    }

    function getFieldByID(fieldID, property){
      var field = _.partial(idSearchFilter, property.fieldList)(fieldID);
      return field && field.fieldValueList ? field.fieldValueList : undefined;
    }

    function validateUserData(data) {
      data.profile  = angular.isObject(data.profile) ? data.profile : {};
      data.profile.userTypes  = angular.isArray(data.profile.userTypes) ? data.profile.userTypes : [];
      data.profile.userLanguages  = angular.isArray(data.profile.userLanguages) ? data.profile.userLanguages : [];
      data.profile.userDescriptions  = angular.isArray(data.profile.userDescriptions) ? data.profile.userDescriptions : [];
      var mainLanguage = _.find(data.profile.userLanguages, _.property("isMainLanguage"));
      data.profile.mainLanguage = mainLanguage ? mainLanguage.language : undefined;

      ImageValidatorService.validateOrFallback(user_images_url + data.profile.imageLink, "UserDefault")
        .then(function (image) {
          data.profile.image = image;
        });

      data.properties = generatePropertiesPreviewData(data.properties, data.profile.mainLanguage);
      return data;
    }

    loadProfile();
  }
})();
