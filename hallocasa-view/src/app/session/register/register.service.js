(function() {
  'use strict';

  angular
    .module('HalloCasa.session')
    .service('RegisterService', RegisterService);

  function RegisterService($resource, backend_url, GenericRESTResource, MAILCHIMP_AID, MAILCHIMP_UID, MAILCHIMP_ID) {

    var service = {
      makeRegister: makeRegister,
      subscribeNewUser: subscribeNewUser
    };

    var resources = {
      register: $resource(backend_url+'user/register', {}, GenericRESTResource),
      subscribe: $resource("//"+MAILCHIMP_AID+".us3.list-manage.com/subscribe/post-json?u="+MAILCHIMP_UID+"&amp;id="+MAILCHIMP_ID, {}, {
        jsonp: {method: 'JSONP'}
      })
    };

    return service;

    function makeRegister(userData){
      return resources.register.create(userData).$promise;
    }

    function subscribeNewUser(userData){
      var payload = {
        "EMAIL":userData.email,
        "UTYPE":"PUBLISHER",
        "mc_language":userData.language.locale
      };

      return resources.subscribe.jsonp(payload).$promise;
    }
  }
})();
