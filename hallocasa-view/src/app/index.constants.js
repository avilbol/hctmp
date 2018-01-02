/* global moment:false */
(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .constant('AppVersion', '1.4.29')
    .constant('INTERCOM_APPID', 't6itp8rl')
    .constant('WOOTRIC_APPID', 'NPS-ead65c4a')
    .constant('MAILCHIMP_AID', 'hallocasa.us10')
    .constant('MAILCHIMP_UID', '34633f8bf6d14b7ef0e8b6bbb')
    .constant('MAILCHIMP_ID', '645de5e37b')
    .constant('cookieLawName', '_cle')
    .constant('cookieLawAccepted', 'accepted')
    .constant('cookieLawDeclined', 'declined')
    .constant('moment', moment)
    .constant('LOCALES', {
      'locales': {
        'es-ES': 'Español',
        'en-US': 'English',
        'de-DE': 'Deutsch'
      },
      'languages': {
        'English': {'id': 1, 'locale': 'en'},
        'Español': {'id': 2, 'locale': 'es'},
        'Deutsch': {'id': 3, 'locale': 'de'}
      },
      'defaultLocale': 'en-US'
    })
    .constant('CURRENCIES', {
      'defaultCurrencyId' : 3 // Constant for USD - Dollar
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
        'buyProcess': 'http://blog.hallocasa.com/es/procesos-de-compra-todos/',
        'termAndConditions': 'http://blog.hallocasa.com/es/terminos-y-condiciones/',
        'privacyPolicy': 'http://blog.hallocasa.com/es/acuerdo-de-privacidad/'
      },
      'English': {
        'blog': 'http://blog.hallocasa.com/',
        'buyProcess': 'http://blog.hallocasa.com/buying-processes-overview/',
        'termAndConditions': 'http://blog.hallocasa.com/terms-conditions/',
        'privacyPolicy': 'http://blog.hallocasa.com/privacy-policy/'
      },
      'Deutsch':{
        'blog': 'http://blog.hallocasa.com/de/',
        'buyProcess': 'http://blog.hallocasa.com/de/kaufprozesse-alle/',
        'termAndConditions': 'http://blog.hallocasa.com/de/nutzungsbedingungen/',
        'privacyPolicy': 'http://blog.hallocasa.com/de/datenschutzerklaerung/'
      }
    })
    .constant('ImagesFallbackList',{
      "PropertyDefault": "assets/images/property/default.jpg",
      "UserDefault": "assets/images/user_avatar/user0.jpg"
    });

})();
