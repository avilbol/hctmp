(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .run(runBlock);

  /** @ngInject */
  function runBlock($log, $route, appAuthProvider) {
    $route.reload();
    appAuthProvider.getAuthToken();
    $log.debug('runBlock end');
  }

})();
