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
        groupTitle: "=?",
        fieldList: "=",
        fieldScope: "=?",
        fieldRootScope: "=?",
        additionalParameters: "=?",
        form: "=?",
        readonly: "=?"
      },
      link: function (scope, element) {
        var destroyWatcher = scope.$watch("groupTitle",renderTitle);
        scope.$on("$destroy",destroyWatcher);
        scope.$on("FormValidation:DetectInvalidFields",detectInvalidFields);

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
              case "binding_field_scope":
                scope.renderedTitle += "{{fieldScope['"+titleSegment.value+"'] | unicode}}";
                break;
              default:
                $log.warn("Tipo de segmento de título desconocido: ",titleSegment);
                scope.renderedTitle += titleSegment.value;
            }
            scope.renderedTitle += " ";
          });
        }

        function detectInvalidFields() {
          var invalidContainedFields = element.find(".ng-invalid");
          if(invalidContainedFields.length){
            scope.accordion.expandAll();
          }
        }
      }
    };
  }
})();
