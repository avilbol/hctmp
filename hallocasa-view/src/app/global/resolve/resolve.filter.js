(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .filter('resolve', resolve);

  /** @ngInject */
  function resolve() {
    return function(obj, path) {
      return path.split('.').reduce(function(prev, curr) {
        return prev ? prev[curr] : undefined
      }, obj)
    }
  }
})();
