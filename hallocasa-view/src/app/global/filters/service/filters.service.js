(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .service('FiltersService', FiltersService);

  function FiltersService($log) {
    var service = {
      generateFiltersRender: generateFiltersRender
    };

    var totalFilters, filtersRendered;
    var componentsIdentifiers = ["accordion_group"];

    return service;

    function generateFiltersRender(filtersDataList, filtersRender) {
      totalFilters = filtersDataList.length;
      filtersRendered = 0;
      filtersRender = concatFiltersData(filtersRender, filtersDataList);

      $log.debug("Campos totales: "+totalFilters+", Campos cargados: "+filtersRender);

      return filtersRender;
    }

    function concatFiltersData(filtersRender, filtersDataList){
      return _.chain(filtersRender)
        .each(function(filterRenderInfo, filterIndex){
          if(isComponentFilter(filterRenderInfo)){
            concatFiltersData(filterRenderInfo.filterList, filtersDataList);
            return;
          }

          var filterData = searchFilterById(filterRenderInfo.id, filtersDataList);
          if(filterData) {
            filterData.filter = _.extend(filterData.filter, filterRenderInfo);
            filtersRender[filterIndex] = filterData;
            filtersRendered += 1;
          }
        })
        .compact()
        .value();
    }

    function isComponentFilter(filter) {
      return _.contains(componentsIdentifiers, filter.id);
    }

    function searchFilterById(filterId, filterDataList) {
      return _.find(filterDataList, function (fieldData) {return fieldData.filter.id === filterId});
    }
  }
})();
