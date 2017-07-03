(function() {
  'use strict';

  angular
    .module('HalloCasa', [
      //Third party modules
      'ngAnimate', 'ngResource', 'ngCookies', 'ngRoute', 'ngMaterial', 'toastr', 'pascalprecht.translate', 'ngSanitize',
      'tmh.dynamicLocale', 'vAccordion', 'ngMessages', 'lfNgMdFileInput', 'angular-carousel-3d', 'pdf', 'LocalStorageModule',
      'angular-spinkit', 'vcRecaptcha',
      //App modules
      'HalloCasa.global',
      'HalloCasa.session',
      'HalloCasa.profiles',
      'HalloCasa.property',
      'HalloCasa.protractor'
    ]);

})();
