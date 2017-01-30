(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .filter('unicode', unicode);

  /** @ngInject */
  function unicode($log) {
    return function(string){
      try{
        string = decodeURIComponent(angular.fromJson('"' + string.replace(/\"/g, '\\"') + '"'));
      }
      catch(error) {
        $log.debug("Error al decodificar el texto" + string, error);
      }
      return string;
    }
  }
})();
