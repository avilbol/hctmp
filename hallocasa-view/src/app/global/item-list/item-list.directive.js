(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('itemList', itemList);

  function itemList($mdMedia) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/item-list/item-list.html",
      scope: {
        list: "=",
        mobileItems: "=?",
        tabletItems: "=?",
        desktopItems: "=?",
        display: "@?"
      },
      link: function (scope) {
        scope.mobileItems = angular.isNumber(scope.mobileItems) ? scope.mobileItems : 2;
        scope.tabletItems = angular.isNumber(scope.tabletItems) ? scope.tabletItems : 4;
        scope.desktopItems = angular.isNumber(scope.desktopItems) ? scope.desktopItems : 7;
        scope.display = scope.display ? scope.display : "row";

        scope.$watchCollection("[mobileItems, tabletItems, desktopItems, list]", calculateList);
        scope.$watch(function() { return $mdMedia('xs'); }, calculateList);
        scope.$watch(function() { return $mdMedia('sm'); }, calculateList);
        scope.$watch(function() { return $mdMedia('gt-sm'); }, calculateList);

        function calculateList() {
          if($mdMedia('xs')){ //Mobile
            scope.maxItems = scope.mobileItems;
          }
          if($mdMedia('sm')){ //Tablet
            scope.maxItems = scope.tabletItems;
          }
          if($mdMedia('gt-sm')){ //Desktop
            scope.maxItems = scope.desktopItems;
          }
          cropList();
        }

        function cropList() {
          if(scope.list.length < scope.maxItems){
            scope.viewList = scope.list;
          }
          else{
            scope.viewList = [];
            _.times(scope.maxItems, function (index) {
              scope.viewList.push(scope.list[index]);
            });
          }
        }
      }
    };
  }
})();
