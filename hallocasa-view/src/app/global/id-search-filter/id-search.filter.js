(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .filter('idSearch', idSearch);

  /** @ngInject */
  function idSearch() {
    return function(list, id, property) {
      id = id ? Number(id) : id;
      var elementFound = _.find(list, function (element) {
        return element.id === id;
      });
      if(!property){
        return elementFound;
      }
      if(property && elementFound){
        return elementFound[property]
      }
    }
  }
})();
