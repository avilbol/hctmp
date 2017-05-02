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
      }
    };
  }
})();
