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
                                backend_url_qa,
                                backend_url_production,
                                security_key) {
    var url = backend_url;


                                  
    var service = {
        loadLocales: loadLocales,
        saveLocale: saveLocale,
        deleteLocale: deleteLocale
    //   getCurrentPosition: getCurrentPosition
    };

    var resources = {
      locale: $resource(backend_url + "locales", {}, GenericRESTResource),
      saveLocale: $resource(backend_url + "locales", {} )
    //   exchange: $resource(backend_url + "currency_exchange_data", {}, GenericRESTResource)
    };

    return service;

    function loadLocales(type) {
      if(type == 1){
        url = backend_url_qa;
      } else {
        url = backend_url_production;
      }

      console.log('Url a usar ', url + "locales/");

      var req = {
        method: 'GET',
        url: url + "locales/",
        headers: {
          'Content-Type':'application/json',
          'security-key': security_key
        }
      }

      return $http(req);

      // return resources.locale.query().$promise;
    }

    function saveLocale(item, type){
      console.log('Save type '+ type);

      if(type == 1){
        url = backend_url_qa;
      } else {
        url = backend_url_production;
      }

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
        url: url + "locales/",
        headers: {
          'Content-Type':'application/json',
          'security-key': security_key
        },
        data: data
      }

      return $http(req);
    }

    function deleteLocale(item, type){
      if(type == 1){
        url = backend_url_qa;
      } else {
        url = backend_url_production;
      }

      var req = {
        method: 'DELETE',
        url: url + "locales/" + item,
        headers: {
          'security-key': security_key
        }
      }

      return $http(req);
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