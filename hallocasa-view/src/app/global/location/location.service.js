(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .service('LocationService', LocationService );

  /** @ngInject */
  function LocationService (GenericRESTResource, backend_url, $resource) {
    var service = {
      getCountries: getCountries,
      getStateByID: getStateByID,
      getCityByID: getCityByID,
      getTelephonePrefixes: getTelephonePrefixes,
      getNeighborhoodsByCityID: getNeighborhoodsByCityID
    };

    var resources = {
      countries: $resource(backend_url + "countries", {}, GenericRESTResource),
      states: $resource(backend_url + "states", {}, GenericRESTResource),
      cities: $resource(backend_url + "cities", {}, GenericRESTResource),
      neighborhoods: $resource(backend_url + "neighborhoods", {}, GenericRESTResource),
      telephonePrefixes: $resource(backend_url + "telephone_prefixes", {}, GenericRESTResource)
    };

    return service;

    function getCountries() {
      return resources.countries.query().$promise;
    }

    function getStateByID(query) {
      return resources.states.query(query).$promise;
    }

    function getCityByID(query) {
      return resources.cities.query(query).$promise;
    }

    function getTelephonePrefixes() {
      return resources.telephonePrefixes.query().$promise;
    }

    function getNeighborhoodsByCityID(query) {
      return resources.neighborhoods.query(query).$promise;
    }
  }
})();
