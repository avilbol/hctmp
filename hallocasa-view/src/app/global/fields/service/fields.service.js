(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .service('FieldsService', FieldsService);

  function FieldsService(LanguageService, $q, $log) {
    var service = {
      generateFieldsRender: generateFieldsRender,
      loadOptionsByServiceId: loadOptionsByServiceId,
      processOptions: processOptions
    };

    var componentsIdentifiers = ["accordion_group", "repeater_group"];

    return service;

    function generateFieldsRender(fieldsDataList, fieldsRender) {
      return _.map(fieldsRender, function(tab){
        tab.fieldList = concatFieldsData(tab.fieldList, fieldsDataList);
        return tab;
      });
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

    function loadOptionsByServiceId(serviceId) {
      var servicePromise;
      switch (serviceId){
        case "Languages":
          servicePromise = LanguageService.getLanguages();
          break;
        default:
          $log.warn("No se reconoce el id del servicio:", serviceId);
          servicePromise = $q(function (resolve) {
            resolve([]);
          });
      }
      return servicePromise;
    }

    function parseUnicode(string) {
      try{
        string = decodeURIComponent(angular.fromJson('"' + string.replace(/\"/g, '\\"') + '"'));
      }
      catch(error) {
        $log.debug("Error al decodificar el texto" + string, error);
      }
      return string;
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
            option.name = parseUnicode(option.name);
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
  }
})();
