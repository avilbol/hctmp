(function() {
  'use strict';

  angular
    .module('HalloCasa.protractor')
    .controller('TestController', TestController);

  /** @ngInject */
  function TestController(ProtractorService) {
    var vm = this;

    vm.operate = operate;

    ProtractorService.getOperations()
      .then(function (operations) {
        vm.operations = operations;
      });

    function operate(firstValue, operation, secondValue) {
      switch (operation){
        case "Suma":
          vm.result = firstValue + secondValue;
          break;
        case "Resta":
          vm.result = firstValue - secondValue;
          break;
        case "Multiplicación":
          vm.result = firstValue * secondValue;
          break;
        case "División":
          vm.result = firstValue / secondValue;
          break;
      }
    }
  }
})();
