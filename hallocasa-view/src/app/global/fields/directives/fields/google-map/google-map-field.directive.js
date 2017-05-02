(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('googleMapField', googleMapField);

  function googleMapField($timeout) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/google-map/google-map-field.html",
      scope: {
        fieldInformation: "=",
        form: "=?",
        readonly: "=?"
      },
      link: function (scope) {
        scope.fieldInformation.fieldValueList = scope.fieldInformation.fieldValueList ? scope.fieldInformation.fieldValueList : [];
        scope.$on("RepaintMap", repaintMap);

        function repaintMap() {
          scope.refresh = false;
          $timeout(function () {
            scope.refresh = true;
          },300);
        }

        function watchRawLocation() {
          var deleteWatcher = scope.$watch('location',function(){
            if(scope.location){
              var locationData = {
                data1: {doubleVal: scope.location.center.latitude},
                data2: {doubleVal: scope.location.center.longitude},
                data3: {intVal: scope.location.zoom}
              };
              if(scope.fieldInformation.fieldValueList[0]){
                locationData.bdid = scope.fieldInformation.fieldValueList[0].bdid;
              }
              scope.fieldInformation.fieldValueList[0] = locationData;
            }
          }, true);

          scope.$on("$destroy",deleteWatcher);
        }

        function loadLocation() {
          if(!_.isEmpty(scope.fieldInformation.fieldValueList)){
            var fieldValue = scope.fieldInformation.fieldValueList[0];

            scope.location = {
              center: {
                latitude: fieldValue.data1.doubleVal,
                longitude: fieldValue.data2.doubleVal
              }
            };
            if(fieldValue.data3 && _.isNumber(fieldValue.data3.intVal)){
              scope.location.zoom = fieldValue.data3.intVal;
            }
            else{
              scope.location.zoom = 16;
            }
          }
        }

        loadLocation();
        watchRawLocation();
      }
    };
  }
})();
