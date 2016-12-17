(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .service('FieldsService', FieldsService);

  function FieldsService(LanguageService, $q, $log) {
    var service = {
      generateFieldsRender: generateFieldsRender,
      loadOptionsByServiceId: loadOptionsByServiceId
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
  }
})();
