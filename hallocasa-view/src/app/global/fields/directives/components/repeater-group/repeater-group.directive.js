(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('repeaterGroup', repeaterGroup);

  function repeaterGroup(FieldsService) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/components/repeater-group/repeater-group.html",
      scope: {
        idField: "=?",
        fieldList: "=?",
        fieldRootScope: "=?",
        contentFlex: "=?",
        fieldWatchParameter: "=?",
        form: "=?"
      },
      link: function (scope) {
        var fieldPath = FieldsService.getFieldPathByID(scope.idField, scope.fieldRootScope);

        if(fieldPath) {
          var field = FieldsService.getFieldByPath(fieldPath, scope.fieldRootScope);
          if (field.fieldValueList) {
            scope.valueList = field.fieldValueList;
          }
          var destroyWatcher = scope.$watch(function () {
            field = FieldsService.getFieldByPath(fieldPath, scope.fieldRootScope);
            if(scope.fieldWatchParameter && field.fieldValueList && field.fieldValueList[0] && !field.fieldValueList[0][scope.fieldWatchParameter]){
              return;
            }
            if (field.fieldValueList) {
              return field.fieldValueList.length;
            }
          }, function (newValue, oldValue) {
            if (newValue !== oldValue) {
              scope.valueList = field.fieldValueList;
            }
          });

          scope.$on("$destroy",destroyWatcher);
        }
      }
    };
  }
})();
