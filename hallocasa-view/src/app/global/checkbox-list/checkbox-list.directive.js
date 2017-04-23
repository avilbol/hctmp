(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('checkboxList', checkboxList);

  function checkboxList(translateFilter, resolveFilter) {
    return {
      restrict: 'EA',
      require: 'ngModel',
      templateUrl: "app/global/checkbox-list/ckeckbox-list.html",
      scope: {
        laptopCols: '=?',
        tabletCols: '=?',
        mobileCols: '=?',
        data: '=',
        optionsLabel: '=?',
        translateLabel: '=?',
        trackBy: '=?',
        ngModel: '=',
        requireOnce: '=',
        requireAll: '='
      },
      link: function (scope, element, attrs, ngModel) {
        scope.toggle = toggle;
        scope.exists = exist;
        scope.getLabel = getLabel;

        scope.mobileCols = (scope.mobileCols ? scope.mobileCols : 1);
        scope.tabletCols = (scope.tabletCols ? scope.tabletCols : 2);
        scope.laptopCols = (scope.laptopCols ? scope.laptopCols : 3);
        scope.flexMobile = Math.floor(100 / scope.mobileCols);
        scope.flexTablet = Math.floor(100 / scope.tabletCols);
        scope.flexLaptop = Math.floor(100 / scope.laptopCols);

        function toggle(item) {
          ngModel.$setTouched();
          scope.ngModel = scope.ngModel ? scope.ngModel : [];
          var idx;
          if(scope.trackBy){
            _.find(scope.ngModel, function (model, index) {
              var found = resolveFilter(model,scope.trackBy) === resolveFilter(item,scope.trackBy);
              idx = found ? index : undefined;
              return found;
            });
            idx = _.isNumber(idx) ? idx : -1;
          }
          else {
            idx = scope.ngModel.indexOf(item);
          }

          if (idx > -1) {
            scope.ngModel.splice(idx, 1);
          }
          else {
            scope.ngModel.push(item);
          }
          ngModel.$validate()
        }

        function exist(item) {
          scope.ngModel = scope.ngModel ? scope.ngModel : [];
          if(scope.trackBy){
            return _.find(scope.ngModel, function (listElement) {
              return resolveFilter(listElement, scope.trackBy) === resolveFilter(item, scope.trackBy);
            });
          }
          else {
            return scope.ngModel.indexOf(item) > -1;
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

        var watcherOL = scope.$watch("optionsLabel", function () {
          if(scope.optionsLabel)
            scope.label = scope.optionsLabel.split(".");
        });

        scope.$on("$destroy", watcherOL);

        ngModel.$validators.requireOnce = function(modelValue) {
          scope.requireOnceValidator = scope.requireOnce ? modelValue && modelValue.length > 0 : true;
          return scope.requireOnceValidator;
        };

        ngModel.$validators.requireAll = function(modelValue) {
          scope.requireAllValidator = scope.requireAll ? modelValue && modelValue.length === scope.data.length : true;
          return scope.requireAllValidator;
        };
      }
    };
  }
})();
