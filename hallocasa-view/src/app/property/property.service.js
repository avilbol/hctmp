(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .service('PropertyService', PropertyService);

  function PropertyService($q, $resource, $log, GenericRESTResource, backend_url, property_images_url, idSearchFilter,
                           ImageValidatorService, LocationService, translateFilter) {
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
      return resources.propertiesPublic.consultObj(filter).$promise;
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

    function getOptSingleField(fieldID, property){
      var field = _.first(getFieldByID(fieldID, property));
      return field ? field : {"text":{}, "data1":{}, "data2":{}, "data3":{}};
    }

    function getSingleFromDropdown(fieldID, property){
      var field = _.partial(idSearchFilter, property.fieldList)(fieldID);
      if(!field){
        return undefined;
      }
      var dropdownOptionList = field.dropdownOptionGroup.dropdownOptionList;
      var fieldValueId = _.first(field.fieldValueList).identifier;
      var result = _.find(dropdownOptionList, function(dropdownOption){
        return dropdownOption.optionId === fieldValueId;
      });
      result.translate = field.dropdownOptionGroup.translationManagement != 'NONE';
      return result;
    }

    function getGroupFromDropdown(fieldID, property){
      var field = _.partial(idSearchFilter, property.fieldList)(fieldID);
      if(!field){
        return undefined;
      }
      var dropdownOptionList = field.dropdownOptionGroup.dropdownOptionList;
      var fieldValues = field.fieldValueList;
      var resultStr = "";
      _.each(fieldValues, function(fieldValueItem){
        var result = _.find(dropdownOptionList, function(dropdownOption){
          return dropdownOption.optionId === fieldValueItem.identifier;
        });
        result.translate = field.dropdownOptionGroup.translationManagement != 'NONE';
        resultStr += ((resultStr == '') ? '' : ', ') + translateFilter(result.data1);
      });
      return resultStr;
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

    function loadByLang(langElementList, selectedLanguage){
      var selectedLangValue = _.find(langElementList, function(langElement){
        return langElement.data1.intVal === selectedLanguage.identifier;
      });
      return selectedLangValue.data2.strVal;
    }

    function generatePropertyDetailData(property, selectedLanguage) {
        var propertyDetail = {titles:{}, descriptions:{}, locationDescriptions:{}};
        propertyDetail.id = property.id;
        propertyDetail.mainLanguage = {id: _.first(getFieldByID(61, property)).identifier};
        var targetLanguage = selectedLanguage || propertyDetail.mainLanguage;
        propertyDetail.languages = getFieldByID(1, property);
        propertyDetail.title = getFieldByID(2, property);
        propertyDetail.description = getFieldByID(3, property);
        propertyDetail.locationDescription = getFieldByID(4, property);
        var countryId = property.propertyKey.country.id;
        var stateId = _.first(getFieldByID(7, property)).identifier;
        var cityId = _.first(getFieldByID(8, property)).identifier;
        propertyDetail.type = property.propertyKey.propertyType.lang;
        propertyDetail.address = getOptSingleField(9, property).text;
        var location = _.first(getFieldByID(10, property));
        location = location ? location : {};
        propertyDetail.location = {
          "lat" : location.data1 ? location.data1.doubleVal : 0,
          "lng" : location.data2 ? location.data2.doubleVal : 0,
          "zoom" : location.data3 ? location.data3.doubleVal : 0
        };
        propertyDetail.video = {"link" : _.first(getFieldByID(13, property))};
        propertyDetail.neighborhood = getSingleFromDropdown(15, property);
        propertyDetail.rooms = getOptSingleField(17, property).text.intVal;
        propertyDetail.bathooms = getOptSingleField(18, property).text.intVal;
        propertyDetail.condition = getSingleFromDropdown(19, property);
        propertyDetail.furnished = getOptSingleField(20, property).text.boolVal;
        propertyDetail.floor = getOptSingleField(21, property).text.intVal;
        propertyDetail.optionalFeatures = getGroupFromDropdown(22, property);
        propertyDetail.suitableFor = getGroupFromDropdown(23, property);
        propertyDetail.parkingSpots = getOptSingleField(24, property).text.intVal;
        propertyDetail.basement = getOptSingleField(25, property).text.intVal;
        propertyDetail.balconyRooftop = getOptSingleField(27, property).text.intVal;
        propertyDetail.gardenTerrace = getOptSingleField(28, property).text.intVal;
        propertyDetail.availableFrom = getOptSingleField(29, property).text.dateVal;
        propertyDetail.rented = getOptSingleField(30, property).text.boolVal;
        propertyDetail.metersBuilt = getOptSingleField(35, property).text.intVal;
        propertyDetail.security = getGroupFromDropdown(36, property);

        var estratoOptions = [
          getSingleFromDropdown(37, property),
          getSingleFromDropdown(38, property),
          getSingleFromDropdown(39, property),
          getSingleFromDropdown(40, property),
          getSingleFromDropdown(41, property),
          getSingleFromDropdown(42, property),
          getSingleFromDropdown(43, property)
        ];

        propertyDetail.estrato = _.find(estratoOptions, function(estratoOption){
          return estratoOption;
        });
        propertyDetail.kindsOfRoad = getGroupFromDropdown(44, property);
        propertyDetail.heating = getSingleFromDropdown(45, property);
        propertyDetail.numberOfFloors = getSingleFromDropdown(46, property);
        propertyDetail.drinkingWater = getGroupFromDropdown(47, property);
        propertyDetail.sewageWater = getGroupFromDropdown(48, property);
        propertyDetail.yearOfConstruction = getSingleFromDropdown(49, property);
        propertyDetail.methodOfConstruction = getSingleFromDropdown(50, property);
        propertyDetail.typeOfSoil = getGroupFromDropdown(51, property);
        propertyDetail.agriculture = getGroupFromDropdown(52, property);
        propertyDetail.lastModernization = getSingleFromDropdown(53, property);
        propertyDetail.priceDevelopment = getSingleFromDropdown(54, property);
        propertyDetail.inclination = getGroupFromDropdown(55, property);
        propertyDetail.agentFee = getSingleFromDropdown(56, property);

        propertyDetail.monthlyAgentFeesForTheLandlord = getOptSingleField(57, property).text.doubleVal;
        propertyDetail.additionalFeesForTheLandlord = getOptSingleField(58, property).text.doubleVal;
        propertyDetail.annualTaxRateOnTheProperty = getSingleFromDropdown(59, property);

        LocationService.getCountries()
          .then(function(countries){
            propertyDetail.country = _.find(countries, function(country){
              return country.id === countryId;
            });
          });
        LocationService.getStateByID({"country_id" : countryId})
          .then(function(states){
            propertyDetail.state = _.find(states, function(state){
              return state.id === stateId;
            });
          });
        LocationService.getCityByID({"state_id" : stateId})
          .then(function(cities){
            propertyDetail.city = _.find(cities, function(city){
              return city.id === cityId;
            });
          });

        _.each(propertyDetail.languages, function(propertyLanguage){
          propertyDetail.titles[propertyLanguage.identifier] = loadByLang(propertyDetail.title, propertyLanguage);
          propertyDetail.descriptions[propertyLanguage.identifier] = loadByLang(propertyDetail.description, propertyLanguage);
          propertyDetail.locationDescriptions[propertyLanguage.identifier] = loadByLang(propertyDetail.locationDescription, propertyLanguage);
        });
        var price = _.first(getFieldByID(5, property));
        price = price ? price : {};
        price.data1 = price.data1 ? price.data1 : {};
        price.data2 = price.data2 ? price.data2 : {};
        propertyDetail.price = {
          "currencyID": price.data1.intVal,
          "amount": price.data2.doubleVal
        };

        var monthlyRent = _.first(getFieldByID(60, property));
        monthlyRent = monthlyRent ? monthlyRent : {};
        monthlyRent.data1 = monthlyRent.data1 ? monthlyRent.data1 : {};
        monthlyRent.data2 = monthlyRent.data2 ? monthlyRent.data2 : {};
        propertyDetail.monthlyRent = {
          "currencyID": monthlyRent.data1.intVal,
          "amount": monthlyRent.data2.doubleVal
        };
        propertyDetail.user = property.user;

        var meters = _.first(getFieldByID(6, property));
        meters = meters ? meters : {text:{}};

        propertyDetail.squareMeters = meters.text.intVal;
        propertyDetail.description = getFieldByID(3, property);

        var images = getFieldByID(12, property);
        propertyDetail.images = [];
        _.each(images, function(image){
          propertyDetail.images.push({"src" : property_images_url + image.data1.strVal});
        });
        return propertyDetail;
      }
  }
})();
