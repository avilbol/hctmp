/* global moment:false */
(function () {
  'use strict';

  angular
    .module('HalloCasaAdmin')
    .constant('AppVersion', '1.0.1')
    .constant('moment', moment)
    .constant('GenericRESTResource', {
        query: { method: 'GET', isArray: true},
        create: { method: 'POST'},
        consult: { method: 'POST', isArray: true},
        consultObj: { method: 'POST'},
        show: { method: 'GET'},
        update: { method: 'PUT', params: {id: '@id'}},
        delete: { method: 'DELETE', params: {id: '@id'}}
    });

})();
