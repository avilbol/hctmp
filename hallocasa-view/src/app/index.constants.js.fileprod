/* global moment:false */
(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .constant('AppVersion', '0.0.1')
    .constant('INTERCOM_APPID', 't6itp8rl')
    .constant('WOOTRIC_APPID', 'NPS-ead65c4a')
    .constant('moment', moment)
    .constant('backend_url', "http://www.hallocasa.com:64647/hallocasa-api/")
    .constant('user_images_url', "http://www.hallocasa.com:64645/resources/images/users/")
    .constant('property_images_url', "http://www.hallocasa.com:64645/resources/images/properties/")
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
      consult: { method: 'POST', isArray: true },
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
    })
    .constant('ImagesFallbackList',{
      "PropertyDefault": "assets/images/property/default.jpg",
      "UserDefault": "assets/images/user_avatar/user0.jpg"
    });

})();
