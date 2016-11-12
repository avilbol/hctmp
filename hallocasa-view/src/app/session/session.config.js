(function() {
  'use strict';

  angular
    .module('HalloCasa.session')
    .config(config)
    .run(run);

  var authProvider;

  /** @ngInject */
  function config($authProvider){
    authProvider = $authProvider;
  }

  /** @ngInject */
  function run(backend_url) {
    authProvider.baseUrl = backend_url;
  }

})();
