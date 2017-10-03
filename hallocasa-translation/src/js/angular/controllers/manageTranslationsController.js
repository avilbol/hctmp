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

        //Init Vars
        // vm.environment = 'qa';

        vm.environments = [
            { id: 1, name: 'QA' },
            { id: 2, name: 'Production' },
            { id: 3, name: 'QA + Production' }
        ];
        vm.environment = { id: 1, name: 'QA' };

        vm.openModal = openModal;
        vm.newTranslation = newTranslation;
        vm.changeEnviroment = changeEnviroment;
        
        $scope.editTranslation = editTranslation;
        $scope.deleteTranslation = deleteTranslation;

        cleanModel();



        TranslationService.loadLocales(1).then(function(locations){
            console.log(locations.data);

            vm.locations = locations.data;
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

        function changeEnviroment(){
            TranslationService.loadLocales(vm.environment.id).then(function(locations){
                vm.locations = locations.data;
            });
        }

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

                TranslationService.deleteLocale(translation.pnemonic, vm.environment.id)
                .then(function(elem){
                    // vm.locations = locations;
                    toastr.success('Delete Completed!');

                    TranslationService.loadLocales(vm.environment.id).then(function(locations){
                        vm.locations = locations.data;
                    });
                })
                .catch(function () {
                    toastr.error('Try delete this element Late', 'Error');
                });
            } else {
                return false;
            }
        }

        function newTranslation(){
            cleanModel();
            openModal(false);
        }


        // Open modal when list is > maxItems
        function openModal(editVal) {
            // toastr.active('Hello world!', 'Toastr fun!');

            $mdDialog.show({
                controller: DialogController,
                templateUrl: '/hc-admin/dialog.new.lenguages.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: true,
                fullscreen:false,
                locals: {
                    elem: vm.translation,
                    edit: editVal,
                    type: vm.environment.id
                }
            });
        }

        // Controller to Modal
        function DialogController($scope, $mdDialog, elem, edit, type, TranslationService,toastr) {
            $scope.translation = elem;
            $scope.edit = edit;


            $scope.closeDialog = function closeDialog() {
                console.log('Cerrar Dialogo');
                $mdDialog.cancel();
            };

            $scope.saveTranslation = function saveTranslation() {
                console.log('Information SAVING', $scope.translation);

                TranslationService.saveLocale($scope.translation, type)
                .then(function(locations){
                    toastr.success('Save Completed!');

                    TranslationService.loadLocales(type).then(function(locations){
                        console.log('Load locations modal ' + type);
                        console.log(locations.data);
                        vm.locations = locations.data;
                    });

                    $scope.closeDialog();
                })
                .catch(function () {
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