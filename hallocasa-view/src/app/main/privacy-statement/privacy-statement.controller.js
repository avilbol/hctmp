(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .controller('PrivacyStatementController', PrivacyStatementController);

  /** @ngInject */
  function PrivacyStatementController($mdDialog) {
    var vm = this;

    vm.closeDialog = closeDialog;
    //TODO: Texto del acuerdo
    vm.description = "Acuerdo de privacidad";

    function closeDialog(){
      $mdDialog.cancel();
    }
  }
})();
