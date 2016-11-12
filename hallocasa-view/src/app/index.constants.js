/* global moment:false */
(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .constant('moment', moment)
    .constant('backend_url'," http://localhost:64647/hallocasa-api/")
    .constant('LOCALES', {
      'locales': {
        'es_ES': 'Español',
        'en_US': 'English',
        'de_DE': 'Deutsch'
      },
      'preferredLocale': 'es_ES'
    })
    .constant('GenericRESTResource', {
      query: { method: 'GET', isArray: true },
      create: { method: 'POST' },
      show: { method: 'GET' },
      update: { method: 'PUT', params: {id: '@id'} },
      delete: { method: 'DELETE', params: {id: '@id'} }
    });

})();
