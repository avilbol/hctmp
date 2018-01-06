(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .directive('carousel', carousel);

  function carousel($interval, $timeout) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/carousel/carousel.html",
      scope: {
        images: "="
      },
      link: function(scope) {
        var interval;
        scope.$watch("images.length", setImagesInterval);
        scope.nextImage = nextImage;
        scope.previousImage = previousImage;
        scope.startTouch = startTouch;
        scope.changeCurrentImage = changeCurrentImage;

        function startTouch(){
          $interval.cancel(interval);
        }

        function setImagesInterval() {
          if(!scope.images || !scope.images.length){
            return;
          }
          scope.currentImage = 0;

          if(interval){
            $interval.cancel(interval);
          }

          interval = $interval(function () {
            nextImage();
          }, 5000);
        }

        function nextImage() {
          scope.direction = "left";
          $timeout(function () {
            scope.currentImage = (scope.currentImage + 1) % scope.images.length;
          },0);
        }

        function previousImage() {
          scope.direction = "right";
          $timeout(function () {
            scope.currentImage = scope.currentImage - 1 >= 0 ? scope.currentImage - 1 : scope.images.length-1;
          },0);
        }

        function changeCurrentImage(key) {
          $timeout(function () {
            scope.currentImage = key;
          },0);
        }
      }
    };
  }
})();
