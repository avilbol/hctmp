(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .directive('fieldsInterpreter', fieldsInterpreter);

  function fieldsInterpreter() {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields-interpreter/fields-interpreter.html",
      scope: {
        fieldList: "=?",
        fieldScope: "=?",
        fieldRootScope: "=?",
        additionalParameters: "=?",
        formName: "=?",
        form: "=?",
        readonly: "=?"
      },
      link: function (scope) {
        if(!scope.form){
          var destroyWatcher = scope.$watch("[formName, form]", function () {
            if(!scope.form && scope.formName){
              scope.form = scope.$parent[scope.formName];
            }
            if(scope.form){
              destroyWatcher();
            }
          });
        }

        scope.multipleFieldsInfo = function(field){
          if(field.options.type !== "Dynamic Info"){
            return [];
          }
          return _.pluck(_.filter(fieldList, function(field) {
            return _.contains(field.options.relatedFields, field.id);
          }), "fieldValueList");
        }
      }
    };
  }
})();
