(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .service('PropertyService', PropertyService);

  function PropertyService($q, $resource, $log, GenericRESTResource, backend_url, property_images_url, user_images_url,
                           idSearchFilter, ImageValidatorService) {
    var service = {
      getPropertyTypes: getPropertyTypes,
      getLocation: getLocation,
      getProposals: getProposals,
      loadPublicProperties: loadPublicProperties,
      loadProperty: loadProperty,
      loadPropertyDetail: loadPropertyDetail,
      loadProperties: loadProperties,
      loadPropertiesByUserID: loadPropertiesByUserID,
      loadFieldsData: loadFieldsData,
      loadPropertyDetailFieldsData: loadPropertyDetailFieldsData,
      saveProperty: saveProperty,
      deleteProperty: deleteProperty,
      generatePropertiesPreviewData: generatePropertiesPreviewData,
      generatePropertyDetailData: generatePropertyDetailData,
      loadPropertiesFilters: loadPropertiesFilters
    };

    var resources = {
      propertyFields: $resource(backend_url + "property_fields/filter_by_key", {}, GenericRESTResource),
      propertyLocations: $resource(backend_url + "property_locations", {}, GenericRESTResource),
      propertyProposals: $resource(backend_url + "property_proposals", {}, GenericRESTResource),
      propertyTypes: $resource(backend_url + "property_types", {}, GenericRESTResource),
      propertyFormRender: $resource("/app/property/property-fields/render-data/property_form_render.json", {}, GenericRESTResource),
      propertyDetailRender: $resource("/app/property/property-fields/render-data/property_detail_render.json", {}, GenericRESTResource),
      propertiesByUser: $resource(backend_url + "properties/by_user/:id", {}, GenericRESTResource),
      property: $resource(backend_url + "properties/:id", {}, GenericRESTResource),
      propertyDelete: $resource(backend_url + "properties/delete/:id", {}, GenericRESTResource),
      propertyDetail: $resource(backend_url + "properties/detail/:id", {}, GenericRESTResource),
      properties: $resource(backend_url + "properties/fetch_random", {}, GenericRESTResource),
      propertiesPublic: $resource(backend_url + "properties/search", {}, GenericRESTResource),
      propertiesFilters: $resource(backend_url + "property_filters", {}, GenericRESTResource),
      propertyFiltersRender: $resource("/app/property/property-fields/render-data/property_filter_render.json", {}, GenericRESTResource)
    };

    return service;

    function getPropertyTypes() {
      return resources.propertyTypes.query().$promise;
    }

    function getLocation() {
      return resources.propertyLocations.query().$promise;
    }

    function getProposals() {
      return resources.propertyProposals.query().$promise;
    }

    function loadPublicProperties(start, finish) {
      $log.log("Cargar rango de propiedades: ("+start+" - "+finish+")");
      var filter = {
        filterList: [],
        resultRequest:{
          pageFrom: start+1,
          pageTo: finish+1,
          orderByMostRecent: false,
          orderByLessRecent: false,
          loadCount: true
        }
      };
      return resources.propertiesPublic.consultObj(filter).$promise;
    }

    function loadProperties(amount) {
      var data = {
        property_number: amount ? amount : 10
      };
      return resources.properties.query(data).$promise;
    }

    function loadProperty(profileID) {
      $log.log("Cargar propiedad: (ID: "+profileID+")");
      return resources.propertyDetail.show({"id":profileID}).$promise;
    }

    function loadFieldsData(propertyDeterminants) {
      $log.debug("Cargar campos de propiedades:", propertyDeterminants);
      var promises = {
        propertyFields: resources.propertyFields.consult(propertyDeterminants).$promise,
        propertyFormRender: resources.propertyFormRender.get().$promise
      };

      return $q.all(promises);
    }

    function loadPropertyDetailFieldsData(propertyDeterminants) {
      $log.debug("Cargar campos de propiedades:", propertyDeterminants);
      var promises = {
        propertyFields: resources.propertyFields.consult(propertyDeterminants).$promise,
        propertyFormRender: resources.propertyDetailRender.query().$promise
      };

      return $q.all(promises);
    }

    function saveProperty(propertyData) {
      return resources.property.create(propertyData).$promise;
    }

    function loadPropertiesByUserID(UserID) {
      return resources.propertiesByUser.query({id: UserID}).$promise;
    }

    function deleteProperty(id) {
      return resources.propertyDelete.delete({id: id}).$promise;
    }

    function loadPropertyDetail(id) {
      return resources.propertyDetail.show({id: id}).$promise;
    }

    function getFieldByID(fieldID, property){
      var field = _.partial(idSearchFilter, property.fieldList)(fieldID);
      return field && field.fieldValueList ? field.fieldValueList : undefined;
    }

    function getNumericValue(fieldID, property){
      var numericObj = _.first(getFieldByID(fieldID, property));
      numericObj = numericObj ? numericObj : {text:{}};
      return numericObj.text.intVal;
    }

    function generatePropertiesPreviewData(properties, mainLanguage) {
      return _.map(properties, function (property) {
        var propertyPreview = {};
        propertyPreview.id = property.id;
        propertyPreview.mainLanguage = {id: _.first(getFieldByID(61, property)).identifier};
        var targetLanguage = mainLanguage || propertyPreview.mainLanguage;
        propertyPreview.title = _.find(getFieldByID(2, property), function(title){
          return targetLanguage.id === title.data1.intVal
        });
        propertyPreview.type = property.propertyKey.propertyType.lang;
        propertyPreview.country = property.propertyKey.country;
        propertyPreview.propertyProposal = property.propertyKey.propertyProposal.lang;
        var price = _.first(getFieldByID(5, property));
        price = price ? price : {};
        price.data1 = price.data1 ? price.data1 : {};
        price.data2 = price.data2 ? price.data2 : {};
        propertyPreview.price = {
          "currencyID": price.data1.intVal,
          "amount": price.data2.doubleVal
        };

        propertyPreview.squareMeters = getNumericValue(6, property);
        propertyPreview.rooms = getNumericValue(17, property);
        propertyPreview.bathrooms = getNumericValue(18, property);

        propertyPreview.description = _.find(getFieldByID(3, property), function(description){
          return targetLanguage.id === description.data1.intVal
        });

        var image = _.find(getFieldByID(12, property), function (imageData) {
          return imageData.data2.boolVal;
        });

        image = image && image.data1 ? image.data1.strVal : undefined;

        ImageValidatorService.validateOrFallback(property_images_url + "/mini/" + image, "PropertyDefault")
          .then(function (image) {
            propertyPreview.image = image;
          });

        return propertyPreview;
      });
    }

    function loadByLang(langElementList, selectedLanguage){
      var selectedLangValue = _.find(langElementList, function(langElement){
        return langElement.data1.intVal === selectedLanguage.identifier;
      });
      return selectedLangValue.data2.strVal;
    }

    function generatePropertyDetailData(property) {
      var propertyDetail = {titles:{}, descriptions:{}, locationDescriptions:{}};
      propertyDetail.id = property.id;
      propertyDetail.mainLanguage = {id: _.first(getFieldByID(61, property)).identifier};
      propertyDetail.propertyKey = property.propertyKey;
      propertyDetail.languages = getFieldByID(1, property);
      propertyDetail.title = getFieldByID(2, property);
      propertyDetail.description = getFieldByID(3, property);
      propertyDetail.locationDescription = getFieldByID(4, property);
      propertyDetail.type = property.propertyKey.propertyType.lang;
      propertyDetail.country = property.propertyKey.country;
      propertyDetail.propertyProposal = property.propertyKey.propertyProposal.lang;

      var location = _.first(getFieldByID(10, property));
      location = location ? location : {};
      propertyDetail.location = {
        center: {
          latitude: location.data1 ? location.data1.doubleVal : 0,
          longitude: location.data2 ? location.data2.doubleVal : 0
        },
        zoom : location.data3 ? location.data3.doubleVal : 16
      };

      var price = _.first(getFieldByID(5, property));
      price = price ? price : {};
      price.data1 = price.data1 ? price.data1 : {};
      price.data2 = price.data2 ? price.data2 : {};
      propertyDetail.price = {
        "currencyID": price.data1.intVal,
        "amount": price.data2.doubleVal
      };

      _.each(propertyDetail.languages, function(propertyLanguage){
        propertyDetail.titles[propertyLanguage.identifier] = loadByLang(propertyDetail.title, propertyLanguage);
        propertyDetail.descriptions[propertyLanguage.identifier] = loadByLang(propertyDetail.description, propertyLanguage);
        propertyDetail.locationDescriptions[propertyLanguage.identifier] = loadByLang(propertyDetail.locationDescription, propertyLanguage);
      });

      var meters = _.first(getFieldByID(6, property));
      meters = meters ? meters : {text:{}};

      propertyDetail.squareMeters = meters.text.intVal;

      var propertyImages = getFieldByID(12, property);
      propertyDetail.images = [];
      _.each(propertyImages, function(image){
        ImageValidatorService.validateOrFallback(property_images_url + image.data1.strVal, "PropertyDefault")
          .then(function (image) {
            propertyDetail.images.push(image);
          });
      });

      propertyDetail.user = property.user;

      ImageValidatorService.validateOrFallback(user_images_url + property.user.imageLink, "ProfileDefault")
        .then(function (image) {
          propertyDetail.user.userImage = image;
        });

      propertyDetail.publishDate = property.publishDate;
      return propertyDetail;
    }

    function loadPropertiesFilters() {
      var promises = {
        propertyFilters: resources.propertiesFilters.query().$promise,
        propertyFiltersRender: resources.propertyFiltersRender.query().$promise
      };

      return $q.all(promises);
    }
  }
})();
