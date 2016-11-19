(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .service('CurrencyService', CurrencyService);

  /** @ngInject */
  function CurrencyService ($q, $resource, GenericRESTResource) {
    var service = {
      loadCurrencyData: loadCurrencyData,
      loadCurrency: loadCurrency,
      loadExchange: loadExchange,
      setCurrentCurrency: setCurrentCurrency,
      getCurrentCurrency: getCurrentCurrency
    };

    var resources = {
      currency: $resource("/mocks/currency/currency.json", {}, GenericRESTResource),
      exchange: $resource("/mocks/currency/exchange.json", {}, GenericRESTResource)
    };

    var currentCurrency;

    return service;

    function loadCurrencyData() {
      var promises = {
        currency: loadCurrency(),
        exchange: loadExchange()
      };
      return $q.all(promises);
    }

    function loadCurrency() {
      return resources.currency.query().$promise;
    }

    function loadExchange() {
      return resources.exchange.get().$promise;
    }

    function setCurrentCurrency(currency) {
      currentCurrency = currency;
    }

    function getCurrentCurrency() {
      return currentCurrency;
    }

  }
})();
