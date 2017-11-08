(function() {
  'use strict';

  angular
    .module('HalloCasaAdmin.global', [])
    .factory('_', loadLodash);

  function loadLodash($window) {
    return $window._;
  }
})();