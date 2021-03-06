(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('flatGroup', flatGroup);

  function flatGroup(translateFilter, $log) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/components/flat-group/flat-group.html",
      scope: {
        groupTitle: "=?",
        fieldList: "=",
        fieldScope: "=?",
        fieldRootScope: "=?",
        additionalParameters: "=?",
        form: "=?",
        readonly: "=?"
      },
      link: function (scope) {
        var destroyWatcher = scope.$watch("groupTitle", scope.renderTitle);
        scope.$on("$destroy",destroyWatcher);

        scope.renderTitle = function(){
          scope.renderedTitle = '';
          _.each(scope.groupTitle, function (titleSegment) {
            switch (titleSegment.type){
              case "translate_key":
                scope.renderedTitle += translateFilter(titleSegment.value);
                break;
              case "literal_string":
                scope.renderedTitle += titleSegment.value;
                break;
              case "binding_field_scope":
                scope.renderedTitle += "{{fieldScope['"+titleSegment.value+"'] | unicode}}";
                break;
              default:
                $log.warn("Tipo de segmento de título desconocido: ",titleSegment);
                scope.renderedTitle += titleSegment.value;
            }
            scope.renderedTitle += " ";
          });
          return scope.renderedTitle;
        }
      }
    };
  }
})();
