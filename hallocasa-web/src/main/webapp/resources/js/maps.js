var map;
var markers = [];

function initDefaultCityMap() {
	setTimeout('defaultCityMap()', 2000);
}

function initLayoutCityMap(lat, lng) {
	setTimeout('layoutCityMap('+lat+','+lng+')', 2000);
}

function initMarkerMap(lat, lng) {
	setTimeout('markerMap('+lat+','+lng+')', 2000);
}

function defaultCityMap(){
	map = new google.maps.Map(document.getElementById('map'), {
		center : {
			lat : latVlu,
			lng : lngVlu
		},
		zoom : 10
	});
	map.setTilt(45);
	var center = map.getCenter();
	google.maps.event.trigger(map, 'resize');
	map.setCenter(center);
	map.addListener("click", clickListener);
}

function layoutCityMap(latVlu, lngVlu){
	map = new google.maps.Map(document.getElementById('map'), {
		center : {
			lat : 4.65,
			lng : -74.05
		},
		zoom : 10
	});
	map.setTilt(45);
	var center = map.getCenter();
	google.maps.event.trigger(map, 'resize');
	map.setCenter(center);
	map.addListener("click", clickListener);
}

function markerMap(latVlu, lngVlu){
	map = new google.maps.Map(document.getElementById('map'), {
		center : {
			lat : latVlu,
			lng : lngVlu
		},
		zoom : 5
	});
	map.setTilt(45);
	var center = map.getCenter();
	google.maps.event.trigger(map, 'resize');
	map.setCenter(center);
	map.addListener("click", clickListener);
	pointMarker(center);
}

function basicMap(lat, lng) {
	var latToApply = lat;
	var lngToApply = lng;
	var zoomToApply = 18;
	if (lat == null) {
		// Bogota coordinates
		latToApply = 4.65;
		// Minor focus
		zoomToApply = 11;
	}
	if (lng == null) {
		// Bogota coordinates
		lngToApply = -74.05;
		// Minor focus
		zoomToApply = 11;
	}
	map = new google.maps.Map(document.getElementById('map'), {
		center : {
			lat : latToApply,
			lng : lngToApply
		},
		zoom : zoomToApply
	});
	map.setTilt(45);
	map.setTilt(45);
	var center = map.getCenter();
	google.maps.event.trigger(map, 'resize');
	map.setCenter(center);
	map.addListener("click", clickListener);
}

function initSatelliteMap() {
	map = new google.maps.Map(document.getElementById('map'), {
		center : {
			lat : 36.964,
			lng : -122.015
		},
		zoom : 18,
		mapTypeId : google.maps.MapTypeId.SATELLITE
	});
	map.setTilt(45);
	var center = map.getCenter();
	google.maps.event.trigger(map, 'resize');
	map.setCenter(center);
	map.addListener("click", clickListener);
}

function initMap() {
	map = new google.maps.Map(document.getElementById('map'), {
		center : {
			lat : -34.397,
			lng : 150.644
		},
		zoom : 8
	});
}

function clickListener(e) {
	// lat and lng is available in e object
	var position = e.latLng;
	pointMarker(position);
}


// Adds a marker to the map and push to the array.
function addMarker(location) {
  var marker = new google.maps.Marker({
    position: location,
    map: map
  });
  markers.push(marker);
}

//Adds a marker to the map and push to the array.
function addMarker(location) {
  var marker = new google.maps.Marker({
    position: location,
    map: map
  });
  markers.push(marker);
}

// Sets the map on all markers in the array.
function setMapOnAll(map) {
  for (var i = 0; i < markers.length; i++) {
    markers[i].setMap(map);
  }
}

// Removes the markers from the map, but keeps them in the array.
function clearMarkers() {
  setMapOnAll(null);
}

// Deletes all markers in the array by removing references to them.
function deleteMarkers() {
	clearMarkers();
	markers = [];
}

function pointMarker(position){
	deleteMarkers();
	addMarker(position);
}

function getLat(){
	if(markers.length == 0){
		return null;
	}
	return markers[0].getPosition().lat();
}

function getLng(){
	if(markers.length == 0){
		return null;
	}
	return markers[0].getPosition().lng;
}
