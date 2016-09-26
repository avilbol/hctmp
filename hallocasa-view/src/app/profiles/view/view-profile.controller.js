(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('ViewProfileController', ViewProfileController);

  /** @ngInject */
  function ViewProfileController() {
    var vm = this;

    vm.view = "view-profile";
  }
})();
