(function () {
    'use strict';
    var moduleName = 'ManageTranslationsCtrl';

    angular.module(moduleName, [])
    .directive('mdtCustomCellButton', function () {
            return {
                template: '<md-button class="md-icon-button"><md-icon md-font-set="material-icons">assignment_turned_in</md-icon></md-button>',
            };
        });

    var ManageTranslationsController = function ($scope, 
                                                $rootScope, 
                                                TranslationService, 
                                                $mdDialog, 
                                                lodash, 
                                                toastr) {

        var vm = this;

        vm.openModal = openModal;
        vm.newTranslation = newTranslation;
        $scope.editTranslation = editTranslation;
        $scope.deleteTranslation = deleteTranslation;

        cleanModel();

        TranslationService.loadLocales().then(function(locations){
            vm.locations = locations;
            // console.log('Locations ', vm.locations);

            // vm.locations = [
            //     {
            //         deDE:"Keine",
            //         description:"",
            //         enUS:"None",
            //         pnemonic:"hallocasa.dropdownoption.heatingnone"
            //     },
            //     {
            //         deDE:"Keine",
            //         description:"",
            //         enUS:"None",
            //         pnemonic:"hallocasa.dropdownoption.heatingnone"
            //     },
            //     {
            //         deDE:"Sükorea",
            //         description:"",
            //         enUS:"Korea (South)",
            //         esES:"Corea (Del Sur)",
            //         pnemonic:"hallocasa.telephoneprefix.koreaopenparsouthclosepar"
            //     }
            // ];

        });

        $scope.$watch('selectedIndex', function(current, old){
            // previous = selected;
            // var selected = $scope.lenguages[current];

            // console.log('Lenguage Selected ',selected);
            // console.log('Code ', selected.code);

            // $scope.translations = translations;

            // console.log('Translations ', translations);
            // if ( old + 1 && (old != current)) $log.debug('Goodbye ' + previous.title + '!');
            // if ( current + 1 )                $log.debug('Hello ' + selected.title + '!');
        });

        // Find element by pnemonic and open modal
        function editTranslation(id){
            console.log('Start find', id);

            cleanModel();

            var translation = _.find(vm.locations, function(item){
                return item.pnemonic === id;
            });

            console.log('Traduccion ', translation);
            vm.translation = translation;

            vm.openModal(true);
        };

        // Find element by pnemonic and deleted element
        function deleteTranslation(id){

            var translation = _.find(vm.locations, function(item){
                return item.pnemonic === id;
            });
            console.log('Traduccion ', translation)

            var isConfirmDelete = confirm('Do you want delete this translation?');
            if (isConfirmDelete) {
                console.log('Deleted element');
            } else {
                return false;
            }
        }

        function newTranslation(){
            // toastr.info('We are open today from 10 to 22', 'Information');

            cleanModel();
            openModal(false);
        }


        // Open modal when list is > maxItems
        function openModal(editVal) {
            // toastr.active('Hello world!', 'Toastr fun!');

            $mdDialog.show({
                controller: DialogController,
                templateUrl: '../../dialog.new.lenguages.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: true,
                fullscreen:false,
                locals: {
                    elem: vm.translation,
                    edit: editVal
                }
            });
        }

        // Controller to Modal
        function DialogController($scope, $mdDialog, elem, edit, TranslationService,toastr) {
            // var vmm = this;

            $scope.translation = elem;
            // $scope.model = {
            //     edit: edit
            // };
            $scope.edit = edit;

            // vmm.edit = edit;
            // vmm.closeDialog = closeDialog;
            // vmm.saveTranslation = saveTranslation;


            $scope.closeDialog = function closeDialog() {
                console.log('Cerrar Dialogo');
                $mdDialog.cancel();
            };

            

            $scope.saveTranslation = function saveTranslation() {
                console.log('Information SAVING', $scope.translation);

                TranslationService.saveLocale($scope.translation)
                .then(function(locations){
                    // vm.locations = locations;
                    toastr.success('Save Completed!');
                    console.log('Guardo ', locations);

                    TranslationService.loadLocales().then(function(locations){
                        vm.locations = locations;
                    });

                    $scope.closeDialog();
                })
                .catch(function () {
                    console.log('Ocurrio un error al guardar')
                    toastr.error('Try Late', 'Error');
                });
            }
        }


        function cleanModel(){
            vm.translation = {
                label: '',
                description: '',
                enUS: '',
                esES: '',
                deDE: ''
            }
        }
        
    };

    //Declaro controllador
    angular.module(moduleName)
        .controller('ManageTranslationsController', [
            '$scope',
            "$rootScope",
            "TranslationService",
            "$mdDialog",
            "_",
            "toastr",
            ManageTranslationsController]);
})();