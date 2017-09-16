(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .service('IpInfoService', IpInfoService );

  /** @ngInject */
  function IpInfoService (GenericRESTResource, $resource) {
    var service = {
      getLocation : getLocation
    };

    var resources = {
      ipInfo: $resource("http://ip-api.com/json", {}, GenericRESTResource)
    };

    return service;

    function getLocation() {
      return resources.ipInfo.show().$promise;
    }
  }
})();
