(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('checkboxGroupField', checkboxGroupField);

  function checkboxGroupField(FieldsService, toastr, $translate) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/checkbox-group/checkbox-group-field.html",
      scope: {
        fieldScope: "=?",
        fieldInformation: "="
      },
      link: function (scope) {
        var optionsData = scope.fieldInformation.options;
        var staticOptionsGroup = scope.fieldInformation.dropdownOptionGroup;

        function processOptions(options) {
          var languageDependient = (optionsData.type === "static_options" && staticOptionsGroup.dependsOnLang);
          scope.optionsLabel = languageDependient ? "data1" : "name";
          return _.map(options, function (option) {
            option.identifier = (option.id || option.optionId);
            delete option.id;
            delete option.optionId;

            if(languageDependient){
              $translate(option.data1,undefined,undefined,option.name)
                .then(function (traslation) {
                  option.data1 = traslation;
                });
            }
            else{
              option.name = parseUnicode(option.name);
            }
            return option;
          })
        }

        function parseUnicode(string) {
          return decodeURIComponent(JSON.parse('"' + string.replace(/\"/g, '\\"') + '"'));
        }

        function loadOptions() {
          switch(optionsData.type){
            case "static_options":
              scope.options = processOptions(staticOptionsGroup.dropdownOptionList);
              break;
            case "dynamic_options":
              var serviceId = optionsData.serviceId;
              FieldsService.loadOptionsByServiceId(serviceId)
                .then(function (options) {
                  scope.options = processOptions(options);
                })
                .catch(function () {
                  //TODO: Traducción de mensaje de error
                  toastr.warn("Error al cargar opciones del servicio:", serviceId);
                  scope.options = [];
                });

              break;
          }
        }

        loadOptions();
      }
    };
  }
})();
