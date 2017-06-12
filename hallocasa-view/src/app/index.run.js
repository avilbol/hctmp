(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .run(runBlock);

  /** @ngInject */
  function runBlock($log, $route, BrowserDetectionService) {
    $route.reload();
    $log.debug('runBlock end');
    handleBrowserRequirements(BrowserDetectionService);
  }

  function handleBrowserRequirements(BrowserDetectionService) {
    var browser = BrowserDetectionService.detectBrowser();
    if(browser.ISSAFARI){
      loadSafariCSS();
    }
  }

  function loadSafariCSS() {
    angular.element('head')
      .append('<link rel="stylesheet" id="customSafari" type="text/css" href="./assets/css/layout-wrap-fix.css">');
  }

})();
