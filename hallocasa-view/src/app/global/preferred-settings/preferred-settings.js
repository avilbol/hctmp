/**
    Service that query back end for preferred settings clasified by country:
    The country can be all the countries loaded in system
    There is default preferred settings if country is not found
    @author avilbol
  **/
(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .service('PreferredSettingsService', PreferredSettingsService );

  /** @ngInject */
  function PreferredSettingsService (GenericRESTResource, backend_url, $resource) {
    var service = {
      getPreferredSettings: getPreferredSettings
    };

    var resources = {
      preferredSettings: $resource(backend_url + "preferred_settings", {}, GenericRESTResource)
    };

    return service;

    function getPreferredSettings() {
      return resources.preferredSettings.query().$promise;
    }
  }
})();
