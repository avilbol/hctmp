(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .service('CurrencyService', CurrencyService);

  /** @ngInject */
  function CurrencyService ($q, $resource, GenericRESTResource, backend_url, localStorageService, $rootScope) {
    var service = {
      loadCurrencyData: loadCurrencyData,
      loadCurrency: loadCurrency,
      loadExchange: loadExchange,
      setCurrentCurrency: setCurrentCurrency,
      getCurrentCurrency: getCurrentCurrency,
      getCurrencyState: getCurrencyState
    };

    var resources = {
      currency: $resource(backend_url + "currencies", {}, GenericRESTResource),
      exchange: $resource(backend_url + "currency_exchange_data", {}, GenericRESTResource)
    };

    var currencyState = {};

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
      if(currencyState.currentCurrency && currencyState.currentCurrency.id === currency.id){ return; }
      currencyState.currentCurrency = currency;
      localStorageService.set("currentCurrency", currency.id);
      $rootScope.$broadcast("$changedCurrency");
    }

    function getCurrentCurrency() {
      return currencyState.currentCurrency;
    }

    function getCurrencyState() {
      return currencyState;
    }
  }
})();
