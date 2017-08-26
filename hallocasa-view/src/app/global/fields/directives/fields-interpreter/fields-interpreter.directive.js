(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .directive('fieldsInterpreter', fieldsInterpreter);

  function fieldsInterpreter($timeout) {
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
        readonly: "=?",
        fieldRendered: "=?"
      },
      link: function (scope) {

        function watchForm() {
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

        scope.showField = function(field) {
          if(!scope.readonly || !field || !field.fieldList){
            return true;
          }
          return _.some(field.fieldList, function(fieldItem){
            return _.has(fieldItem, "fieldValueList") && fieldItem.fieldValueList.length > 0;
          });
        }
        /*
         * This code will run after
         * templateUrl has been loaded, cloned
         * and transformed by directives.
         * and properly rendered by the browser
         * http://lorenzmerdian.blogspot.com.co/2013/03/how-to-handle-dom-updates-in-angularjs.html
         * */
        function watchRender() {
          $timeout(function () {
            $timeout(function () {
              scope.fieldRendered = true;
            }, 0);
          }, 0);
        }

        scope.multipleFieldsInfo = function(field){
          if(field.options.type !== "Dynamic Info"){
            return [];
          }
          return _.pluck(_.filter(scope.fieldList, function(field) {
            return _.contains(field.options.relatedFields, field.id);
          }), "fieldValueList");
        }

        watchForm();
        watchRender();

      }
    };
  }
})();
