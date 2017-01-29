(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('checkboxGroupField', checkboxGroupField);

  function checkboxGroupField(FieldsService, toastr) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/checkbox-group/checkbox-group-field.html",
      scope: {
        fieldScope: "=?",
        fieldInformation: "=",
        form: "=?"
      },
      link: function (scope) {
        var optionsData = scope.fieldInformation.options;
        var staticOptionsGroup = scope.fieldInformation.dropdownOptionGroup;
        
        function applyValidations(){
          if(scope.fieldInformation.validations){
            scope.requireOnce = scope.fieldInformation.validations.includes("requireOnce");
            scope.requireAll = scope.fieldInformation.validations.includes("requireAll");
          }
        }
        

        function loadOptions() {
          switch(optionsData.type){
            case "static_options":
              scope.options = FieldsService.processOptions(staticOptionsGroup.dropdownOptionList, staticOptionsGroup.translationManagement);
              scope.optionsLabel = "data1";
              break;
            case "dynamic_options":
              var serviceId = optionsData.serviceId;
              FieldsService.loadOptionsByServiceId(serviceId)
                .then(function (options) {
                  scope.options = FieldsService.processOptions(options, "NONE");
                  scope.optionsLabel = "name";
                })
                .catch(function () {
                  //TODO: Traducción de mensaje de error
                  toastr.warning("Error al cargar opciones del servicio:", serviceId);
                  scope.options = [];
                });

              break;
          }
        }

        loadOptions();
        applyValidations();
      }
    };
  }
})();
