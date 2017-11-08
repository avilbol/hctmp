(function () {
  'use strict';

  angular
    .module('HalloCasaAdmin')
    .controller('MainController', MainController);

  /** @ngInject */
  function MainController(){
    var vm = this;

    vm.hola = 'Bienvenido Administrador';
  }
})();
