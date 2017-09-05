(function() {
  'use strict';

  angular
    .module('HalloCasaAdmin')
    .service('TranslationService', TranslationService );

  /** @ngInject */
  function TranslationService ($q, 
                                $resource, 
                                $http,
                                GenericRESTResource, 
                                backend_url, 
                                security_key) {
    var service = {
        loadLocales: loadLocales,
        saveLocale: saveLocale
    //   getCurrentPosition: getCurrentPosition
    };

    var resources = {
      locale: $resource(backend_url + "locales", {}, GenericRESTResource),
      saveLocale: $resource(backend_url + "locales", {} 
      )
    //   exchange: $resource(backend_url + "currency_exchange_data", {}, GenericRESTResource)
    };

    return service;

    function loadLocales() {
      return resources.locale.query().$promise;
    }

    function saveLocale(item){
      var data = [
        { 
          "pnemonic": item.pnemonic,
          "description": item.description,
          "enUS": item.enUS,
          "esES": item.esES,
          "deDE": item.deDE
        }
      ]

      var req = {
        method: 'POST',
        url: backend_url + "locales/",
        headers: {
          'Content-Type':'application/json',
          'security-key': security_key
        },
        data: data
      }

      return $http(req);

      // return $http(backend_url + "locales/", {
      //         "pnemonic": item.pnemonic,
      //         "description": item.description,
      //         "enUS": item.enUS,
      //         "esES": item.esES,
      //         "deDE": item.deDE
      //       }, {
      //       headers: {
      //         'Content-Type': 'application/json',
      //         'security-key': security_key
      //       }
      // });


      // var resource = $resource(backend_url + "locales", {}, {
      //   'save': {
      //     method: 'POST',
      //     headers: {
      //       'Content-Type': 'application/json ; charset=UTF-8',
      //       'security-key': security_key
      //     }
      //   }
      // });  
      // var DirectoryApi = $resource(backend_url, null, {
      //     move: {
      //         url: backend_url,
      //         headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
      //         transformRequest: function (param) {
      //             return $.param(param);
      //         },
      //         method: 'POST'
      //     },
      // });

      // return resource.save(item).$promise;
    }

    // function getCurrentPosition() {
    //   return $q(function (resolve, reject) {
    //     if (!$window.navigator.geolocation) {
    //       reject('Geolocation not supported.');
    //     } else {
    //       $window.navigator.geolocation.getCurrentPosition(
    //         function (position) {
    //           resolve(position);
    //         },
    //         function (error) {
    //           reject(error);
    //         });
    //     }
    //   });
    // }

  }

})();