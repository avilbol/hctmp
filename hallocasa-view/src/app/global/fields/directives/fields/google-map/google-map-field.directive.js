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
        form: "=?"
      },
      link: function (scope) {
        scope.fieldInformation.fieldValueList = scope.fieldInformation.fieldValueList ? scope.fieldInformation.fieldValueList : [];

        scope.$on("RepaintMap", function () {
          scope.refresh = false;
          $timeout(function () {
            scope.refresh = true;
          },300);
        });

        function watchRawLocation() {
          var deleteWatcher = scope.$watch('location',function(){
            if(scope.location){
              scope.fieldInformation.fieldValueList[0] = {
                data1: {doubleVal: scope.location.center.latitude},
                data2: {doubleVal: scope.location.center.longitude},
                data3: {intVal: scope.location.zoom}
              };
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
              },
              zoom: fieldValue.data3.intVal
            };

          }
        }

        loadLocation();
        watchRawLocation();
      }
    };
  }
})();
