(function() {

  'use strict';

  angular
    .module('HalloCasa.global')
    .controller('PluralizeController', PluralizeController);

  /** @ngInject */
  function PluralizeController($mdDialog, list, title) {

    var vm = this;

    vm.list = list;
    vm.title = title;
    vm.closeDialog = closeDialog;

    function closeDialog() {
      $mdDialog.cancel();
    }
  }
})();
