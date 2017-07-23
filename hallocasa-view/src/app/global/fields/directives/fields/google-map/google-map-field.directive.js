(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('googleMapField', googleMapField);

  function googleMapField($timeout, FieldsService) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/google-map/google-map-field.html",
      scope: {
        fieldInformation: "=",
        fieldRootScope: "=?",
        form: "=?",
        readonly: "=?"
      },
      link: function (scope) {
        var optionsData = scope.fieldInformation.options;

        scope.fieldInformation.fieldValueList = scope.fieldInformation.fieldValueList ? scope.fieldInformation.fieldValueList : [];
        scope.$on("RepaintMap", repaintMap);

        function loadOptions() {
          switch(optionsData.type){
            case "internal_dependency_options":
              internalDependencyOptionsHandler();
              break;
          }
        }

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

        function internalDependencyOptionsHandler(){
          var fieldId = optionsData.fieldId;
          var fieldPath = FieldsService.getFieldPathByID(fieldId, scope.fieldRootScope);
          var field;

          if(!fieldPath) {
            return;
          }
          field = FieldsService.getFieldByPath(fieldPath, scope.fieldRootScope);
          if (field.fieldValueList) {
            scope.options = field.fieldValueList;
          }
          var destroyInDependencyWatcher = scope.$watch(function () {
            field = FieldsService.getFieldByPath(fieldPath, scope.fieldRootScope);

            var fieldWithIdentifier = field.fieldValueList && field.fieldValueList[0] && _.isNumber(field.fieldValueList[0].identifier);

            if(fieldWithIdentifier){
              return field.fieldValueList[0].identifier;
            }
          },function (newIdentifier, oldIdentifier) {
            if(newIdentifier === oldIdentifier){
              return;
            }
            var optionSelected = _.find(field.fieldOptions, function (option) {
              return field.fieldValueList[0].identifier === option.id
            });

            scope.location.center.latitude = optionSelected.defaultLatCoordinate;
            scope.location.center.longitude = optionSelected.defaultLngCoordinate;
            scope.location.zoom = optionSelected.defaultZoom;
          });
          scope.$on("$destroy",destroyInDependencyWatcher);
        }

        loadOptions();
        loadLocation();
        watchRawLocation();
      }
    };
  }
})();
