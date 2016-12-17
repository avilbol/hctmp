(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('textField', textField);

  function textField($log) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/text/text-field.html",
      scope: {
        fieldScope: "=?",
        fieldInformation: "="
      },
      link: function (scope) {
        var fieldTypes =[
          {
            id: "standard_field",
            fieldDescriptor: {
              "textType": "TEXT",
              "data1Type": "SAME",
              "data2Type": "SAME",
              "data3Type": "SAME"
            }
          },
          {
            id: "scope_dependent_field",
            fieldDescriptor: {
              "textType":"SAME",
              "data1Type":"INT",
              "data2Type":"TEXT",
              "data3Type":"SAME"
            }
          }
        ];


        function buildFieldModel(fieldType) {
          var validFieldModel = (
            _.isArray(scope.fieldInformation.fieldValueList) &&
            scope.fieldInformation.fieldValueList.length > 0
          );

          if(validFieldModel){
            return;
          }

          scope.fieldInformation.fieldValueList = [];
          var fieldValue = {};

          switch (fieldType){
            case "standard_field":
              fieldValue.text = {};
              break;
            case "scope_dependent_field":
              fieldValue.data1 = {
                intVal: scope.fieldScope.id
              };
              break;
          }
          scope.fieldInformation.fieldValueList.push(fieldValue);
        }

        function detectFieldType() {
          var fieldTypeDescriptor =  _.pick(scope.fieldInformation, "textType", "data1Type", "data2Type", "data3Type");
          scope.fieldType = _.find(fieldTypes, function (fieldType) {
            return _.isEqual(fieldType.fieldDescriptor, fieldTypeDescriptor);
          });

          if(!scope.fieldType){
            $log.warn("No reconoce descriptor de tipo de campo de texto:", fieldTypeDescriptor);
            return;
          }

          buildFieldModel(scope.fieldType.id);
        }

        detectFieldType();
      }
    };
  }
})();
