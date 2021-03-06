(function() {
  'use strict';

  angular.module('HalloCasa.global')

      .factory('CookieLawService', CookieLawService);

      function CookieLawService (CookieService, cookieLawName, cookieLawAccepted, cookieLawDeclined) {
        var accept = function (expireDate) {
          CookieService.set(cookieLawName, cookieLawAccepted + ';expires=' + expireDate);
        };

        var decline = function () {
          CookieService.set(cookieLawName, cookieLawDeclined);
        };

        var isEnabled = function () {
          return CookieService.get(cookieLawName) === cookieLawAccepted;
        };

        return {
          accept: accept,
          decline: decline,
          isEnabled: isEnabled
        }
      }  

})();        