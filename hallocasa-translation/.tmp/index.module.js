(function () {
  'use strict';

  angular
    .module('HalloCasaAdmin', [
      //Third party modules 
      'ngAnimate', 'ngResource', 'ngRoute', 'ngMaterial', 'toastr', 'ngSanitize',
      'ngMessages','mdDataTable','toastr',
      //App modules 
      'HalloCasaAdmin.environment.constants',
      'HalloCasaAdmin.global'
    ]);
})();