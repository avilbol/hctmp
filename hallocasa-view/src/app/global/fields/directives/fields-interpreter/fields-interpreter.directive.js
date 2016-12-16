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
        fieldScope: "=?"
      },
      link: function () {

      }
    };
  }
})();