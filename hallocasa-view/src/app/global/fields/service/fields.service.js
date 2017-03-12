(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .service('FieldsService', FieldsService);

  function FieldsService(LanguageService, LocationService, CurrencyService,$q, $log) {
    var service = {
      generateFieldsRender: generateFieldsRender,
      loadOptionsByServiceId: loadOptionsByServiceId,
      processOptions: processOptions,
      getFieldPathByID: getFieldPathByID,
      getFieldByPath: getFieldByPath,
      generateFieldValueList: generateFieldValueList,
      consolidateFields: consolidateFields
    };

    var componentsIdentifiers = ["accordion_group", "repeater_group"];
    var totalFields, fieldsRendered;

    return service;

    function generateFieldsRender(fieldsDataList, fieldsRender) {
      totalFields = fieldsDataList.length;
      fieldsRendered = 0;
      fieldsRender = _.map(fieldsRender, function(tab){
        tab.fieldList = concatFieldsData(tab.fieldList, fieldsDataList);
        return tab;
      });

      $log.debug("Campos totales: "+totalFields+", Campos cargados: "+fieldsRendered);

      return fieldsRender;
    }

    function generateFieldValueList(fieldsRender) {
      var fieldValueList = [];
      var fieldsSearcher = function (fieldList) {
        _.each(fieldList, function (field) {
          if(isComponentField(field)){
            fieldsSearcher(field.fieldList);
            return;
          }

          field = _.pick(field, "id", "fieldValueList", "bdid", "data1Type", "data2Type", "data3Type", "textType", "propertyFieldValueType");
          var compactFieldValueList = [];
          for(var index in field.fieldValueList){
            var value = field.fieldValueList[index];
            if(_.isObject(value)){
              compactFieldValueList.push(value);
            }
          }
          field.fieldValueList = compactFieldValueList;

          if(!_.isEmpty(field.fieldValueList)){
            fieldValueList.push(field);
          }
        });
      };

      _.each(fieldsRender, function (tab) {
        fieldsSearcher(tab.fieldList);
      });

      return fieldValueList;
    }

    function concatFieldsData(fieldList, fieldsDataList){
      return _.chain(fieldList)
        .each(function(field, fieldIndex){
          if(isComponentField(field)){
            concatFieldsData(field.fieldList, fieldsDataList);
            return;
          }

          var fieldData = searchFieldById(field.id, fieldsDataList);
          if(!fieldData){
            delete fieldList[fieldIndex];
          }
          else {
            fieldList[fieldIndex] = _.extend(fieldData, field);
            fieldsRendered += 1;
          }
        })
        .compact()
        .value();
    }

    function isComponentField(field) {
      return _.contains(componentsIdentifiers, field.id);
    }

    function searchFieldById(fieldId, fieldsDataList) {
      return _.find(fieldsDataList, function (fieldData) {return fieldData.id === fieldId});
    }

    function loadOptionsByServiceId(serviceId, payload) {
      var servicePromise;
      switch (serviceId){
        case "Languages":
          servicePromise = LanguageService.getLanguages();
          break;
        case "States":
          servicePromise = LocationService.getStateByID(payload);
          break;
        case "Cities":
          servicePromise = LocationService.getCityByID(payload);
          break;
        case "Currency":
          servicePromise = CurrencyService.loadCurrency();
          break;
        default:
          $log.warn("No se reconoce el id del servicio:", serviceId);
          servicePromise = $q(function (resolve) {
            resolve([]);
          });
      }
      return servicePromise;
    }

    function detectOptionID(option) {
      option.identifier = (option.id || option.optionId);
      return option;
    }

    function cleanOption(option) {
      return _.pick(option, "identifier", "name", "data1");
    }

    function processOptions(optionsList, translationManagement) {
      var parseOptionString;

      switch (translationManagement){
        case "NONE":
          parseOptionString = function (option) {
            return option;
          };
          break;
        case "TOTAL":
          parseOptionString = function (option) {
            option.data1 = LanguageService.translate(option.data1, option.name);
            return option;
          };
          break;
        case "PARTIAL":
          parseOptionString = function (option) {
            option.data1 = option.dependsOnLang ? LanguageService.translate(option.data1, option.name) : option.data1;
            return option;
          };
          break;
        default:
          $log.warn("No se reconoce el tipo de manejo de traducción "+translationManagement, optionsList);
          parseOptionString = _.identity;
      }

      var parseFunction = _.compose(cleanOption, detectOptionID, parseOptionString);
      return _.map(optionsList, parseFunction);
    }

    function getFieldPathByID(fieldID, fieldRootScope) {
      var fieldPath;
      var fieldFound =_.find(fieldRootScope, function (tab, tabIndex) {
        fieldPath = getFieldPathInFieldsList(fieldID, tab.fieldList);
        var fieldFound = fieldPath.length > 0;
        if(fieldFound){
          fieldPath.unshift(tabIndex);
        }
        return fieldFound;
      });
      if(!fieldFound){
        $log.warn("No se pudo encontrar el campo con ID "+fieldID+" en la lista de campos", fieldRootScope);
      }
      return fieldPath;
    }

    function getFieldPathInFieldsList(fieldID, fieldList) {
      var fieldPath = [];
      _.find(fieldList, function (field, fieldIndex) {
        if(isComponentField(field)){
          fieldPath = getFieldPathInFieldsList(fieldID, field.fieldList);
          var fieldFound = fieldPath.length > 0;
          if(fieldFound){
            fieldPath.unshift(fieldIndex);
          }
          return fieldFound;
        }
        var isSoughtField = (field.id === fieldID);
        if(isSoughtField){
          fieldPath.unshift(fieldIndex);
        }
        return isSoughtField;
      });
      return fieldPath;
    }

    function getFieldByPath(fieldPath, fieldRootScope) {
      fieldPath = angular.copy(fieldPath);
      var tabIndex = fieldPath.shift();
      var fieldList = fieldRootScope[tabIndex].fieldList;
      var field;
      _.each(fieldPath, function (patch) {
        field = fieldList[patch];
        fieldList = field.fieldList;
      });
      return field;
    }

    function consolidateFields(filledFields, fieldList) {
      filledFields = angular.copy(filledFields);
      while(filledFields.length){
        var filledField = filledFields.pop();
        _.find(fieldList, function (field, index) {
          var foundField = field.id === filledField.id;
          if(foundField){
            fieldList[index].fieldValueList = filledField.fieldValueList;
          }
          return foundField;
        })
      }
      return fieldList;
    }
  }
})();
