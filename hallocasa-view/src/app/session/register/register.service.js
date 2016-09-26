(function() {
	'use strict';

	angular
		.module('HalloCasa.session')
		.service('RegisterService', RegisterService);

	function RegisterService($resource, backend_url) {
		var service = {
			makeRegister: makeRegister
		};
		
		var RegisterResource =  $resource(backend_url+'/users/register', {}, {
			register: { method: 'POST' }
		});
		
		return service;
		
		function makeRegister(userData){
			return RegisterResource.register(userData).$promise;
		}
	}
})();
