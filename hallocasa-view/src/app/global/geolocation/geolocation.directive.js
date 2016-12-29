(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('geolocation', geolocation);

  /** @ngInject */
  function geolocation(GeolocationService, $log) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/geolocation/geolocation.html",
      scope: {
        location: "=",
        options: "=?",
        refresh: "=?",
        readonly: "=?"
      },
      link: function (scope) {
        scope.location = angular.isObject(scope.location) ? scope.location : {};
        scope.location.zoom = angular.isNumber(scope.location.zoom) ? scope.location.zoom : 1;
        scope.options = angular.isObject(scope.options) ? scope.options : {};

        scope.map= {control: {}};

        function isValidLocation(center) {
          var validLocation = angular.isObject(center) &&
            angular.isNumber(center.latitude) &&
            angular.isNumber(center.longitude) &&
            center.latitude >= -90 && center.latitude <= 90 &&
            center.longitude >= -180 && center.longitude <= 180;
          if(!validLocation){
            $log.warn("Se ha detectado una ubicación invalida: ",center);
          }
          return validLocation;
        }

        function startLocation(){
          if(isValidLocation(scope.location.center)){
            scope.mapCenter = scope.location.center;
            return;
          }
          scope.location.center = { latitude: 45, longitude: -73 };
          scope.mapCenter = { latitude: 45, longitude: -73 };
          GeolocationService.getCurrentPosition()
            .then(function (position) {
              scope.location.center.latitude = position.coords.latitude;
              scope.location.center.longitude = position.coords.longitude;
              scope.location.zoom = 16;
              scope.mapCenter.latitude = position.coords.latitude;
              scope.mapCenter.longitude = position.coords.longitude;
            })
        }

        startLocation();

        scope.$watch("location.center", function (newCenter, oldCenter) {
          if(!isValidLocation(newCenter)){
            location.center = oldCenter;
          }
          else{
            detectMarkerVisibility(newCenter);
          }
        }, true);

        function detectMarkerVisibility(newCenter) {
          if(!angular.isFunction(scope.map.control.getGMap)){
            return
          }
          var latLng = new google.maps.LatLng(newCenter.latitude, newCenter.longitude);
          var isVisible = scope.map.control.getGMap().getBounds().contains(latLng);
          scope.mapCenter = isVisible ? scope.mapCenter : newCenter;
        }

        scope.events = {click: function(mapModel, eventName, originalEventArgs){
          scope.$apply(function(){
            var e = originalEventArgs[0];
            scope.location.center.latitude = e.latLng.lat();
            scope.location.center.longitude = e.latLng.lng();
          });
        }};
      }
    };
  }
})();
