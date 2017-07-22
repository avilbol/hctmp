(function() {
	'use strict';

	angular
		.module('HalloCasa.session')
		.service('MailchimpService', MailchimpService)
		.constant('mailchimp_url', 'https://us16.api.mailchimp.com/2.0')
		.constant('api_key', 'f466b577ec449b95e57977d4c326903e-us10')
		.constant('list_id', '645de5e37b');

	function MailchimpService($resource, mailchimp_url, api_key, list_id) {
		var service = {
			subscribeNewUser: subscribeNewUser
		};

		return service;

		function subscribeNewUser(userData){
			var payload = {
			    "email": {
			    	"email": userData.email
			    },
			    "apikey": api_key,
			    "id": list_id,
			    "double_optin":false,
			    "update_existing":true,
			    "merge_vars":{
			    	"EMAIL":userData.email,
			    	"UTYPE":"PUBLISHER",
			    	"mc_language":userData.language
			    }
			}
			var SubscribeResource =  $resource(mailchimp_url+'/lists/subscribe.json', payload, {
				subscribe: { method: 'POST' },
				headers: { 'Content-Type': 'text/plain' }
			});
			return SubscribeResource.subscribe(payload).$promise;
		}
	}
})();
