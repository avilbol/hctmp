(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('dropdownField', dropdownField);

  function dropdownField(FieldsService, toastr) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/dropdown/dropdown-field.html",
      scope: {
        fieldScope: "=?",
        fieldInformation: "="
      },
      link: function (scope) {
        var optionsData = scope.fieldInformation.options;
        var staticOptionsGroup = scope.fieldInformation.dropdownOptionGroup;

        function loadOptions() {
          switch(optionsData.type){
            case "static_options":
              scope.options = FieldsService.processOptions(staticOptionsGroup.dropdownOptionList, staticOptionsGroup.translationManagement);
              break;
            case "dynamic_options":
              var serviceId = optionsData.serviceId;
              FieldsService.loadOptionsByServiceId(serviceId)
                .then(function (options) {
                  scope.options = FieldsService.processOptions(options, "NONE");
                })
                .catch(function () {
                  //TODO: Traducción de mensaje de error
                  toastr.warning("Error al cargar opciones del servicio:", serviceId);
                  scope.options = [];
                });
              break;
            case "binary_options":
              scope.options = [
                {name: "Yes", value: true},
                {name: "No", value: false}
              ];
              break;

          }
        }

        loadOptions();
      }
    };
  }
})();
