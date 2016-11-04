(function() {
  'use strict';

  angular
    .module('HalloCasa.protractor')
    .service('ProtractorService', ProtractorService);

  function ProtractorService($q) {
    var service = {
      getOperations: getOperations
    };

    return service;

    function getOperations() {
      return $q(function (resolve) {
        resolve([
          "Suma",
          "Resta",
          "Multiplicación",
          "División"
        ]);
      });
    }
  }
})();
