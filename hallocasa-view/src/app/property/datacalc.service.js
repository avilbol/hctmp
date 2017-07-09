(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .service('DataCalcService', DataCalcService)
    .constant('monthly_rent_fiid', 60)
    .constant('landlord_fee_fiid', 57)
    .constant('marketprice_fiid', 5);

  function DataCalcService($log, monthly_rent_fiid, marketprice_fiid, landlord_fee_fiid, dynamicCurrencyFilter, numberFilter) {
    var service = {
      calculateRoi : calculateRoi,
      loadCurrentCurrency : loadCurrentCurrency
    };

    return service;

    
    function searchFieldById(fieldList, id){
      return _.find(fieldList, function(fiitem){
        return fiitem.id == id;
      });
    }

    function isValidRoiCalcField(field){
      return isValidCurrencyField(field);
    }

    function isValidCurrencyField(field){
      var valid = true;
      valid = valid && field;
      valid = valid && _.has(field, "fieldValueList");
      valid = valid && _.isArray(field.fieldValueList);
      valid = valid && !_.isEmpty(field.fieldValueList);
      valid = valid && _.has(field.fieldValueList[0], "data1");
      valid = valid && _.has(field.fieldValueList[0], "data2");
      valid = valid && _.has(field.fieldValueList[0].data1, "intVal");
      valid = valid && _.has(field.fieldValueList[0].data2, "doubleVal");
      return valid;
    }

    function toCrcyRep(field){
      return {
        "currencyID": field.fieldValueList[0].data1.intVal,
        "amount": field.fieldValueList[0].data2.doubleVal
      }
    }

    function toStandardCrcy(field){
      return dynamicCurrencyFilter(toCrcyRep(field), true);
    }

    function roiOf(monthlyRentField, landlordFeeField, marketpriceField){
      var monthlyRent = parseFloat(toStandardCrcy(monthlyRentField).amount);
      var landlordFee = parseFloat(toStandardCrcy(landlordFeeField).amount);
      var marketprice = parseFloat(toStandardCrcy(marketpriceField).amount);
      return (monthlyRent - landlordFee) * 12 / marketprice;
    }

    function calculateRoi(fieldList){
      var monthlyRentField = searchFieldById(fieldList, monthly_rent_fiid);
      var landlordFeeField = searchFieldById(fieldList, landlord_fee_fiid);
      var marketpriceField = searchFieldById(fieldList, marketprice_fiid);
      var selectedFields = [monthlyRentField, landlordFeeField, marketpriceField];
      var roiComputable = _.every(selectedFields, isValidRoiCalcField);
      var roi = roiComputable ? roiOf(monthlyRentField, landlordFeeField, marketpriceField) : undefined;
      return roi ? (numberFilter(roi * 100, 2) + '%') : undefined;
    }

    function loadCurrentCurrency(fieldList){
      var marketpriceField = searchFieldById(fieldList, marketprice_fiid);
      return isValidCurrencyField(marketpriceField) ? marketpriceField.fieldValueList[0].data1.intVal : null;
    }
  }
})();
