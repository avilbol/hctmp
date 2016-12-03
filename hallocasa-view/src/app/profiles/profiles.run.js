(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .run(run);

  function run($rootScope, SessionService) {
    var routeListener = $rootScope.$on('$routeChangeStart', function (event, toState) {
      if (toState.requiredLogin) {
        SessionService.validateActiveSession("PublicProfile.PreAuthorize.loginNeeded");
      }
    });

    $rootScope.$on('$destroy',routeListener);
  }

})();