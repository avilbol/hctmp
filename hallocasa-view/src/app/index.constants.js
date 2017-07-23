/* global moment:false */
(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .constant('AppVersion', '1.1.0')
    .constant('INTERCOM_APPID', 't6itp8rl')
    .constant('WOOTRIC_APPID', 'NPS-ead65c4a')
    .constant('moment', moment)
    .constant('LOCALES', {
      'locales': {
        'es_ES': 'Español',
        'en_US': 'English',
        'de_DE': 'Deutsch'
      },
      'languages': {
        'English': {'id': 1, 'locale': 'en'},
        'Español': {'id': 2, 'locale': 'es'},
        'Deutsch': {'id': 3, 'locale': 'de'}
      },
      'preferredLocale': 'es_ES'
    })
    .constant('GenericRESTResource', {
      query: { method: 'GET', isArray: true},
      create: { method: 'POST'},
      consult: { method: 'POST', isArray: true},
      consultObj: { method: 'POST'},
      show: { method: 'GET'},
      update: { method: 'PUT', params: {id: '@id'}},
      delete: { method: 'DELETE', params: {id: '@id'}}
    })
    .constant('ApplicationCredentials', {
      'client-id': 'hallocasa_frontend',
      'client_secret': "12345",
      'grant_type': 'password',
      'code': 'mFQDZKXBtVkoUofIOsmhuyxnyvOcmMPT'
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
