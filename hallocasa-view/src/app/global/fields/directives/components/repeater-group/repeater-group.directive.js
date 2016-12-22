(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('repeaterGroup', repeaterGroup);

  function repeaterGroup() {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/components/repeater-group/repeater-group.html",
      scope: {
        idField: "=?",
        fieldList: "=?",
        fieldRootScope: "=?",
        contentFlex: "=?"
      },
      link: function (scope) {
        scope.field = scope.fieldRootScope[0].fieldList[0].fieldList[0];
      }
    };
  }
})();
