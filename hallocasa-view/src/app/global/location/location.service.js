(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .service('LocationService', LocationService );

  /** @ngInject */
  function LocationService ($q, $log) {
    var service = {
      getCountries: getCountries,
      getStateByID: getStateByID,
      getCityByID: getCityByID
    };

    return service;

    function getCountries() {
      return $q(function (resolve) {
        resolve([
          {id: 1, name: "Colombia"}
        ]);
      });
    }

    function getStateByID(id) {
      $log.log(id);
      return $q(function (resolve) {
        resolve([
          {id: 1, name: "Amazonas"},
          {id: 2, name: "Antioquia"},
          {id: 3, name: "Arauca"},
          {id: 4, name: "Atlántico"},
          {id: 5, name: "Bogotá D.C"},
          {id: 6, name: "Bolívar"},
          {id: 7, name: "Boyacá"},
          {id: 8, name: "Caldas"},
          {id: 9, name: "Caquetá"},
          {id: 10, name: "Casanare"},
          {id: 11, name: "Cauca"},
          {id: 12, name: "Cesar"},
          {id: 13, name: "Chocó"},
          {id: 14, name: "Córdoba"},
          {id: 15, name: "Cundinamarca"},
          {id: 16, name: "Güainia"},
          {id: 17, name: "Guaviare"},
          {id: 18, name: "Huila"},
          {id: 19, name: "La Guajira"},
          {id: 20, name: "Magdalena"},
          {id: 21, name: "Meta"},
          {id: 22, name: "Nariño"},
          {id: 23, name: "Norte de Santander"},
          {id: 24, name: "Putumayo"},
          {id: 25, name: "Quindío"},
          {id: 26, name: "Risaralda"},
          {id: 27, name: "San Andrés y Providencia"},
          {id: 28, name: "Santander"},
          {id: 29, name: "Sucre"},
          {id: 30, name: "Tolima"},
          {id: 31, name: "Valle del Cauca"},
          {id: 32, name: "Vaupés"},
          {id: 33, name: "Vichada"}
        ]);
      });
    }

    function getCityByID(id) {
      $log.log(id);
      return $q(function (resolve) {
        resolve([
          {id: 1, name: "Bogotá D.C", lat: 4.6449837, lng: -74.103700, zoom: 11},
          {id: 2, name: "Cali", lat: 3.432378, lng: -76.522994, zoom: 11},
          {id: 3, name: "Medellín", lat: 6.245511, lng: -75.580259, zoom: 12}
        ]);
      });
    }
  }
})();
