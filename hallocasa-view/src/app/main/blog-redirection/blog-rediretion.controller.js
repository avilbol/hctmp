(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .controller('BlogRedirectionController', BlogRedirectionController);

  /** @ngInject */
  function BlogRedirectionController($location, BlogLinks, LocaleService, $window) {

    function blogRedirection(section) {
      section = section ? section : "blog";
      var currentLanguage = LocaleService.getLocaleDisplayName();
      currentLanguage = currentLanguage ? currentLanguage : "English";
      $window.location.href = BlogLinks[currentLanguage][section];
    }

    function detectBlogSection() {
      var section;
      var currentURL = $location.url();

      if(currentURL.includes("pages/blog")) {
        section = "blog";
      }
      if(currentURL.includes("pages/buyprocess")) {
        section = "buyProcess";
      }
      if(currentURL.includes("pages/links")) {
        section = "links";
      }

      blogRedirection(section);
    }

    detectBlogSection();
  }
})();
