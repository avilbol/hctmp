(function(){
	var w=window;
	var ic=w.Intercom;
	if(typeof ic==="function"){
		ic('reattach_activator');
		ic('update',intercomSettings);
	}else{
		var d=document;
		var i=function(){
			i.c(arguments);
		};
		i.q=[];
		i.c=function(args){
			i.q.push(args);
		};
		w.Intercom=i;
		function l(){
			var s=d.createElement('script');
			s.type='text/javascript';
			s.async=true;
			s.src='https://widget.intercom.io/widget/t6itp8rl';
			var x=d.getElementsByTagName('script')[0];
			x.parentNode.insertBefore(s,x);
		}
		if(w.attachEvent){
			w.attachEvent('onload',l);
		}else{
			w.addEventListener('load',l,false);
		}
	}
	updateIntercom();
	doIntercomSettings(null, null);
})();

function doIntercomSettings(username, email) {
	if(username == null){
		window.intercomSettings = { app_id: "t6itp8rl" };
	}
	else {
		alert(window.intercomSettings.name);
		if(window.intercomSettings.name == null){
			bootIntercomUser(username, email);
		}
		window.intercomSettings = {
		  app_id: "t6itp8rl",
		  name: username, 
		  email: email, 
		  created_at: 1312182000
		};
	}
	updateIntercom();
}

function bootIntercomUser(username, email){
	window.Intercom("boot", {
	  app_id: "t6itp8rl",
	  name: username, 
	  email: email, 
	  created_at: 1312182000 // TODO: modify this when sign up time was developed
	});
}

function updateIntercom(){
	window.Intercom('update');
}