(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .service('PropertyService', PropertyService);

  function PropertyService($q, $resource, $log, GenericRESTResource) {
    var service = {
      getPropertyTypes: getPropertyTypes,
      getLocation: getLocation,
      getBuyRent: getBuyRent,
      getCurrencies: getCurrencies,
      loadPublicProperties: loadPublicProperties
    };

    var resources = {
      propertiesPublic: $resource("/mocks/property/publicProperties.json", {}, GenericRESTResource)
    };

    return service;

    function getPropertyTypes() {
      return $q(function (resolve) {
        resolve([
          {id: 1, name: "Lote"},
          {id: 2, name: "Centro comercial"},
          {id: 4, name: "Hotel"},
          {id: 5, name: "Finca con Casa"},
          {id: 6, name: "Apartamento"},
          {id: 9, name: "Penthouse"},
          {id: 10, name: "Bodega"},
          {id: 11, name: "Garaje"},
          {id: 12, name: "Restaurante"},
          {id: 14, name: "Oficina"},
          {id: 15, name: "Casa Unifamiliar"},
          {id: 16, name: "Edificio de Apartamentos"},
          {id: 17, name: "Aeropuerto"},
          {id: 18, name: "Fábrica"},
          {id: 19, name: "Escuela"},
          {id: 20, name: "Teatro"},
          {id: 21, name: "Jardín Infantil"},
          {id: 22, name: "Estacionamiento"},
          {id: 23, name: "Hospital"}
        ]);
      });
    }

    function getLocation() {
      return $q(function (resolve) {
        resolve([
          {id: 1, name: "Urbano"},
          {id: 2, name: "Suburbio"},
          {id: 3, name: "Rural"}
        ]);
      });
    }

    function getBuyRent() {
      return $q(function (resolve) {
        resolve([
          {id: 1, name: "Arrendar"},
          {id: 2, name: "Vender"}
        ]);
      });
    }

    function getCurrencies() {
      return $q(function (resolve) {
        resolve([
          {id: 1, name: "COP - Peso colombiano"},
          {id: 2, name: "EUR - Euro"},
          {id: 3, name: "USD - Dólar estadounidense"},
          {id: 4, name: "CAD - Dólar canadiense"},
          {id: 5, name: "GBP - Libra británica"},
          {id: 6, name: "CHF - Franco suizo"},
          {id: 7, name: "AUD - Dolar australiano"}
        ]);
      });
    }

    function loadPublicProperties(start, finish) {
      $log.log("Cargar rango de propiedades: ("+start+" - "+finish+")");
      return resources.propertiesPublic.get().$promise;
    }
  }
})();
