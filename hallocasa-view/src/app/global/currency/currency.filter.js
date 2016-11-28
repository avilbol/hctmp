(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .filter('dynamicCurrency', dynamicCurrency);

  function dynamicCurrency(CurrencyService, idSearchFilter, numberFilter, toastr) {
    var exchange, currency;

    function calculateExchange(inputCurrency, outputCurrency) {
      var rate = getCurrencyRate(inputCurrency, outputCurrency);
      var convertedCurrency = numberFilter(inputCurrency.amount * rate, 2);

      return convertedCurrency + " " + outputCurrency.name;
    }

    function getCurrencyRate(inputCurrency, outputCurrency) {
      inputCurrency = idSearchFilter(currency, inputCurrency.currencyID).name;
      outputCurrency = idSearchFilter(currency, outputCurrency.id).name;
      return exchange[inputCurrency][outputCurrency];
    }

    CurrencyService.loadCurrencyData()
      .then(function (data) {
        exchange = data.exchange;
        currency = data.currency;
      })
      .catch(function (error) {
        //TODO: Traducción de mensaje de error
        toastr.info(error, "Error al cargar tasas de cambio");
      });

    return function(inputMoney) {
      var outputCurrency = CurrencyService.getCurrentCurrency();
      return exchange ? calculateExchange(inputMoney, outputCurrency) : "...";
    }
  }
})();
