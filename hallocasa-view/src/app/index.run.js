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
    polyfill();
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

  function polyfill() {
    stringIncludes();
  }

  function stringIncludes() {
    if (!String.prototype.includes) {
      String.prototype.includes = function(search, start) {
        'use strict';
        if (!_.isNumber(start)) {
          start = 0;
        }

        if (start + search.length > this.length) {
          return false;
        } else {
          return this.indexOf(search, start) !== -1;
        }
      };
    }
  }

})();
