(function() {
  'use strict';

  angular.module('HalloCasa')
      .factory('CookieService', CookieService);

      function CookieService ($document, $cookies) {
        
        var get = function (key) {
          if ($cookies.get(key)) {
            return $cookies.get(key).split(';')[0];
          } else {
            return null;
          }
        };

        var set = function (key, value) {
          $cookies.put(key, value);
        };

        return {
          get: get,
          set: set
        }
      }

})();           