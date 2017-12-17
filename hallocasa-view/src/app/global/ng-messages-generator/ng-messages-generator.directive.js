(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('ngMessagesGenerator', ngMessagesGenerator);

  function ngMessagesGenerator() {
    return {
      restrict: 'EA',
      templateUrl: "app/global/ng-messages-generator/ng-messages-generator.html",
      scope: {
        form: "=",
        validators: "@",
        messagesParams: "=",
        inputName: "@",
        validationParams: "=?"
      },
      link: function (scope) {
        scope.$watch("showMessages", function () {
          var messagesObject = _.object(scope.validators.split(' ').join('').split(","), []);
          scope.messages = _.mapObject(messagesObject, function () {return true;});
        });

        scope.matchPassword = function () {
          return scope.validationParams.passwordToMatch.firstPassword === scope.validationParams.passwordToMatch.secondPassword;
        }
      }
    };
  }
})();
