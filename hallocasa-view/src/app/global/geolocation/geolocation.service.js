(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .service('GeolocationService', GeolocationService );

  /** @ngInject */
  function GeolocationService ($q, $window) {
    var service = {
      getCurrentPosition: getCurrentPosition
    };

    return service;

    function getCurrentPosition() {
      return $q(function (resolve, reject) {
        if (!$window.navigator.geolocation) {
          reject('Geolocation not supported.');
        } else {
          $window.navigator.geolocation.getCurrentPosition(
            function (position) {
              resolve(position);
            },
            function (error) {
              reject(error);
            });
        }
      });
    }

  }

})();
