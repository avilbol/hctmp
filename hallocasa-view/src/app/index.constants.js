/* global moment:false */
(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .constant('AppVersion', '1.1.0')
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
      'languages': {
        'English': {'id': 1, 'locale': 'en'},
        'Español': {'id': 2, 'locale': 'es'},
        'Deutsch': {'id': 3, 'locale': 'de'}
      },
      'preferredLocale': 'es_ES'
    })
    .constant('GenericRESTResource', {
      query: { method: 'GET', isArray: true, headers: {'Content-Encoding' : 'gzip'} },
      create: { method: 'POST', headers: {'Content-Encoding' : 'gzip'} },
      consult: { method: 'POST', isArray: true, headers: {'Content-Encoding' : 'gzip'} },
      consultObj: { method: 'POST', headers: {'Content-Encoding' : 'gzip'}},
      show: { method: 'GET', headers: {'Content-Encoding' : 'gzip'} },
      update: { method: 'PUT', params: {id: '@id'}, headers: {'Content-Encoding' : 'gzip'} },
      delete: { method: 'DELETE', params: {id: '@id'}, headers: {'Content-Encoding' : 'gzip'} }
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
