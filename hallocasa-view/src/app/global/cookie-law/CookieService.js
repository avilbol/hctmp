(function() {
  'use strict';

  angular.module('HalloCasa.global')
      .factory('CookieService', CookieService);

      function CookieService ($document, $cookies) {
        var readCookie = function (key) {

          var ca = $cookies.getAll();
          var result = null;
          _.map(ca, function(value, index){
            if (index === key) result = value.split(';')[0];
          });

          // before function
          // var nameEQ = key + "=";
          // var ca = document.cookie.split(';');

          // for (var i = 0; i < ca.length; i++) {
          //   var c = ca[i];
          //   console.log ('c value ', c);
          //   console.log ('c comparation ', (c.indexOf(nameEQ) == 0));
          //   while (c.charAt(0) == ' ') c = c.substring(1, c.length);
          //   if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
          //   // if (c.indexOf(nameEQ) == 0)   console.log('valor de la coockie ', c.substring(nameEQ.length, c.length));
          // }
          return result;
        }
  
        var get = function (key) {
          var rc = readCookie(key);
          return rc;
        };
  
        var set = function (key, value) {
          $cookies.put(key, value);
        };

        // var get = function (key) {
        //   if ($cookies.get(key)) {
        //     return $cookies.get(key).split(';')[0];
        //   } else {
        //     return null;
        //   }
        // };

        // var set = function (key, value) {
        //   $cookies.put(key, value);
        // };

        return {
          get: get,
          set: set
        }
      }

})();           

