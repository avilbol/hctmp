(function () {
  'use strict';

  angular
    .module('HalloCasaAdmin')
    .service('TranslationService', TranslationService);

  /** @ngInject */
  function TranslationService($q,
    $resource,
    $http,
    GenericRESTResource,
    backendUrl,
    backendUrlQA,
    backendUrlProduction,
    securityKey) {
    var url = backendUrl;

    var service = {
      loadLocales: loadLocales,
      saveLocale: saveLocale,
      deleteLocale: deleteLocale
    };

    var resources = {
      locale: $resource(backendUrl + 'locales', {}, GenericRESTResource),
      saveLocale: $resource(backendUrl + 'locales', {})
    };

    return service;

    function loadLocales(type) {
      if (type === 1) {
        url = backendUrlQA;
      } else {
        url = backendUrlProduction;
      }

      var req = {
        method: 'GET',
        url: url + 'locales/',
        headers: {
          'Content-Type': 'application/json',
          'security-key': securityKey
        }
      }

      var promise = $http(req).then(function (data) {
        var translations = data.data
        angular.forEach(translations, function (value, key) {
          value.pnemonicEdit = value.pnemonic;
          value.pnemonicDelete = value.pnemonic;
        });

        return data;
      });

      return promise;
    }

    function saveLocale(item, type) {

      if (type === 1) {
        url = backendUrlQA;
      } else {
        url = backendUrlProduction;
      }

      var data = [{
        'pnemonic': item.pnemonic,
        'description': item.description,
        'enUS': item.enUS,
        'esES': item.esES,
        'deDE': item.deDE
      }]

      var req = {
        method: 'POST',
        url: url + 'locales/',
        headers: {
          'Content-Type': 'application/json',
          'security-key': securityKey
        },
        data: data
      }

      return $http(req);
    }

    function deleteLocale(item, type) {
      if (type === 1) {
        url = backendUrlQA;
      } else {
        url = backendUrlProduction;
      }

      var req = {
        method: 'DELETE',
        url: url + "locales/" + item,
        headers: {
          'security-key': securityKey
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