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
        fieldInformation: "=",
        form: "=?"
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
        var fieldValueList = scope.fieldInformation.fieldValueList;

        function applyValidations() {
          if (scope.fieldInformation.validations) {
            scope.required = scope.fieldInformation.validations.includes("required");
          }
        }

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
              scope.on("$destroy", function () {
                delete scope.fieldInformation.fieldValueList[scope.fieldScope.identifier];
              });
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
        applyValidations();
      }
    };
  }
})();
