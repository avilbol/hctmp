(function() {
  'use strict';

  angular
    .module('HalloCasa.property')
    .directive('fieldsInterpreter', fieldsInterpreter);

  function fieldsInterpreter() {
    return {
      restrict: 'EA',
      templateUrl: "app/property/property-fields/directives/fields-interpreter/fields-interpreter.html",
      scope: {
      },
      link: function (scope) {

      }
    };
  }
})();
