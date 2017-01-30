(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('itemList', itemList);

  function itemList($mdMedia, translateFilter) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/item-list/item-list.html",
      scope: {
        list: "=",
        mobileItems: "=?",
        tabletItems: "=?",
        desktopItems: "=?",
        display: "@?",
        labelAttribute: "=?",
        translateLabel: "=?"
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
        scope.$watch("labelAttribute", function () {
          if(scope.labelAttribute)
            scope.label = scope.labelAttribute.split(".");
        });

        scope.getLabel = getLabel;

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
          if(scope.list){
            cropList();
          }
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

        function getLabel(element) {
          if(!scope.label){return element;}
          var label = angular.copy(element);
          _.each(scope.label, function (attribute) {
            label = label[attribute];
          });
          label = scope.translateLabel ? translateFilter(label) : label;
          return label;
        }
      }
    };
  }
})();
