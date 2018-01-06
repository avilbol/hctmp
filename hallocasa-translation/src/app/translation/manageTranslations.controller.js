(function () {
  'use strict';
  // var moduleName = 'HalloCasaAdmin';

  //Declaro controllador
  angular.module('HalloCasaAdmin')
    .controller('ManageTranslationsController', ManageTranslationsController);

  function ManageTranslationsController($scope,
    $rootScope,
    TranslationService,
    $mdDialog,
    toastr) {

    var vm = this;

    vm.environments = [{
      id: 1,
      name: 'QA'
    },
    {
      id: 2,
      name: 'Production'
    },
    {
      id: 3,
      name: 'QA + Production'
    }
    ];
    vm.environment = {
      id: 1,
      name: 'QA'
    };

    vm.openModal = openModal;
    vm.newTranslation = newTranslation;
    vm.changeEnviroment = changeEnviroment;

    $scope.editTranslation = editTranslation;
    $scope.deleteTranslation = deleteTranslation;

    cleanModel();

    TranslationService.loadLocales(1).then(function (locations) {
      vm.locations = locations.data;
    });

    function changeEnviroment() {
      TranslationService.loadLocales(vm.environment.id).then(function (locations) {
        vm.locations = locations.data;
      });
    }

    // Find element by pnemonic and open modal
    function editTranslation(id) {
      var translation = _.find(vm.locations, function (item) {
        return item.pnemonic === id;
      });

      cleanModel();
      vm.translation = translation;
      vm.openModal(true);
    };

    // Find element by pnemonic and deleted element
    function deleteTranslation(id) {

      var translation = _.find(vm.locations, function (item) {
        return item.pnemonic === id;
      });

      var isConfirmDelete = confirm('Do you want delete this translation?');
      if (isConfirmDelete) {
        if (vm.environment.id === 3) {
          TranslationService.deleteLocale(translation.pnemonic, 1)
            .then(function () {
              // toastr.success('Delete Completed!');

              // TranslationService.loadLocales(vm.environment.id).then(function (locations) {
              //   vm.locations = locations.data;
              // });
              TranslationService.deleteLocale(translation.pnemonic, 2)
                .then(function () {
                  toastr.success('Delete Completed in QA and PRODUCTION!');

                  TranslationService.loadLocales(vm.environment.id).then(function (locations) {
                    vm.locations = locations.data;
                  });
                })
                .catch(function () {
                  toastr.error('Try late, problem in PRODUCTION', 'Error');
                });
            })
            .catch(function () {
              toastr.error('Try late, problem in QA', 'Error');
            });
        } else {
          TranslationService.deleteLocale(translation.pnemonic, vm.environment.id)
            .then(function () {
              toastr.success('Delete Completed in QA or PRODUCTION!');

              TranslationService.loadLocales(vm.environment.id).then(function (locations) {
                vm.locations = locations.data;
              });
            })
            .catch(function () {
              toastr.error('Try late, ', 'Error');
            });
        }
      } else {
        return false;
      }
    }

    function newTranslation() {
      cleanModel();
      openModal(false);
    }


    function openModal(editVal) {
      $mdDialog.show({
        controller: DialogController,
        templateUrl: 'dialog.new.lenguages.tmpl.html',
        parent: angular.element(document.body),
        clickOutsideToClose: true,
        fullscreen: false,
        locals: {
          elem: vm.translation,
          locations: vm.locations,
          edit: editVal,
          type: vm.environment.id
        }
      });
    }

    // Controller to Modal
    function DialogController($scope, $mdDialog, elem, edit, type, locations, TranslationService, toastr) {
      $scope.translation = elem;
      $scope.edit = edit;


      $scope.closeDialog = function closeDialog() {
        $mdDialog.cancel();
      };

      $scope.saveTranslation = function saveTranslation() {

        var pnemonicRepeat = _.find(locations, function (item) {
          return item.pnemonic === $scope.translation.pnemonic;
        });

        // if exist the label and it's a new element
        if ((pnemonicRepeat !== undefined) && (edit === false)) {
          toastr.error('The label is repeated, please change it', 'Error');
          return false;
        }

        // Save in QA
        if (type === 3) {
          // save element in QA
          TranslationService.saveLocale($scope.translation, 1)
            .then(function (locations) {
              // save element in PRODUCTION
              TranslationService.saveLocale($scope.translation, 2)
                .then(function (locations) {
                  toastr.success('Save Completed in QA + PRODUCTION!');

                  TranslationService.loadLocales(type).then(function (locations) {
                    vm.locations = locations.data;
                  });

                  $scope.closeDialog();
                })
                .catch(function () {
                  toastr.error('Try Late, problem in PRODUCTION', 'Error');
                });
            })
            .catch(function () {
              toastr.error('Try Late, problem in QA', 'Error');
            });
        } else {
          TranslationService.saveLocale($scope.translation, type)
            .then(function () {
              toastr.success('Save Completed in QA or PRODUCTION!');

              TranslationService.loadLocales(type).then(function (locations) {
                vm.locations = locations.data;
              });

              $scope.closeDialog();
            })
            .catch(function () {
              toastr.error('Try Late, problem in QA or PRODUCTION', 'Error');
            });
        }
      }
    }


    function cleanModel() {
      vm.translation = {
        label: '',
        description: '',
        enUS: '',
        esES: '',
        deDE: ''
      }
    }
  };
})();