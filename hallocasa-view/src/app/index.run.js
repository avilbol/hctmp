(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .run(runBlock);

  /** @ngInject */
  function runBlock($log, $route, BrowserDetectionService, localStorageService, AppVersion) {
    $route.reload();
    $log.debug('runBlock end');
    handleBrowserRequirements(BrowserDetectionService);
    polyfill();
    validateLocalStorageVersion(localStorageService, AppVersion);
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

  function validateLocalStorageVersion(localStorageService, AppVersion) {
    var localStorageVersion = localStorageService.get("storageVersion");

    if(localStorageVersion !== AppVersion){
      localStorageService.clearAll();
      localStorageService.set("storageVersion", AppVersion);
    }
  }

})();
