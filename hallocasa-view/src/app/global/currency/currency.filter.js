(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .filter('dynamicCurrency', dynamicCurrency);

  function dynamicCurrency(CurrencyService, idSearchFilter, translateFilter, numberFilter, toastr) {
    var exchange, currency;

    function calculateExchange(inputCurrency, outputCurrency) {
      var rate = getCurrencyRate(inputCurrency, outputCurrency);
      var convertedCurrency = numberFilter(inputCurrency.amount * rate, 2);

      return convertedCurrency + " " + outputCurrency.abbreviation;
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

    return function(inputMoney) {
      var outputCurrency = CurrencyService.getCurrentCurrency();
      if(exchange){
        try{
          return calculateExchange(inputMoney, outputCurrency);
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
