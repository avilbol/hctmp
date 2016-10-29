(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .service('VirtualRepeatUtilsService', VirtualRepeatUtilsService);

  /** @ngInject */
  function VirtualRepeatUtilsService($window) {

    var service = {
      heightCalculator: heightCalculator,
      getVirtualRepeatInstance: getVirtualRepeatInstance
    };

    return service;

    function heightCalculator(container, scope) {
      angular.element(container).height(0);
      var onResize = _.debounce(function () {
        scope.$digest();
      }, 250);

      $window.addEventListener('resize', onResize);

      scope.$on('$destroy', function () {
        $window.removeEventListener('resize', onResize);
      });

      return function() {
        return {height: '' + (angular.element(container).height()) + 'px'};
      };
    }

    function getVirtualRepeatInstance(fetchItemsFunction, itemsToLoad) {
      var instance = {
        toLoad_: 0,
        list: [],

        getItemAtIndex: function(index) {
          if (index > instance.list.length) {
            instance.fetchMoreItems_(index);
            return null;
          }

          return instance.list[index];
        },

        getLength: function() {
          return instance.list.length + 1;
        },

        fetchMoreItems_: function(index) {
          if (instance.toLoad_ < index) {
            instance.toLoad_ += 10;
            fetchItemsFunction(instance.list.length, instance.list.length + itemsToLoad)
              .then(function (items) {
                instance.list = _.union(instance.list, items);
              });
          }
        }
      };

      return instance;
    }

  }
})();
