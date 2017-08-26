(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('scrollOnClick', scrollOnClick);

  function scrollOnClick() {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {

            element.on('click', function () {
                angular.element("#mainContainer").animate({ scrollTop: 0 }, "slow");
            });
        }
    };
  }
})();