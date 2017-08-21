(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('accordionFilterGroup', accordionFilterGroup);

  function accordionFilterGroup(translateFilter, $log) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/filters/directives/components/accordion-filter-group/accordion-filter-group.html",
      scope: {
        groupTitle: "=?",
        filtersList: "=",
        filtersScope: "=?",
        filtersRootScope: "=?"
      },
      link: function (scope) {
        var destroyWatcher = scope.$watch("groupTitle",renderTitle);
        scope.$on("$destroy",destroyWatcher);

        function renderTitle() {
          scope.renderedTitle = "";
          _.each(scope.groupTitle, function (titleSegment) {
            switch (titleSegment.type){
              case "translate_key":
                scope.renderedTitle += translateFilter(titleSegment.value);
                break;
              case "literal_string":
                scope.renderedTitle += titleSegment.value;
                break;
              default:
                $log.warn("Tipo de segmento de título desconocido: ",titleSegment);
                scope.renderedTitle += titleSegment.value;
            }
            scope.renderedTitle += " ";
          });
        }
      }
    };
  }
})();
