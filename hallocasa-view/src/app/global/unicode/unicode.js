(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .filter('unicode', unicode);

  /** @ngInject */
  function unicode($log) {
    return function(string){
      if(!string){return;}
      try{
        string = decodeURIComponent(angular.fromJson('"' + string.replace(/\"/g, '\\"').replace(/%/g, '&#37;') + '"'));
        string = string.replace(/&#37;/g, '%');
      }
      catch(error) {
        $log.debug("Error al decodificar el texto" + string, error);
      }
      return string;
    }
  }
})();
