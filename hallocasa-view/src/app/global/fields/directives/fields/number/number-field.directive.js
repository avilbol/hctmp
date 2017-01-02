(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('numberField', numberField);

  function numberField($log) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/number/number-field.html",
      scope: {
        fieldScope: "=?",
        fieldInformation: "="
      },
      link: function (scope) {
        var fieldTypes =[
          {
            id: "standard_field",
            fieldDescriptor: {
              "textType": "INT",
              "data1Type": "SAME",
              "data2Type": "SAME",
              "data3Type": "SAME"
            }
          },
          {
            id: "double_field",
            fieldDescriptor: {
              "textType": "DOUBLE",
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
              "data2Type":"DOUBLE",
              "data3Type":"SAME"
            }
          }
        ];

        var fieldValueList = scope.fieldInformation.fieldValueList;

        function validFieldModel(fieldType) {
          var baseValidation = (_.isArray(fieldValueList) && fieldValueList.length > 0);

          switch (fieldType){
            case "scope_dependent_field":
              var identifier = scope.fieldScope.identifier;
              return (baseValidation && _.isObject(fieldValueList[identifier]));
            default:
              return baseValidation;
          }
        }


        function buildFieldModel(fieldType) {
          if(validFieldModel(fieldType)){
            return;
          }

          var fieldValue = {};
          scope.fieldInformation.fieldValueList = fieldValueList ? fieldValueList : [];

          switch (fieldType){
            case "scope_dependent_field":
              fieldValue.data1 = {
                intVal: scope.fieldScope.identifier
              };
              scope.fieldInformation.fieldValueList[scope.fieldScope.identifier] = fieldValue;
              break;
          }
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
