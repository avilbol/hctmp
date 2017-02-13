(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .filter('dynamicCurrency', dynamicCurrency);

  function dynamicCurrency(CurrencyService, idSearchFilter, numberFilter, toastr) {
    var exchange, currency;

    function calculateExchange(inputCurrency, outputCurrency) {
      var rate = getCurrencyRate(inputCurrency, outputCurrency);
      var convertedCurrency = numberFilter(inputCurrency.amount * rate, 2);

      return convertedCurrency + " " + outputCurrency.name;
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
        //TODO: Traducción de mensaje de error
        toastr.warning("Error al cargar tasas de cambio");
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
