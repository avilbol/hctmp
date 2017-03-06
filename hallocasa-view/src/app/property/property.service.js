(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .service('PropertyService', PropertyService);

  function PropertyService($q, $resource, $log, GenericRESTResource, backend_url, property_images_url, idSearchFilter,
                           ImageValidatorService) {
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
      saveProperty: saveProperty,
      deleteProperty: deleteProperty,
      generatePropertiesPreviewData: generatePropertiesPreviewData,
      generatePropertyDetailData: generatePropertyDetailData
    };

    var resources = {
      propertyFields: $resource(backend_url + "property_fields/filter_by_key", {}, GenericRESTResource),
      propertyLocations: $resource(backend_url + "property_locations", {}, GenericRESTResource),
      propertyProposals: $resource(backend_url + "property_proposals", {}, GenericRESTResource),
      propertyTypes: $resource(backend_url + "property_types", {}, GenericRESTResource),
      fieldsRender: $resource("/app/property/property-fields/render-data/fields_render.json", {}, GenericRESTResource),
      propertiesByUser: $resource(backend_url + "properties/by_user/:id", {}, GenericRESTResource),
      property: $resource(backend_url + "properties/:id", {}, GenericRESTResource),
      propertyDetail: $resource(backend_url + "properties/detail/:id", {}, GenericRESTResource),
      properties: $resource(backend_url + "properties/fetch_random", {}, GenericRESTResource),
      propertiesPublic: $resource(backend_url + "properties/search", {}, GenericRESTResource)
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
          loadCount: false
        }
      };
      return resources.propertiesPublic.consult(filter).$promise;
    }

    function loadProperties() {
      return resources.properties.query({"property_number": 10}).$promise;
    }

    function loadProperty(profileID) {
      $log.log("Cargar propiedad: (ID: "+profileID+")");
      return resources.propertyDetail.show({"id":profileID}).$promise;
    }

    function loadFieldsData(propertyDeterminants) {
      $log.debug("Cargar campos de propiedades:", propertyDeterminants);
      var promises = {
        propertyFields: resources.propertyFields.consult(propertyDeterminants).$promise,
        fieldsRender: resources.fieldsRender.get().$promise
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
      return resources.property.delete({id: id}).$promise;
    }

    function loadPropertyDetail(id) {
      return resources.propertyDetail.show({id: id}).$promise;
    }

    function getFieldByID(fieldID, property){
      var field = _.partial(idSearchFilter, property.fieldList)(fieldID);
      return field && field.fieldValueList ? field.fieldValueList : undefined;
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
        var price = _.first(getFieldByID(5, property));
        price = price ? price : {};
        price.data1 = price.data1 ? price.data1 : {};
        price.data2 = price.data2 ? price.data2 : {};
        propertyPreview.price = {
          "currencyID": price.data1.intVal,
          "amount": price.data2.doubleVal
        };

        var meters = _.first(getFieldByID(6, property));
        meters = meters ? meters : {text:{}};

        propertyPreview.squareMeters = meters.text.intVal;
        propertyPreview.description = _.find(getFieldByID(3, property), function(description){
          return targetLanguage.id === description.data1.intVal
        });

        var image = _.find(getFieldByID(12, property), function (imageData) {
          return imageData.data2.boolVal;
        });

        image = image && image.data1 ? image.data1.strVal : undefined;

        ImageValidatorService.validateOrFallback(property_images_url + image, "PropertyDefault")
          .then(function (image) {
            propertyPreview.image = image;
          });

        return propertyPreview;
      });
    }

    function generatePropertyDetailData(property, mainLanguage) {
        var propertyDetail = {};
        propertyDetail.id = property.id;
        propertyDetail.mainLanguage = {id: _.first(getFieldByID(61, property)).identifier};
        var targetLanguage = mainLanguage || propertyDetail.mainLanguage;
        propertyDetail.title = _.find(getFieldByID(2, property), function(title){
          return targetLanguage.id === title.data1.intVal
        });
        propertyDetail.type = property.propertyKey.propertyType.lang;
        var price = _.first(getFieldByID(5, property));
        price = price ? price : {};
        price.data1 = price.data1 ? price.data1 : {};
        price.data2 = price.data2 ? price.data2 : {};
        propertyDetail.price = {
          "currencyID": price.data1.intVal,
          "amount": price.data2.doubleVal
        };

        var meters = _.first(getFieldByID(6, property));
        meters = meters ? meters : {text:{}};

        propertyDetail.squareMeters = meters.text.intVal;
        propertyDetail.description = _.find(getFieldByID(3, property), function(description){
          return targetLanguage.id === description.data1.intVal
        });

        var image = _.find(getFieldByID(12, property), function (imageData) {
          return imageData.data2.boolVal;
        });

        image = image && image.data1 ? image.data1.strVal : undefined;

        ImageValidatorService.validateOrFallback(property_images_url + image, "PropertyDefault")
          .then(function (image) {
            propertyDetail.image = image;
          });

        return propertyDetail;
      }
  }
})();
