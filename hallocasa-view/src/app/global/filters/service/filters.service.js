(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .service('FiltersService', FiltersService);

  function FiltersService($log, backend_url, $resource, localStorageService, GenericRESTResource) {
    var service = {
      generateFiltersRender: generateFiltersRender,
      loadFiltersOptions: loadFiltersOptions,
      getFilterById: getFilterById,
      loadContext: loadContext,
      saveContext: saveContext
    };

    var resources = {
      loadOptions: $resource(backend_url + "property_filters/options/:filterID", {}, GenericRESTResource)
    };

    var totalFilters, filtersRendered;
    var componentsIdentifiers = ["accordion_group", "separator_group", "repeater_group"];

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

    function loadFiltersOptions(filterID, payload) {
      return resources.loadOptions.consult({filterID: filterID}, payload).$promise;
    }

    function getFilterById(filterID, filterRootScope) {
      var filterPath = getFilterPathByID(filterID, filterRootScope);
      return getFilterByPath(filterPath, filterRootScope);
    }

    function getFilterPathByID(filterID, filterRootScope) {
      var filterPath = getFilterPathInFiltersList(filterID, filterRootScope);
      var filterFound = filterPath.length > 0;

      if(!filterFound){
        $log.warn("No se pudo encontrar el campo con ID "+filterID+" en la lista de campos", filterRootScope);
      }
      return filterPath;
    }


    function getFilterPathInFiltersList(filterID, filterList) {
      var filterPath = [];
      _.find(filterList, function (filterInformation, filterIndex) {
        if(isComponentFilter(filterInformation)){
          filterPath = getFilterPathInFiltersList(filterID, filterInformation.filterList);
          var filterFound = filterPath.length > 0;
          if(filterFound){
            filterPath.unshift(filterIndex);
          }
          return filterFound;
        }
        var isSoughtFilter = (filterInformation.filter.id === filterID);
        if(isSoughtFilter){
          filterPath.unshift(filterIndex);
        }
        return isSoughtFilter;
      });
      return filterPath;
    }

    function getFilterByPath(filterPath, filterRootScope) {
      filterPath = angular.copy(filterPath);
      var filterList = filterRootScope;
      var filter = undefined;
      _.each(filterPath, function (patch) {
        filter = filterList[patch];
        filterList = filter.filterList;
      });
      return filter;
    }

    function loadContext(context) {
      var filtersContext = localStorageService.get("filtersContext");
      filtersContext = filtersContext ? filtersContext : {};

      if(!filtersContext[context]){
        filtersContext[context] = {};
        localStorageService.set("filtersContext", filtersContext);
      }

      return filtersContext[context];
    }

    function saveContext(context, contextData) {
      var filtersContext = localStorageService.get("filtersContext");
      filtersContext = filtersContext ? filtersContext : {};

      filtersContext[context] = contextData;
      localStorageService.set("filtersContext", filtersContext);
    }

  }
})();
