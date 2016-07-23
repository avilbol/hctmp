var map;
var markers = [];


$(function(){
	$("a[data-toggle='tab']").on("shown.bs.tab", function(e) {
		initialize();
	});
});

function clean(){
	map = null;
	markers = [];
}

function initialize(){
	map.setTilt(45);	
	var center = map.getCenter();
    google.maps.event.trigger(map, "resize");
    map.setCenter(center);
	map.addListener("click", clickListener);
}

function initDefaultCityMap() {
	clean();
	setTimeout('defaultCityMap()', 2000);
}

function initLayoutCityMap(lat, lng) {
	clean();
	setTimeout('layoutCityMap(' + lat + ',' + lng + ')', 2000);
}

function initMarkerMap(lat, lng) {
	clean();
	setTimeout('markerMap(' + lat + ',' + lng + ')', 2000);
}

function initLastMap() {
	setTimeout('lastMap()', 2000);
}

function initMarkerAndLayoutCityMap(latVluCity,
		lngVluCity) {
	setTimeout('markerAndLayoutCityMap(' + getLat() + ',' + getLng()
			+ ',' + latVluCity + ',' + lngVluCity + ')', 2000);
}

function changeCityMap(latValue, lngValue) {
	if (latValue != null && lngValue != null) {
		map.setCenter({
			lat : latValue,
			lng : lngValue
		});
		map.setZoom(10);
	}
}

function defaultCityMap() {
	map = new google.maps.Map(document.getElementById('map'), {
		// Bogota coordinates
		center : {
			lat : 4.65,
			lng : -74.05
		},
		zoom : 10
	});
	setupInput();
	map.addListener("click", clickListener);
}

function lastMap() {
	map = new google.maps.Map(document.getElementById('map'), {
		center : map.getCenter(),
		zoom : map.getZoom()
	});
	setupInput();
	if(markers.length > 0)
		pointMarker(markers[0].getPosition());
}

function layoutCityMap(latVlu, lngVlu) {
	map = new google.maps.Map(document.getElementById('map'), {
		center : {
			lat : latVlu,
			lng : lngVlu
		},
		zoom : 10
	});
	setupInput();
	map.addListener("click", clickListener);
}

function markerMap(latVlu, lngVlu) {
	map = new google.maps.Map(document.getElementById('map'), {
		center : {
			lat : latVlu,
			lng : lngVlu
		},
		zoom : 18
	});
	var center = map.getCenter();
	pointMarker(center);
}

function markerAndLayoutCityMap(latVluMarker, lngVluMarker, latVluCity,
		lngVluCity) {
	if (latVluCity != null && lngVluCity != null) {
		map = new google.maps.Map(document.getElementById('map'), {
			center : {
				lat : latVluCity,
				lng : lngVluCity
			},
			zoom : 12
		});
		map.setTilt(45);
		var center = map.getCenter();
		google.maps.event.trigger(map, 'resize');
		map.setCenter(center);
		map.addListener("click", clickListener);
	}
	if(latVluMarker != null && lngVluMarker != null){
		pointMarker({
			lat : latVluMarker,
			lng : lngVluMarker
		});
	}
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
		position : location,
		map : map
	});
	markers.push(marker);
}

// Adds a marker to the map and push to the array.
function addMarker(location) {
	var marker = new google.maps.Marker({
		position : location,
		map : map
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

function pointMarker(position) {
	deleteMarkers();
	addMarker(position);
	setupInput();
}

function getLat() {
	if (markers.length == 0) {
		return null;
	}
	return markers[0].getPosition().lat();
}

function getLng() {
	if (markers.length == 0) {
		return null;
	}
	return markers[0].getPosition().lng();
}

function setLatDms() {
	var lat = getLat();
	var result = "--";
	if (lat != null) {
		var latDms = {
			dir : lat < 0 ? 'S' : 'N',
			deg : 0 | (lat < 0 ? lat = -lat : lat),
			min : 0 | lat % 1 * 60,
			sec : (0 | lat * 60 % 1 * 6000) / 100
		};
		result = latDms.deg + " " + latDms.min + "' " + latDms.sec + "'' "
				+ latDms.dir;
	}
	$("#property-latitude-value").text(result);
}

function setLngDms() {
	var lng = getLng();
	var result = "--";
	if (lng != null) {
		var lngDms = {
			dir : lng < 0 ? 'W' : 'E',
			deg : 0 | (lng < 0 ? lng = -lng : lng),
			min : 0 | lng % 1 * 60,
			sec : (0 | lng * 60 % 1 * 6000) / 100
		};
		var result = lngDms.deg + " " + lngDms.min + "' " + lngDms.sec + "'' "
				+ lngDms.dir;
	}
	$("#property-longitude-value").text(result);
}

function setInputLongitude() {
	var rawId = "hc-profile-form:property-longitude";
	if(getLng() != null)
		$('#' + rawId.split(':').join('\\:')).val(getLng());
}

function setInputLatitude() {
	var rawId = "hc-profile-form:property-latitude";
	if(getLat() != null)
		$('#' + rawId.split(':').join('\\:')).val(getLat());
}

function setupInput() {
	setInputLongitude();
	setInputLatitude();
	setLngDms();
	setLatDms();
}
