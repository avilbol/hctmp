(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('itemList', itemList);

  function itemList($mdMedia, unicodeFilter, resolveFilter) {
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
        translateLabel: "=?",
        translateVal: "=?"
      },
      link: function (scope) {
        scope.mobileItems = angular.isNumber(scope.mobileItems) ? scope.mobileItems : 2;
        scope.tabletItems = angular.isNumber(scope.tabletItems) ? scope.tabletItems : 4;
        scope.desktopItems = angular.isNumber(scope.desktopItems) ? scope.desktopItems : 7;
        scope.generateRowLabel = generateRowLabel;
        scope.display = scope.display ? scope.display : "row";

        scope.$watch("labelAttribute",updateLabel);
        scope.$watch("list",updateLabel);
        scope.$watch("translateVal",updateLabel);

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

        function updateLabel(oldVal, newVal) {
          if(oldVal === newVal && scope.label){
            return;
          }
          calculateList();

          if(scope.display === "row"){
            scope.label = generateRowLabel();
          }
          else{
            scope.label = [];
            _.each(scope.viewList, function (itemList) {
              scope.label.push(getLabel(itemList));
            });
          }
        }

        function getLabel(element) {
          if(!scope.labelAttribute){
            return !_.isObject(element) ? element : undefined;
          }
          var label = resolveFilter(element, scope.labelAttribute);
          return label;
        }

        function generateRowLabel() {
          if(!scope.viewList){
            return;
          }

          var label = "";
          _.each(scope.viewList, function (itemList, index) {
            var rawLabel = getLabel(itemList);
            if(!rawLabel){return;}
            if(scope.translateLabel){
              label += "{{'"+rawLabel+"' | translate}}";
            }
            else{
              label += unicodeFilter(rawLabel);
            }
            if(index < scope.viewList.length - 1){
              label += ", ";
            }
          });
          if(label && scope.list.length > scope.maxItems){
            label += ", " + "<span class='link'>+" + (scope.list.length - scope.maxItems) + "</span>";
          }
          return label;
        }
      }
    };
  }
})();
