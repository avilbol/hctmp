(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .filter('dynamicCurrency', dynamicCurrency);

  function dynamicCurrency(CurrencyService, idSearchFilter, translateFilter, numberFilter, toastr, $sce) {
    var exchange, currency;

    function calculateExchange(inputCurrency, outputCurrency, loadConvertedCrcy) {
      var rate = getCurrencyRate(inputCurrency, outputCurrency);
      var convertedCurrency = inputCurrency.amount * rate;

      if(loadConvertedCrcy){
        return {
          "currencyID": outputCurrency.id,
          "amount": convertedCurrency
        }
      }
      convertedCurrency = numberFilter(convertedCurrency, 2);
      return $sce.trustAsHtml("<b>" + convertedCurrency + "</b> " + outputCurrency.abbreviation);
    }

    function getCurrencyRate(inputCurrency, outputCurrency) {
      inputCurrency = idSearchFilter(currency, inputCurrency.currencyID).abbreviation;
      outputCurrency = idSearchFilter(currency, outputCurrency.id).abbreviation;
      return exchange[inputCurrency][outputCurrency];
    }

    CurrencyService.loadCurrencyData()
      .then(function (data) {
        exchange = data.exchange;
        currency = data.currency;
      })
      .catch(function () {
        toastr.warning(
          translateFilter("Error.whenloadingexchangerates"));
      });

    return function(inputMoney, loadConvertedCrcy) {
      var outputCurrency = CurrencyService.getCurrentCurrency();
      if(exchange){
        try{
          return calculateExchange(inputMoney, outputCurrency, loadConvertedCrcy);
        }
        catch(err){
          return "...";
        }
      }
      else{
        return "...";
      }
    }
  }
})();
