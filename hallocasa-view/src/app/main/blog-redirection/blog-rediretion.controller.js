(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .controller('BlogRedirectionController', BlogRedirectionController);

  /** @ngInject */
  function BlogRedirectionController($location, BlogLinks, LocaleService, $window) {

    function blogRedirection(section) {
      var currentLanguage = LocaleService.getLocaleDisplayName();
      $window.location.href = BlogLinks[currentLanguage][section];
    }

    function detectBlogSection() {
      var section;
      var currentURL = $location.url();

      if(currentURL.includes("pages/blog")) {
        section = "blog";
      }
      if(currentURL.includes("pages/buyprocess")) {
        section = "buyprocess";
      }
      if(currentURL.includes("pages/links")) {
        section = "links";
      }

      blogRedirection(section);
    }

    detectBlogSection();
  }
})();
