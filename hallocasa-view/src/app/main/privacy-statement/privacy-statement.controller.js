(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .controller('PrivacyStatementController', PrivacyStatementController);

  /** @ngInject */
  function PrivacyStatementController($mdDialog, $scope) {
    var vm = this;

    vm.closeDialog = closeDialog;
    $scope.pdfUrl = "/resources/privacy_statement/hallocasa_privacy_statement.pdf"; // eslint-disable-line

    function closeDialog(){
      $mdDialog.cancel();
    }
  }
})();
