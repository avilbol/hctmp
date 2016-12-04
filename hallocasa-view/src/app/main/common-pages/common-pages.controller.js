(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .controller('CommonPagesController', CommonPagesController);

  /** @ngInject */
  function CommonPagesController($location) {
    var vm = this;

    function detectPageType() {
      switch($location.url()){
        case "/404":
          vm.title = "PageNotFound.Panel.Title";
          vm.message = "PageNotFound.Panel.Info";
          break;
        case "/forbidden":
          vm.title = "Forbidden.Panel.Title";
          vm.message = "Forbidden.Panel.Info";
          break;
        case "/coming-soon":
          vm.title = "Forbidden.Panel.Title";
          vm.message = "Forbidden.Panel.Info";
          break;
      }
    }

    detectPageType();
  }
})();
