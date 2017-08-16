(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .service('ImageValidatorService', ImageValidatorService);

  /** @ngInject */
  function ImageValidatorService (ImagesFallbackList, $q) {
    var service = {
      validateBase64: validateImage,
      validateOrFallback: validateOrFallback
    };

    return service;

    function validateImage(imageURL) {
      var deferred = $q.defer();

      var image = new Image();
      image.onerror = function() {
        deferred.reject();
      };
      image.onload = function() {
        deferred.resolve();
      };
      image.src = imageURL;

      return deferred.promise;
    }   

    function validateOrFallback(imageURL, imageFallbackId) {
      return $q(function (resolve) {
        validateImage(imageURL)
          .then(function () {
            resolve(imageURL);
          })
          .catch(function () {
            resolve(  [imageFallbackId]);
          });
      });
    }


  }
})();
