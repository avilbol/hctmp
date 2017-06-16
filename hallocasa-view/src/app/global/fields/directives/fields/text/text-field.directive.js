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
        form: "=?",
        readonly: "=?"
      },
      link: function (scope) {
        scope.fieldName = scope.$id;

        console.log(scope.fieldInformation);

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
              if(!baseValidation){
                return false;
              }
              var foundValues;
              for(var index in fieldValueList){
                var fieldValue = fieldValueList[index];
                var indexAlreadyRelocated = Number(index) < 0;
                foundValues = (fieldValue && fieldValue.data1 && fieldValue.data1.intVal === identifier);

                if(indexAlreadyRelocated && foundValues){
                  scope.relocatedIndex = index;
                  break;
                }

                if(foundValues){
                  scope.relocatedIndex = identifier * -1;
                  fieldValueList[scope.relocatedIndex] = angular.copy(fieldValue);
                  delete fieldValueList[index];
                  break;
                }
              }
              return (baseValidation && foundValues);
            default:
              return baseValidation;
          }
        }

        function buildFieldModel(fieldType) {
          if(validFieldModel(fieldType)){
            if(scope.relocatedIndex){
              watchDestroyField(scope.relocatedIndex);
            }
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
              watchDestroyField(scope.fieldScope.identifier);
              scope.relocatedIndex = scope.fieldScope.identifier;
              break;
          }
        }

        function watchDestroyField(identifier) {
          scope.$on("$destroy", function () {
            var valueList = scope.fieldInformation.fieldValueList[identifier];
            var filledField = valueList && valueList.data1 && valueList.data2 && valueList.data1.intVal && valueList.data2.strVal;
            if(!filledField){
              delete scope.fieldInformation.fieldValueList[identifier];
            }
          });
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
