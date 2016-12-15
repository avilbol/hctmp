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
      "<md-menu>"+
      "<md-button ng-click='$mdOpenMenu($event)'>"+
      "<i class='material-icons'>language</i> <div>{{'directives.language-select.Language' | translate}}</div>"+
      "</md-button>"+
      "<md-menu-content>"+
      "<md-menu-item ng-repeat='localesDisplayName in localesDisplayNames'>" +
      "<md-button ng-click='changeLanguage(localesDisplayName)'>{{localesDisplayName}}</md-button>" +
      "</md-menu-item>"+
      "</md-menu-content>"+
      "</md-menu>",
      controller: function ($scope) {
        $scope.currentLocaleDisplayName = LocaleService.getLocaleDisplayName();
        $scope.localesDisplayNames = LocaleService.getLocalesDisplayNames();
        $scope.visible = $scope.localesDisplayNames &&
          $scope.localesDisplayNames.length > 1;

        $scope.changeLanguage = function (locale) {
          LocaleService.setLocaleByDisplayName(locale);
        };
      }
    };
  }
})();
