(function() {
  'use strict';

  angular
    .module('HalloCasa', [
      //Third party modules
      'ngAnimate', 'ngResource', 'ngCookies', 'ngRoute', 'ngMaterial', 'toastr', 'pascalprecht.translate', 'ngSanitize',
      'tmh.dynamicLocale', 'vAccordion', 'ngMessages', 'lfNgMdFileInput', 'pdf', 'LocalStorageModule',
      'angular-spinkit', 'vcRecaptcha', 'vr.directives.slider', 'uz.mailto', '720kb.socialshare', 'ngTouch', 'ngTouchstart',
      // Cookie Law
      // 'angular-cookie-law',
      //App modules
      'HalloCasa.environment.constants',
      'HalloCasa.global',
      'HalloCasa.session',
      'HalloCasa.profiles',
      'HalloCasa.property',
      'HalloCasa.protractor'
    ]);

})();
