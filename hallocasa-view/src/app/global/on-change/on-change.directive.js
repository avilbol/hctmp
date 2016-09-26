(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('onChange', onChange);

  function onChange() {
    return {
      restrict: 'A',
      link: function (scope, element, attrs) {
        var onChangeHandler = scope.$eval(attrs.onChange);
        element.bind('change', onChangeHandler);
      }
    };
  }
})();
