(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('pluralize', pluralize);

  function pluralize($mdDialog, $document) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/pluralize/pluralize.html",
      scope: { 
        list: "=",
        title: "@?"
      },
      link: function (scope) {
        scope.showList = showList;
        scope.title = scope.title ? scope.title : "";
        
        scope.$watch("list", function () {
          scope.linkStyle = scope.list.length > 2 ? {"cursor": "pointer"} : {}
        });
        
        function showList(ev) {
          if(scope.list.length < 2){
            return;
          }
          $mdDialog.show({
            controller: "PluralizeController",
            controllerAs: "vm",
            templateUrl: 'app/global/pluralize/pluralize-dialog.html',
            parent: $document.body,
            targetEvent: ev,
            clickOutsideToClose:true,
            locals: {
              list: scope.list,
              title: scope.title
            },
            fullscreen: false
          });
        }
      }
    };
  }
})();
