/* global moment:false */
(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .constant('moment', moment)
    .constant('backend_url'," http://www.hallocasa.com:64647/hallocasa-api/")
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
    })
    .constant('ApplicationCredentials', {
      'client-id': 'hallocasa_frontend',
      'client_secret': "12345",
      'grant_type': 'password',
      'code': 'gXLLZEhkfsbUZmAfIfLhyGvjfVLzpyRq'
    })
    .constant('BlogLinks',{
      'Español':{
        'blog': 'http://blog.hallocasa.com/es/',
        'buyProcess': 'http://blog.hallocasa.com/es/procesos-de-compra-todos/'
      },
      'English': {
        'blog': 'http://blog.hallocasa.com/',
        'buyProcess': 'http://blog.hallocasa.com/buying-processes-overview/'
      },
      'Deutsch':{
        'blog': 'http://blog.hallocasa.com/de/',
        'buyProcess': 'http://blog.hallocasa.com/de/kaufprozesse-alle/'
      }
    });

})();
