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
      loadProperties: loadProperties,
      loadPropertiesByUserID: loadPropertiesByUserID,
      loadFieldsData: loadFieldsData,
      saveProperty: saveProperty,
      generatePropertiesPreviewData: generatePropertiesPreviewData
    };

    var resources = {
      propertyFields: $resource(backend_url + "property_fields/filter_by_key", {}, GenericRESTResource),
      propertyLocations: $resource(backend_url + "property_locations", {}, GenericRESTResource),
      propertyProposals: $resource(backend_url + "property_proposals", {}, GenericRESTResource),
      propertyTypes: $resource(backend_url + "property_types", {}, GenericRESTResource),
      fieldsRender: $resource("/app/property/property-fields/render-data/fields_render.json", {}, GenericRESTResource),
      propertiesByUser: $resource(backend_url + "properties/by_user/:id", {}, GenericRESTResource),

      property: $resource(backend_url + "properties", {}, GenericRESTResource),

      properties: $resource(backend_url + "properties/fetch_random/:property_number", {}, GenericRESTResource),
      propertiesPublic: $resource(backend_url + "properties/search", {}, GenericRESTResource),
      propertyLoad: $resource("/mocks/property/loadProperty.json", {}, GenericRESTResource)
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
      return resources.properties.consult({"property_number": 10}).$promise;
    }

    function loadProperty(profileID) {
      $log.log("Cargar propiedad: (ID: "+profileID+")");
      return resources.propertyLoad.show().$promise;
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

    function getFieldByID(fieldID, property){
      var field = _.partial(idSearchFilter, property.fieldList)(fieldID);
      return field && field.fieldValueList ? field.fieldValueList : undefined;
    }

    function generatePropertiesPreviewData(properties, mainLanguage) {
      return _.map(properties, function (property) {
        var propertyPreview = {};
        propertyPreview.id = property.id;
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
  }
})();
