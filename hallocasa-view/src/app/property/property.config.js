(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .config(config);

  /** @ngInject */
  function config(uiGmapGoogleMapApiProvider) {
    uiGmapGoogleMapApiProvider.configure({
      //key: 'your api key',
      //libraries: 'weather,geometry,visualization',
      v: '3.x' //defaults to latest 3.X anyhow
    });
  }

})();

