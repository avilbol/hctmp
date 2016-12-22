(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('accordionGroup', accordionGroup);

  function accordionGroup(translateFilter, $log) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/components/accordion-group/accordion-group.html",
      scope: {
        title: "=?",
        fieldList: "=",
        fieldScope: "=?",
        fieldRootScope: "=?"
      },
      link: function (scope) {
        function renderTitle() {
          scope.renderedTitle = "";
          _.each(scope.title, function (titleSegment) {
            switch (titleSegment.type){
              case "translate_key":
                scope.renderedTitle += translateFilter(titleSegment.value);
                break;
              case "literal_string":
                scope.renderedTitle += titleSegment.value;
                break;
              case "binding_field_scope":
                scope.renderedTitle += scope.fieldScope[titleSegment.value];
                break;
              default:
                $log.warn("Tipo de segmento de título desconocido: ",titleSegment);
                scope.renderedTitle += titleSegment.value;
            }
            scope.renderedTitle += " ";
          });
        }

        var destroyWatcher = scope.$watch("title",renderTitle);
        scope.$on("$destroy",destroyWatcher);
      }
    };
  }
})();
