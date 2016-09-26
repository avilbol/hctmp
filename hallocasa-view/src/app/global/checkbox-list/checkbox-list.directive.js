(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('checkboxList', checkboxList);

  function checkboxList() {
    return {
      restrict: 'EA',
      require: 'ngModel',
      templateUrl: "app/global/checkbox-list/ckeckbox-list.html",
      scope: {
        laptopCols: '=?',
        tabletCols: '=?',
        mobileCols: '=?',
        data: '=',
        dataLabel: '=?',
        ngModel: '=',
        requireOnce: '=',
        requireAll: '='
      },
      link: function (scope, element, attrs, ngModel) {
        scope.toggle = toggle;
        scope.exists = exist;

        scope.mobileCols = (scope.mobileCols ? scope.mobileCols : 1);
        scope.tabletCols = (scope.tabletCols ? scope.tabletCols : 2);
        scope.laptopCols = (scope.laptopCols ? scope.laptopCols : 3);
        scope.flexMobile = Math.floor(100 / scope.mobileCols);
        scope.flexTablet = Math.floor(100 / scope.tabletCols);
        scope.flexLaptop = Math.floor(100 / scope.laptopCols);

        function toggle(item) {
          ngModel.$setTouched();
          scope.ngModel = scope.ngModel ? scope.ngModel : [];
          var idx = scope.ngModel.indexOf(item);
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
          return scope.ngModel.indexOf(item) > -1;
        }

        ngModel.$validators.requireOnce = function(modelValue) {
          scope.requireOnceValidator = scope.requireOnce ? modelValue.length > 0 : true;
          return scope.requireOnceValidator;
        };

        ngModel.$validators.requireAll = function(modelValue) {
          scope.requireAllValidator = scope.requireAll ? modelValue.length === scope.data.length : true;
          return scope.requireAllValidator;
        };
      }
    };
  }
})();
