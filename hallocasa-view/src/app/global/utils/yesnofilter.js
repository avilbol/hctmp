(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .filter('yesNoFilter', yesNoFilter);

  function yesNoFilter(translateFilter) {
    return function(booleanVlu) {
      var result = booleanVlu === true ? 'global.yes' : (booleanVlu === false ? 'global.no' : null);
      return translateFilter(result);
    }
  }
})();
