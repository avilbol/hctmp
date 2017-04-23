(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .directive('ngTranslateLanguageSelect', ngTranslateLanguageSelect);

  function ngTranslateLanguageSelect(LocaleService) {
    return {
      restrict: 'A',
      replace: true,
      template:
      "<div>"+
      "<md-menu layout-fill layout='row' layout-align='center stretch'>"+
      "<md-button ng-click='$mdMenu.open($event)' aria-label='Open language dropdown' class='bold-hover'>"+
      "<div>{{currentLanguage}}</div>"+
      "</md-button>"+
      "<md-menu-content>"+
      "<md-menu-item ng-repeat='localesDisplayName in localesDisplayNames'>" +
      "<md-button ng-click='changeLanguage(localesDisplayName)'>{{localesDisplayName}}</md-button>" +
      "</md-menu-item>"+
      "</md-menu-content>"+
      "</md-menu>"+
      "</div>",
      controller: function ($scope) {
        $scope.currentLocaleDisplayName = LocaleService.getLocaleDisplayName();
        $scope.currentLanguage = $scope.currentLocaleDisplayName;
        $scope.localesDisplayNames = LocaleService.getLocalesDisplayNames();
        $scope.visible = $scope.localesDisplayNames &&
          $scope.localesDisplayNames.length > 1
        $scope.changeLanguage = function (locale) {
          LocaleService.setLocaleByDisplayName(locale);
          $scope.currentLanguage = locale;
        };
      }
    };
  }
})();
