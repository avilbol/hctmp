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
      getTelephonePrefixes: getTelephonePrefixes
    };

    var resources = {
      countries: $resource(backend_url + "countries", {}, GenericRESTResource),
      states: $resource(backend_url + "states", {}, GenericRESTResource),
      cities: $resource(backend_url + "cities", {}, GenericRESTResource),
      telephonePrefixes: $resource(backend_url + "telephone_prefixes", {}, GenericRESTResource)
    };

    return service;

    function getCountries() {
      return resources.countries.query().$promise;
    }

    function getStateByID(id) {
      return resources.states.query(id).$promise;
    }

    function getCityByID(id) {
      return resources.cities.query(id).$promise;
    }

    function getTelephonePrefixes() {
      return resources.telephonePrefixes.query().$promise;
    }
  }
})();
