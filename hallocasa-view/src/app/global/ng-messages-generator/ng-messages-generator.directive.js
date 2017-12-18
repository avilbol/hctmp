(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('ngMessagesGenerator', ngMessagesGenerator);

  function ngMessagesGenerator(moment) {
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
        };

        scope.dateMinorThan = function () {
          if(!scope.validationParams.dateValidation.minorDate || !scope.validationParams.dateValidation.greaterDate){
            return true;
          }

          var validationsParams = scope.validationParams.dateValidation;
          var minorDate = moment(validationsParams.minorDate, validationsParams.dateFormat);
          var greaterDate = moment(validationsParams.greaterDate, validationsParams.dateFormat);

          scope.validationParams.dateValidation.greaterDateFormated = greaterDate.format(validationsParams.dateFormat);
          var valid = minorDate.isSameOrBefore(greaterDate);
          scope.form[scope.inputName].$setValidity("dateMinorThan", valid);
          return valid;
        };
      }
    };
  }
})();
