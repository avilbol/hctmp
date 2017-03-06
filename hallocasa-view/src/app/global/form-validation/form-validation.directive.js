(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('formValidation', formValidation);

  function formValidation($timeout) {
    return {
      restrict: "A",
      scope: {
        tabList: "=",
        name: "@"
      },
      link: function (scope, element) {

        element.on("submit", function (event) {
          var form = scope.$parent[scope.name];
          var formElement = element.find("ng-form");
          var formScope = angular.element(formElement).scope();

          if(form.$invalid){
            event.preventDefault();
            _.find(scope.tabList, function (tab) {
              var tabForm = form[tab.name];
              if(tabForm.$invalid) {
                var formTabElement = formElement.filter("[name='"+tab.name+"']");
                angular.element(formTabElement).scope()
                  .$broadcast("FormValidation:DetectInvalidFields");
                formScope.$broadcast("FormValidation:ShowValidationMessages");

                tabForm.$setSubmitted();

                tab.isActive = true;
                $timeout(function () {
                  var invalidField = formTabElement
                    .find(".ng-invalid")
                    .first();
                  invalidField.focus();
                }, 1000);
              }
              return tabForm.$invalid;
            });
          }
        });
      }
    };
  }
})();
