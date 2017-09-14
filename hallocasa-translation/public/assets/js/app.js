(function () {
    'use strict';

    var app = angular.module("HalloCasaAdmin", [
        'ngRoute',
        'ngMaterial',
        'ManageTranslationsCtrl',
        'ngSanitize',
        'ngResource',
        'mdDataTable',
        'ngAnimate',
        'toastr',
        // 'underscore',
        //App modules
        'HalloCasaAdmin.environment.constants'
    ]);

    
    app
        .controller("MainController", function($scope, $route, $routeParams, $location) {
            $scope.$route = $route;
            $scope.$location = $location;
            $scope.$routeParams = $routeParams;
            
            // $scope.$on('cfpLoadingBar:completed', function(event, data) {
            //     angular.element(".animated").addClass("fadeIn");
            // });
            // $scope.goTo = function(url, closeMenu) {
            //     $location.url(url);
            //     if(closeMenu){
            //         toggleMenu();
            //     }
            // }
        })
        .factory('_', ['$window',
                        function($window) {
                            // place lodash include before angular
                            return $window._;
                        }
        ])
        .config(function($routeProvider, $locationProvider, toastrConfig) {
            $routeProvider
                .when('/', {
                    // url: '/manage-translations',
                    templateUrl: '../../manageTranslations.html',
                    controller: 'ManageTranslationsController',
                    controllerAs: 'vm'
                });

            // Set options third-party lib
            toastrConfig.allowHtml = true;
            toastrConfig.timeOut = 7000;
            toastrConfig.positionClass = 'toast-bottom-center';
            toastrConfig.progressBar = true;   
         

            // $locationProvider.hashPrefix('');
            $locationProvider.html5Mode(true);
        })
        // .filter('unsafe', function($sce) {
        //     return function(value) {
        //         if (!value) { return ''; }
        //         return $sce.trustAsHtml(value);
        //     };
        // });
})();
angular.module("HalloCasaAdmin.environment.constants", [])
.constant("backend_url", "http://www.hallocasa.com:64647/hallocasa-api/")
.constant("backend_url_qa", "http://www.hallocasa.com:64647/hallocasa-api/")
.constant("backend_url_production", "http://www.hallocasa.com/hallocasa-api/")
.constant("user_images_url", "http://www.hallocasa.com:64645/resources/images/users/")
.constant("property_images_url", "http://www.hallocasa.com:64645/resources/images/properties/")
.constant("security_key", "4h4h4&gfhgk6eddy5%gjhdkkd6lsu");

/* global moment:false */
(function() {
  'use strict';

  angular
    .module('HalloCasaAdmin.environment.constants')
    .constant('GenericRESTResource', {
        query: { method: 'GET', isArray: true},
        create: { method: 'POST'},
        consult: { method: 'POST', isArray: true},
        consultObj: { method: 'POST'},
        show: { method: 'GET'},
        update: { method: 'PUT', params: {id: '@id'}},
        delete: { method: 'DELETE', params: {id: '@id'}}
    })

})();    
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
                templateUrl: '../../dialog.new.lenguages.tmpl.html',
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
(function() {
  'use strict';

  angular
    .module('HalloCasaAdmin')
    .service('TranslationService', TranslationService );

  /** @ngInject */
  function TranslationService ($q, 
                                $resource, 
                                $http,
                                GenericRESTResource, 
                                backend_url, 
                                backend_url_qa,
                                backend_url_production,
                                security_key) {
    var url = backend_url;


                                  
    var service = {
        loadLocales: loadLocales,
        saveLocale: saveLocale,
        deleteLocale: deleteLocale
    //   getCurrentPosition: getCurrentPosition
    };

    var resources = {
      locale: $resource(backend_url + "locales", {}, GenericRESTResource),
      saveLocale: $resource(backend_url + "locales", {} )
    //   exchange: $resource(backend_url + "currency_exchange_data", {}, GenericRESTResource)
    };

    return service;

    function loadLocales(type) {
      if(type == 1){
        url = backend_url_qa;
      } else {
        url = backend_url_production;
      }

      console.log('Url a usar ', url + "locales/");

      var req = {
        method: 'GET',
        url: url + "locales/",
        headers: {
          'Content-Type':'application/json',
          'security-key': security_key
        }
      }

      return $http(req);

      // return resources.locale.query().$promise;
    }

    function saveLocale(item, type){
      console.log('Save type '+ type);

      if(type == 1){
        url = backend_url_qa;
      } else {
        url = backend_url_production;
      }

      var data = [
        { 
          "pnemonic": item.pnemonic,
          "description": item.description,
          "enUS": item.enUS,
          "esES": item.esES,
          "deDE": item.deDE
        }
      ]

      var req = {
        method: 'POST',
        url: url + "locales/",
        headers: {
          'Content-Type':'application/json',
          'security-key': security_key
        },
        data: data
      }

      return $http(req);
    }

    function deleteLocale(item, type){
      if(type == 1){
        url = backend_url_qa;
      } else {
        url = backend_url_production;
      }

      var req = {
        method: 'DELETE',
        url: url + "locales/" + item,
        headers: {
          'security-key': security_key
        }
      }

      return $http(req);
    }

    // function getCurrentPosition() {
    //   return $q(function (resolve, reject) {
    //     if (!$window.navigator.geolocation) {
    //       reject('Geolocation not supported.');
    //     } else {
    //       $window.navigator.geolocation.getCurrentPosition(
    //         function (position) {
    //           resolve(position);
    //         },
    //         function (error) {
    //           reject(error);
    //         });
    //     }
    //   });
    // }

  }

})();
//# sourceMappingURL=data:application/json;charset=utf8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImFuZ3VsYXIvYXBwLmpzIiwiYW5ndWxhci9lbnZpcm9tZW50LmNvbnN0YW50cy5qcyIsImFuZ3VsYXIvaW5kZXguY29uc3RhbnRzLmpzIiwiYW5ndWxhci9jb250cm9sbGVycy9tYW5hZ2VUcmFuc2xhdGlvbnNDb250cm9sbGVyLmpzIiwiYW5ndWxhci9zZXJ2aWNlcy90cmFuc2xhdGlvbi5zZXJ2aWNlLmpzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQ2pFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FDUEE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQ2hCQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FDdE5BO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBIiwiZmlsZSI6ImFwcC5qcyIsInNvdXJjZXNDb250ZW50IjpbIihmdW5jdGlvbiAoKSB7XG4gICAgJ3VzZSBzdHJpY3QnO1xuXG4gICAgdmFyIGFwcCA9IGFuZ3VsYXIubW9kdWxlKFwiSGFsbG9DYXNhQWRtaW5cIiwgW1xuICAgICAgICAnbmdSb3V0ZScsXG4gICAgICAgICduZ01hdGVyaWFsJyxcbiAgICAgICAgJ01hbmFnZVRyYW5zbGF0aW9uc0N0cmwnLFxuICAgICAgICAnbmdTYW5pdGl6ZScsXG4gICAgICAgICduZ1Jlc291cmNlJyxcbiAgICAgICAgJ21kRGF0YVRhYmxlJyxcbiAgICAgICAgJ25nQW5pbWF0ZScsXG4gICAgICAgICd0b2FzdHInLFxuICAgICAgICAvLyAndW5kZXJzY29yZScsXG4gICAgICAgIC8vQXBwIG1vZHVsZXNcbiAgICAgICAgJ0hhbGxvQ2FzYUFkbWluLmVudmlyb25tZW50LmNvbnN0YW50cydcbiAgICBdKTtcblxuICAgIFxuICAgIGFwcFxuICAgICAgICAuY29udHJvbGxlcihcIk1haW5Db250cm9sbGVyXCIsIGZ1bmN0aW9uKCRzY29wZSwgJHJvdXRlLCAkcm91dGVQYXJhbXMsICRsb2NhdGlvbikge1xuICAgICAgICAgICAgJHNjb3BlLiRyb3V0ZSA9ICRyb3V0ZTtcbiAgICAgICAgICAgICRzY29wZS4kbG9jYXRpb24gPSAkbG9jYXRpb247XG4gICAgICAgICAgICAkc2NvcGUuJHJvdXRlUGFyYW1zID0gJHJvdXRlUGFyYW1zO1xuICAgICAgICAgICAgXG4gICAgICAgICAgICAvLyAkc2NvcGUuJG9uKCdjZnBMb2FkaW5nQmFyOmNvbXBsZXRlZCcsIGZ1bmN0aW9uKGV2ZW50LCBkYXRhKSB7XG4gICAgICAgICAgICAvLyAgICAgYW5ndWxhci5lbGVtZW50KFwiLmFuaW1hdGVkXCIpLmFkZENsYXNzKFwiZmFkZUluXCIpO1xuICAgICAgICAgICAgLy8gfSk7XG4gICAgICAgICAgICAvLyAkc2NvcGUuZ29UbyA9IGZ1bmN0aW9uKHVybCwgY2xvc2VNZW51KSB7XG4gICAgICAgICAgICAvLyAgICAgJGxvY2F0aW9uLnVybCh1cmwpO1xuICAgICAgICAgICAgLy8gICAgIGlmKGNsb3NlTWVudSl7XG4gICAgICAgICAgICAvLyAgICAgICAgIHRvZ2dsZU1lbnUoKTtcbiAgICAgICAgICAgIC8vICAgICB9XG4gICAgICAgICAgICAvLyB9XG4gICAgICAgIH0pXG4gICAgICAgIC5mYWN0b3J5KCdfJywgWyckd2luZG93JyxcbiAgICAgICAgICAgICAgICAgICAgICAgIGZ1bmN0aW9uKCR3aW5kb3cpIHtcbiAgICAgICAgICAgICAgICAgICAgICAgICAgICAvLyBwbGFjZSBsb2Rhc2ggaW5jbHVkZSBiZWZvcmUgYW5ndWxhclxuICAgICAgICAgICAgICAgICAgICAgICAgICAgIHJldHVybiAkd2luZG93Ll87XG4gICAgICAgICAgICAgICAgICAgICAgICB9XG4gICAgICAgIF0pXG4gICAgICAgIC5jb25maWcoZnVuY3Rpb24oJHJvdXRlUHJvdmlkZXIsICRsb2NhdGlvblByb3ZpZGVyLCB0b2FzdHJDb25maWcpIHtcbiAgICAgICAgICAgICRyb3V0ZVByb3ZpZGVyXG4gICAgICAgICAgICAgICAgLndoZW4oJy8nLCB7XG4gICAgICAgICAgICAgICAgICAgIC8vIHVybDogJy9tYW5hZ2UtdHJhbnNsYXRpb25zJyxcbiAgICAgICAgICAgICAgICAgICAgdGVtcGxhdGVVcmw6ICcuLi8uLi9tYW5hZ2VUcmFuc2xhdGlvbnMuaHRtbCcsXG4gICAgICAgICAgICAgICAgICAgIGNvbnRyb2xsZXI6ICdNYW5hZ2VUcmFuc2xhdGlvbnNDb250cm9sbGVyJyxcbiAgICAgICAgICAgICAgICAgICAgY29udHJvbGxlckFzOiAndm0nXG4gICAgICAgICAgICAgICAgfSk7XG5cbiAgICAgICAgICAgIC8vIFNldCBvcHRpb25zIHRoaXJkLXBhcnR5IGxpYlxuICAgICAgICAgICAgdG9hc3RyQ29uZmlnLmFsbG93SHRtbCA9IHRydWU7XG4gICAgICAgICAgICB0b2FzdHJDb25maWcudGltZU91dCA9IDcwMDA7XG4gICAgICAgICAgICB0b2FzdHJDb25maWcucG9zaXRpb25DbGFzcyA9ICd0b2FzdC1ib3R0b20tY2VudGVyJztcbiAgICAgICAgICAgIHRvYXN0ckNvbmZpZy5wcm9ncmVzc0JhciA9IHRydWU7ICAgXG4gICAgICAgICBcblxuICAgICAgICAgICAgLy8gJGxvY2F0aW9uUHJvdmlkZXIuaGFzaFByZWZpeCgnJyk7XG4gICAgICAgICAgICAkbG9jYXRpb25Qcm92aWRlci5odG1sNU1vZGUodHJ1ZSk7XG4gICAgICAgIH0pXG4gICAgICAgIC8vIC5maWx0ZXIoJ3Vuc2FmZScsIGZ1bmN0aW9uKCRzY2UpIHtcbiAgICAgICAgLy8gICAgIHJldHVybiBmdW5jdGlvbih2YWx1ZSkge1xuICAgICAgICAvLyAgICAgICAgIGlmICghdmFsdWUpIHsgcmV0dXJuICcnOyB9XG4gICAgICAgIC8vICAgICAgICAgcmV0dXJuICRzY2UudHJ1c3RBc0h0bWwodmFsdWUpO1xuICAgICAgICAvLyAgICAgfTtcbiAgICAgICAgLy8gfSk7XG59KSgpOyIsImFuZ3VsYXIubW9kdWxlKFwiSGFsbG9DYXNhQWRtaW4uZW52aXJvbm1lbnQuY29uc3RhbnRzXCIsIFtdKVxuLmNvbnN0YW50KFwiYmFja2VuZF91cmxcIiwgXCJodHRwOi8vd3d3LmhhbGxvY2FzYS5jb206NjQ2NDcvaGFsbG9jYXNhLWFwaS9cIilcbi5jb25zdGFudChcImJhY2tlbmRfdXJsX3FhXCIsIFwiaHR0cDovL3d3dy5oYWxsb2Nhc2EuY29tOjY0NjQ3L2hhbGxvY2FzYS1hcGkvXCIpXG4uY29uc3RhbnQoXCJiYWNrZW5kX3VybF9wcm9kdWN0aW9uXCIsIFwiaHR0cDovL3d3dy5oYWxsb2Nhc2EuY29tL2hhbGxvY2FzYS1hcGkvXCIpXG4uY29uc3RhbnQoXCJ1c2VyX2ltYWdlc191cmxcIiwgXCJodHRwOi8vd3d3LmhhbGxvY2FzYS5jb206NjQ2NDUvcmVzb3VyY2VzL2ltYWdlcy91c2Vycy9cIilcbi5jb25zdGFudChcInByb3BlcnR5X2ltYWdlc191cmxcIiwgXCJodHRwOi8vd3d3LmhhbGxvY2FzYS5jb206NjQ2NDUvcmVzb3VyY2VzL2ltYWdlcy9wcm9wZXJ0aWVzL1wiKVxuLmNvbnN0YW50KFwic2VjdXJpdHlfa2V5XCIsIFwiNGg0aDQmZ2ZoZ2s2ZWRkeTUlZ2poZGtrZDZsc3VcIik7XG4iLCIvKiBnbG9iYWwgbW9tZW50OmZhbHNlICovXG4oZnVuY3Rpb24oKSB7XG4gICd1c2Ugc3RyaWN0JztcblxuICBhbmd1bGFyXG4gICAgLm1vZHVsZSgnSGFsbG9DYXNhQWRtaW4uZW52aXJvbm1lbnQuY29uc3RhbnRzJylcbiAgICAuY29uc3RhbnQoJ0dlbmVyaWNSRVNUUmVzb3VyY2UnLCB7XG4gICAgICAgIHF1ZXJ5OiB7IG1ldGhvZDogJ0dFVCcsIGlzQXJyYXk6IHRydWV9LFxuICAgICAgICBjcmVhdGU6IHsgbWV0aG9kOiAnUE9TVCd9LFxuICAgICAgICBjb25zdWx0OiB7IG1ldGhvZDogJ1BPU1QnLCBpc0FycmF5OiB0cnVlfSxcbiAgICAgICAgY29uc3VsdE9iajogeyBtZXRob2Q6ICdQT1NUJ30sXG4gICAgICAgIHNob3c6IHsgbWV0aG9kOiAnR0VUJ30sXG4gICAgICAgIHVwZGF0ZTogeyBtZXRob2Q6ICdQVVQnLCBwYXJhbXM6IHtpZDogJ0BpZCd9fSxcbiAgICAgICAgZGVsZXRlOiB7IG1ldGhvZDogJ0RFTEVURScsIHBhcmFtczoge2lkOiAnQGlkJ319XG4gICAgfSlcblxufSkoKTsgICAgIiwiKGZ1bmN0aW9uICgpIHtcbiAgICAndXNlIHN0cmljdCc7XG4gICAgdmFyIG1vZHVsZU5hbWUgPSAnTWFuYWdlVHJhbnNsYXRpb25zQ3RybCc7XG5cbiAgICBhbmd1bGFyLm1vZHVsZShtb2R1bGVOYW1lLCBbXSlcbiAgICAuZGlyZWN0aXZlKCdtZHRDdXN0b21DZWxsQnV0dG9uJywgZnVuY3Rpb24gKCkge1xuICAgICAgICAgICAgcmV0dXJuIHtcbiAgICAgICAgICAgICAgICB0ZW1wbGF0ZTogJzxtZC1idXR0b24gY2xhc3M9XCJtZC1pY29uLWJ1dHRvblwiPjxtZC1pY29uIG1kLWZvbnQtc2V0PVwibWF0ZXJpYWwtaWNvbnNcIj5hc3NpZ25tZW50X3R1cm5lZF9pbjwvbWQtaWNvbj48L21kLWJ1dHRvbj4nLFxuICAgICAgICAgICAgfTtcbiAgICAgICAgfSk7XG5cbiAgICB2YXIgTWFuYWdlVHJhbnNsYXRpb25zQ29udHJvbGxlciA9IGZ1bmN0aW9uICgkc2NvcGUsIFxuICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgJHJvb3RTY29wZSwgXG4gICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICBUcmFuc2xhdGlvblNlcnZpY2UsIFxuICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgJG1kRGlhbG9nLCBcbiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIGxvZGFzaCwgXG4gICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICB0b2FzdHIpIHtcblxuICAgICAgICB2YXIgdm0gPSB0aGlzO1xuXG4gICAgICAgIC8vSW5pdCBWYXJzXG4gICAgICAgIC8vIHZtLmVudmlyb25tZW50ID0gJ3FhJztcblxuICAgICAgICB2bS5lbnZpcm9ubWVudHMgPSBbXG4gICAgICAgICAgICB7IGlkOiAxLCBuYW1lOiAnUUEnIH0sXG4gICAgICAgICAgICB7IGlkOiAyLCBuYW1lOiAnUHJvZHVjdGlvbicgfSxcbiAgICAgICAgICAgIHsgaWQ6IDMsIG5hbWU6ICdRQSArIFByb2R1Y3Rpb24nIH1cbiAgICAgICAgXTtcbiAgICAgICAgdm0uZW52aXJvbm1lbnQgPSB7IGlkOiAxLCBuYW1lOiAnUUEnIH07XG5cbiAgICAgICAgdm0ub3Blbk1vZGFsID0gb3Blbk1vZGFsO1xuICAgICAgICB2bS5uZXdUcmFuc2xhdGlvbiA9IG5ld1RyYW5zbGF0aW9uO1xuICAgICAgICB2bS5jaGFuZ2VFbnZpcm9tZW50ID0gY2hhbmdlRW52aXJvbWVudDtcbiAgICAgICAgXG4gICAgICAgICRzY29wZS5lZGl0VHJhbnNsYXRpb24gPSBlZGl0VHJhbnNsYXRpb247XG4gICAgICAgICRzY29wZS5kZWxldGVUcmFuc2xhdGlvbiA9IGRlbGV0ZVRyYW5zbGF0aW9uO1xuXG4gICAgICAgIGNsZWFuTW9kZWwoKTtcblxuXG5cbiAgICAgICAgVHJhbnNsYXRpb25TZXJ2aWNlLmxvYWRMb2NhbGVzKDEpLnRoZW4oZnVuY3Rpb24obG9jYXRpb25zKXtcbiAgICAgICAgICAgIGNvbnNvbGUubG9nKGxvY2F0aW9ucy5kYXRhKTtcblxuICAgICAgICAgICAgdm0ubG9jYXRpb25zID0gbG9jYXRpb25zLmRhdGE7XG4gICAgICAgICAgICAvLyBjb25zb2xlLmxvZygnTG9jYXRpb25zICcsIHZtLmxvY2F0aW9ucyk7XG5cbiAgICAgICAgICAgIC8vIHZtLmxvY2F0aW9ucyA9IFtcbiAgICAgICAgICAgIC8vICAgICB7XG4gICAgICAgICAgICAvLyAgICAgICAgIGRlREU6XCJLZWluZVwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBkZXNjcmlwdGlvbjpcIlwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBlblVTOlwiTm9uZVwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBwbmVtb25pYzpcImhhbGxvY2FzYS5kcm9wZG93bm9wdGlvbi5oZWF0aW5nbm9uZVwiXG4gICAgICAgICAgICAvLyAgICAgfSxcbiAgICAgICAgICAgIC8vICAgICB7XG4gICAgICAgICAgICAvLyAgICAgICAgIGRlREU6XCJLZWluZVwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBkZXNjcmlwdGlvbjpcIlwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBlblVTOlwiTm9uZVwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBwbmVtb25pYzpcImhhbGxvY2FzYS5kcm9wZG93bm9wdGlvbi5oZWF0aW5nbm9uZVwiXG4gICAgICAgICAgICAvLyAgICAgfSxcbiAgICAgICAgICAgIC8vICAgICB7XG4gICAgICAgICAgICAvLyAgICAgICAgIGRlREU6XCJTw7xrb3JlYVwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBkZXNjcmlwdGlvbjpcIlwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBlblVTOlwiS29yZWEgKFNvdXRoKVwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBlc0VTOlwiQ29yZWEgKERlbCBTdXIpXCIsXG4gICAgICAgICAgICAvLyAgICAgICAgIHBuZW1vbmljOlwiaGFsbG9jYXNhLnRlbGVwaG9uZXByZWZpeC5rb3JlYW9wZW5wYXJzb3V0aGNsb3NlcGFyXCJcbiAgICAgICAgICAgIC8vICAgICB9XG4gICAgICAgICAgICAvLyBdO1xuXG4gICAgICAgIH0pO1xuXG4gICAgICAgICRzY29wZS4kd2F0Y2goJ3NlbGVjdGVkSW5kZXgnLCBmdW5jdGlvbihjdXJyZW50LCBvbGQpe1xuICAgICAgICAgICAgLy8gcHJldmlvdXMgPSBzZWxlY3RlZDtcbiAgICAgICAgICAgIC8vIHZhciBzZWxlY3RlZCA9ICRzY29wZS5sZW5ndWFnZXNbY3VycmVudF07XG5cbiAgICAgICAgICAgIC8vIGNvbnNvbGUubG9nKCdMZW5ndWFnZSBTZWxlY3RlZCAnLHNlbGVjdGVkKTtcbiAgICAgICAgICAgIC8vIGNvbnNvbGUubG9nKCdDb2RlICcsIHNlbGVjdGVkLmNvZGUpO1xuXG4gICAgICAgICAgICAvLyAkc2NvcGUudHJhbnNsYXRpb25zID0gdHJhbnNsYXRpb25zO1xuXG4gICAgICAgICAgICAvLyBjb25zb2xlLmxvZygnVHJhbnNsYXRpb25zICcsIHRyYW5zbGF0aW9ucyk7XG4gICAgICAgICAgICAvLyBpZiAoIG9sZCArIDEgJiYgKG9sZCAhPSBjdXJyZW50KSkgJGxvZy5kZWJ1ZygnR29vZGJ5ZSAnICsgcHJldmlvdXMudGl0bGUgKyAnIScpO1xuICAgICAgICAgICAgLy8gaWYgKCBjdXJyZW50ICsgMSApICAgICAgICAgICAgICAgICRsb2cuZGVidWcoJ0hlbGxvICcgKyBzZWxlY3RlZC50aXRsZSArICchJyk7XG4gICAgICAgIH0pO1xuXG4gICAgICAgIGZ1bmN0aW9uIGNoYW5nZUVudmlyb21lbnQoKXtcbiAgICAgICAgICAgIFRyYW5zbGF0aW9uU2VydmljZS5sb2FkTG9jYWxlcyh2bS5lbnZpcm9ubWVudC5pZCkudGhlbihmdW5jdGlvbihsb2NhdGlvbnMpe1xuICAgICAgICAgICAgICAgIHZtLmxvY2F0aW9ucyA9IGxvY2F0aW9ucy5kYXRhO1xuICAgICAgICAgICAgfSk7XG4gICAgICAgIH1cblxuICAgICAgICAvLyBGaW5kIGVsZW1lbnQgYnkgcG5lbW9uaWMgYW5kIG9wZW4gbW9kYWxcbiAgICAgICAgZnVuY3Rpb24gZWRpdFRyYW5zbGF0aW9uKGlkKXtcbiAgICAgICAgICAgIGNvbnNvbGUubG9nKCdTdGFydCBmaW5kJywgaWQpO1xuXG4gICAgICAgICAgICBjbGVhbk1vZGVsKCk7XG5cbiAgICAgICAgICAgIHZhciB0cmFuc2xhdGlvbiA9IF8uZmluZCh2bS5sb2NhdGlvbnMsIGZ1bmN0aW9uKGl0ZW0pe1xuICAgICAgICAgICAgICAgIHJldHVybiBpdGVtLnBuZW1vbmljID09PSBpZDtcbiAgICAgICAgICAgIH0pO1xuXG4gICAgICAgICAgICBjb25zb2xlLmxvZygnVHJhZHVjY2lvbiAnLCB0cmFuc2xhdGlvbik7XG4gICAgICAgICAgICB2bS50cmFuc2xhdGlvbiA9IHRyYW5zbGF0aW9uO1xuXG4gICAgICAgICAgICB2bS5vcGVuTW9kYWwodHJ1ZSk7XG4gICAgICAgIH07XG5cbiAgICAgICAgLy8gRmluZCBlbGVtZW50IGJ5IHBuZW1vbmljIGFuZCBkZWxldGVkIGVsZW1lbnRcbiAgICAgICAgZnVuY3Rpb24gZGVsZXRlVHJhbnNsYXRpb24oaWQpe1xuXG4gICAgICAgICAgICB2YXIgdHJhbnNsYXRpb24gPSBfLmZpbmQodm0ubG9jYXRpb25zLCBmdW5jdGlvbihpdGVtKXtcbiAgICAgICAgICAgICAgICByZXR1cm4gaXRlbS5wbmVtb25pYyA9PT0gaWQ7XG4gICAgICAgICAgICB9KTtcbiAgICAgICAgICAgIGNvbnNvbGUubG9nKCdUcmFkdWNjaW9uICcsIHRyYW5zbGF0aW9uKVxuXG4gICAgICAgICAgICB2YXIgaXNDb25maXJtRGVsZXRlID0gY29uZmlybSgnRG8geW91IHdhbnQgZGVsZXRlIHRoaXMgdHJhbnNsYXRpb24/Jyk7XG4gICAgICAgICAgICBpZiAoaXNDb25maXJtRGVsZXRlKSB7XG5cbiAgICAgICAgICAgICAgICBUcmFuc2xhdGlvblNlcnZpY2UuZGVsZXRlTG9jYWxlKHRyYW5zbGF0aW9uLnBuZW1vbmljLCB2bS5lbnZpcm9ubWVudC5pZClcbiAgICAgICAgICAgICAgICAudGhlbihmdW5jdGlvbihlbGVtKXtcbiAgICAgICAgICAgICAgICAgICAgLy8gdm0ubG9jYXRpb25zID0gbG9jYXRpb25zO1xuICAgICAgICAgICAgICAgICAgICB0b2FzdHIuc3VjY2VzcygnRGVsZXRlIENvbXBsZXRlZCEnKTtcblxuICAgICAgICAgICAgICAgICAgICBUcmFuc2xhdGlvblNlcnZpY2UubG9hZExvY2FsZXModm0uZW52aXJvbm1lbnQuaWQpLnRoZW4oZnVuY3Rpb24obG9jYXRpb25zKXtcbiAgICAgICAgICAgICAgICAgICAgICAgIHZtLmxvY2F0aW9ucyA9IGxvY2F0aW9ucy5kYXRhO1xuICAgICAgICAgICAgICAgICAgICB9KTtcbiAgICAgICAgICAgICAgICB9KVxuICAgICAgICAgICAgICAgIC5jYXRjaChmdW5jdGlvbiAoKSB7XG4gICAgICAgICAgICAgICAgICAgIHRvYXN0ci5lcnJvcignVHJ5IGRlbGV0ZSB0aGlzIGVsZW1lbnQgTGF0ZScsICdFcnJvcicpO1xuICAgICAgICAgICAgICAgIH0pO1xuICAgICAgICAgICAgfSBlbHNlIHtcbiAgICAgICAgICAgICAgICByZXR1cm4gZmFsc2U7XG4gICAgICAgICAgICB9XG4gICAgICAgIH1cblxuICAgICAgICBmdW5jdGlvbiBuZXdUcmFuc2xhdGlvbigpe1xuICAgICAgICAgICAgY2xlYW5Nb2RlbCgpO1xuICAgICAgICAgICAgb3Blbk1vZGFsKGZhbHNlKTtcbiAgICAgICAgfVxuXG5cbiAgICAgICAgLy8gT3BlbiBtb2RhbCB3aGVuIGxpc3QgaXMgPiBtYXhJdGVtc1xuICAgICAgICBmdW5jdGlvbiBvcGVuTW9kYWwoZWRpdFZhbCkge1xuICAgICAgICAgICAgLy8gdG9hc3RyLmFjdGl2ZSgnSGVsbG8gd29ybGQhJywgJ1RvYXN0ciBmdW4hJyk7XG5cbiAgICAgICAgICAgICRtZERpYWxvZy5zaG93KHtcbiAgICAgICAgICAgICAgICBjb250cm9sbGVyOiBEaWFsb2dDb250cm9sbGVyLFxuICAgICAgICAgICAgICAgIHRlbXBsYXRlVXJsOiAnLi4vLi4vZGlhbG9nLm5ldy5sZW5ndWFnZXMudG1wbC5odG1sJyxcbiAgICAgICAgICAgICAgICBwYXJlbnQ6IGFuZ3VsYXIuZWxlbWVudChkb2N1bWVudC5ib2R5KSxcbiAgICAgICAgICAgICAgICBjbGlja091dHNpZGVUb0Nsb3NlOiB0cnVlLFxuICAgICAgICAgICAgICAgIGZ1bGxzY3JlZW46ZmFsc2UsXG4gICAgICAgICAgICAgICAgbG9jYWxzOiB7XG4gICAgICAgICAgICAgICAgICAgIGVsZW06IHZtLnRyYW5zbGF0aW9uLFxuICAgICAgICAgICAgICAgICAgICBlZGl0OiBlZGl0VmFsLFxuICAgICAgICAgICAgICAgICAgICB0eXBlOiB2bS5lbnZpcm9ubWVudC5pZFxuICAgICAgICAgICAgICAgIH1cbiAgICAgICAgICAgIH0pO1xuICAgICAgICB9XG5cbiAgICAgICAgLy8gQ29udHJvbGxlciB0byBNb2RhbFxuICAgICAgICBmdW5jdGlvbiBEaWFsb2dDb250cm9sbGVyKCRzY29wZSwgJG1kRGlhbG9nLCBlbGVtLCBlZGl0LCB0eXBlLCBUcmFuc2xhdGlvblNlcnZpY2UsdG9hc3RyKSB7XG4gICAgICAgICAgICAkc2NvcGUudHJhbnNsYXRpb24gPSBlbGVtO1xuICAgICAgICAgICAgJHNjb3BlLmVkaXQgPSBlZGl0O1xuXG5cbiAgICAgICAgICAgICRzY29wZS5jbG9zZURpYWxvZyA9IGZ1bmN0aW9uIGNsb3NlRGlhbG9nKCkge1xuICAgICAgICAgICAgICAgIGNvbnNvbGUubG9nKCdDZXJyYXIgRGlhbG9nbycpO1xuICAgICAgICAgICAgICAgICRtZERpYWxvZy5jYW5jZWwoKTtcbiAgICAgICAgICAgIH07XG5cbiAgICAgICAgICAgICRzY29wZS5zYXZlVHJhbnNsYXRpb24gPSBmdW5jdGlvbiBzYXZlVHJhbnNsYXRpb24oKSB7XG4gICAgICAgICAgICAgICAgY29uc29sZS5sb2coJ0luZm9ybWF0aW9uIFNBVklORycsICRzY29wZS50cmFuc2xhdGlvbik7XG5cbiAgICAgICAgICAgICAgICBUcmFuc2xhdGlvblNlcnZpY2Uuc2F2ZUxvY2FsZSgkc2NvcGUudHJhbnNsYXRpb24sIHR5cGUpXG4gICAgICAgICAgICAgICAgLnRoZW4oZnVuY3Rpb24obG9jYXRpb25zKXtcbiAgICAgICAgICAgICAgICAgICAgdG9hc3RyLnN1Y2Nlc3MoJ1NhdmUgQ29tcGxldGVkIScpO1xuXG4gICAgICAgICAgICAgICAgICAgIFRyYW5zbGF0aW9uU2VydmljZS5sb2FkTG9jYWxlcyh0eXBlKS50aGVuKGZ1bmN0aW9uKGxvY2F0aW9ucyl7XG4gICAgICAgICAgICAgICAgICAgICAgICBjb25zb2xlLmxvZygnTG9hZCBsb2NhdGlvbnMgbW9kYWwgJyArIHR5cGUpO1xuICAgICAgICAgICAgICAgICAgICAgICAgY29uc29sZS5sb2cobG9jYXRpb25zLmRhdGEpO1xuICAgICAgICAgICAgICAgICAgICAgICAgdm0ubG9jYXRpb25zID0gbG9jYXRpb25zLmRhdGE7XG4gICAgICAgICAgICAgICAgICAgIH0pO1xuXG4gICAgICAgICAgICAgICAgICAgICRzY29wZS5jbG9zZURpYWxvZygpO1xuICAgICAgICAgICAgICAgIH0pXG4gICAgICAgICAgICAgICAgLmNhdGNoKGZ1bmN0aW9uICgpIHtcbiAgICAgICAgICAgICAgICAgICAgdG9hc3RyLmVycm9yKCdUcnkgTGF0ZScsICdFcnJvcicpO1xuICAgICAgICAgICAgICAgIH0pO1xuICAgICAgICAgICAgfVxuICAgICAgICB9XG5cblxuICAgICAgICBmdW5jdGlvbiBjbGVhbk1vZGVsKCl7XG4gICAgICAgICAgICB2bS50cmFuc2xhdGlvbiA9IHtcbiAgICAgICAgICAgICAgICBsYWJlbDogJycsXG4gICAgICAgICAgICAgICAgZGVzY3JpcHRpb246ICcnLFxuICAgICAgICAgICAgICAgIGVuVVM6ICcnLFxuICAgICAgICAgICAgICAgIGVzRVM6ICcnLFxuICAgICAgICAgICAgICAgIGRlREU6ICcnXG4gICAgICAgICAgICB9XG4gICAgICAgIH1cbiAgICAgICAgXG4gICAgfTtcblxuICAgIC8vRGVjbGFybyBjb250cm9sbGFkb3JcbiAgICBhbmd1bGFyLm1vZHVsZShtb2R1bGVOYW1lKVxuICAgICAgICAuY29udHJvbGxlcignTWFuYWdlVHJhbnNsYXRpb25zQ29udHJvbGxlcicsIFtcbiAgICAgICAgICAgICckc2NvcGUnLFxuICAgICAgICAgICAgXCIkcm9vdFNjb3BlXCIsXG4gICAgICAgICAgICBcIlRyYW5zbGF0aW9uU2VydmljZVwiLFxuICAgICAgICAgICAgXCIkbWREaWFsb2dcIixcbiAgICAgICAgICAgIFwiX1wiLFxuICAgICAgICAgICAgXCJ0b2FzdHJcIixcbiAgICAgICAgICAgIE1hbmFnZVRyYW5zbGF0aW9uc0NvbnRyb2xsZXJdKTtcbn0pKCk7IiwiKGZ1bmN0aW9uKCkge1xuICAndXNlIHN0cmljdCc7XG5cbiAgYW5ndWxhclxuICAgIC5tb2R1bGUoJ0hhbGxvQ2FzYUFkbWluJylcbiAgICAuc2VydmljZSgnVHJhbnNsYXRpb25TZXJ2aWNlJywgVHJhbnNsYXRpb25TZXJ2aWNlICk7XG5cbiAgLyoqIEBuZ0luamVjdCAqL1xuICBmdW5jdGlvbiBUcmFuc2xhdGlvblNlcnZpY2UgKCRxLCBcbiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgJHJlc291cmNlLCBcbiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgJGh0dHAsXG4gICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIEdlbmVyaWNSRVNUUmVzb3VyY2UsIFxuICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICBiYWNrZW5kX3VybCwgXG4gICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIGJhY2tlbmRfdXJsX3FhLFxuICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICBiYWNrZW5kX3VybF9wcm9kdWN0aW9uLFxuICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICBzZWN1cml0eV9rZXkpIHtcbiAgICB2YXIgdXJsID0gYmFja2VuZF91cmw7XG5cblxuICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIFxuICAgIHZhciBzZXJ2aWNlID0ge1xuICAgICAgICBsb2FkTG9jYWxlczogbG9hZExvY2FsZXMsXG4gICAgICAgIHNhdmVMb2NhbGU6IHNhdmVMb2NhbGUsXG4gICAgICAgIGRlbGV0ZUxvY2FsZTogZGVsZXRlTG9jYWxlXG4gICAgLy8gICBnZXRDdXJyZW50UG9zaXRpb246IGdldEN1cnJlbnRQb3NpdGlvblxuICAgIH07XG5cbiAgICB2YXIgcmVzb3VyY2VzID0ge1xuICAgICAgbG9jYWxlOiAkcmVzb3VyY2UoYmFja2VuZF91cmwgKyBcImxvY2FsZXNcIiwge30sIEdlbmVyaWNSRVNUUmVzb3VyY2UpLFxuICAgICAgc2F2ZUxvY2FsZTogJHJlc291cmNlKGJhY2tlbmRfdXJsICsgXCJsb2NhbGVzXCIsIHt9IClcbiAgICAvLyAgIGV4Y2hhbmdlOiAkcmVzb3VyY2UoYmFja2VuZF91cmwgKyBcImN1cnJlbmN5X2V4Y2hhbmdlX2RhdGFcIiwge30sIEdlbmVyaWNSRVNUUmVzb3VyY2UpXG4gICAgfTtcblxuICAgIHJldHVybiBzZXJ2aWNlO1xuXG4gICAgZnVuY3Rpb24gbG9hZExvY2FsZXModHlwZSkge1xuICAgICAgaWYodHlwZSA9PSAxKXtcbiAgICAgICAgdXJsID0gYmFja2VuZF91cmxfcWE7XG4gICAgICB9IGVsc2Uge1xuICAgICAgICB1cmwgPSBiYWNrZW5kX3VybF9wcm9kdWN0aW9uO1xuICAgICAgfVxuXG4gICAgICBjb25zb2xlLmxvZygnVXJsIGEgdXNhciAnLCB1cmwgKyBcImxvY2FsZXMvXCIpO1xuXG4gICAgICB2YXIgcmVxID0ge1xuICAgICAgICBtZXRob2Q6ICdHRVQnLFxuICAgICAgICB1cmw6IHVybCArIFwibG9jYWxlcy9cIixcbiAgICAgICAgaGVhZGVyczoge1xuICAgICAgICAgICdDb250ZW50LVR5cGUnOidhcHBsaWNhdGlvbi9qc29uJyxcbiAgICAgICAgICAnc2VjdXJpdHkta2V5Jzogc2VjdXJpdHlfa2V5XG4gICAgICAgIH1cbiAgICAgIH1cblxuICAgICAgcmV0dXJuICRodHRwKHJlcSk7XG5cbiAgICAgIC8vIHJldHVybiByZXNvdXJjZXMubG9jYWxlLnF1ZXJ5KCkuJHByb21pc2U7XG4gICAgfVxuXG4gICAgZnVuY3Rpb24gc2F2ZUxvY2FsZShpdGVtLCB0eXBlKXtcbiAgICAgIGNvbnNvbGUubG9nKCdTYXZlIHR5cGUgJysgdHlwZSk7XG5cbiAgICAgIGlmKHR5cGUgPT0gMSl7XG4gICAgICAgIHVybCA9IGJhY2tlbmRfdXJsX3FhO1xuICAgICAgfSBlbHNlIHtcbiAgICAgICAgdXJsID0gYmFja2VuZF91cmxfcHJvZHVjdGlvbjtcbiAgICAgIH1cblxuICAgICAgdmFyIGRhdGEgPSBbXG4gICAgICAgIHsgXG4gICAgICAgICAgXCJwbmVtb25pY1wiOiBpdGVtLnBuZW1vbmljLFxuICAgICAgICAgIFwiZGVzY3JpcHRpb25cIjogaXRlbS5kZXNjcmlwdGlvbixcbiAgICAgICAgICBcImVuVVNcIjogaXRlbS5lblVTLFxuICAgICAgICAgIFwiZXNFU1wiOiBpdGVtLmVzRVMsXG4gICAgICAgICAgXCJkZURFXCI6IGl0ZW0uZGVERVxuICAgICAgICB9XG4gICAgICBdXG5cbiAgICAgIHZhciByZXEgPSB7XG4gICAgICAgIG1ldGhvZDogJ1BPU1QnLFxuICAgICAgICB1cmw6IHVybCArIFwibG9jYWxlcy9cIixcbiAgICAgICAgaGVhZGVyczoge1xuICAgICAgICAgICdDb250ZW50LVR5cGUnOidhcHBsaWNhdGlvbi9qc29uJyxcbiAgICAgICAgICAnc2VjdXJpdHkta2V5Jzogc2VjdXJpdHlfa2V5XG4gICAgICAgIH0sXG4gICAgICAgIGRhdGE6IGRhdGFcbiAgICAgIH1cblxuICAgICAgcmV0dXJuICRodHRwKHJlcSk7XG4gICAgfVxuXG4gICAgZnVuY3Rpb24gZGVsZXRlTG9jYWxlKGl0ZW0sIHR5cGUpe1xuICAgICAgaWYodHlwZSA9PSAxKXtcbiAgICAgICAgdXJsID0gYmFja2VuZF91cmxfcWE7XG4gICAgICB9IGVsc2Uge1xuICAgICAgICB1cmwgPSBiYWNrZW5kX3VybF9wcm9kdWN0aW9uO1xuICAgICAgfVxuXG4gICAgICB2YXIgcmVxID0ge1xuICAgICAgICBtZXRob2Q6ICdERUxFVEUnLFxuICAgICAgICB1cmw6IHVybCArIFwibG9jYWxlcy9cIiArIGl0ZW0sXG4gICAgICAgIGhlYWRlcnM6IHtcbiAgICAgICAgICAnc2VjdXJpdHkta2V5Jzogc2VjdXJpdHlfa2V5XG4gICAgICAgIH1cbiAgICAgIH1cblxuICAgICAgcmV0dXJuICRodHRwKHJlcSk7XG4gICAgfVxuXG4gICAgLy8gZnVuY3Rpb24gZ2V0Q3VycmVudFBvc2l0aW9uKCkge1xuICAgIC8vICAgcmV0dXJuICRxKGZ1bmN0aW9uIChyZXNvbHZlLCByZWplY3QpIHtcbiAgICAvLyAgICAgaWYgKCEkd2luZG93Lm5hdmlnYXRvci5nZW9sb2NhdGlvbikge1xuICAgIC8vICAgICAgIHJlamVjdCgnR2VvbG9jYXRpb24gbm90IHN1cHBvcnRlZC4nKTtcbiAgICAvLyAgICAgfSBlbHNlIHtcbiAgICAvLyAgICAgICAkd2luZG93Lm5hdmlnYXRvci5nZW9sb2NhdGlvbi5nZXRDdXJyZW50UG9zaXRpb24oXG4gICAgLy8gICAgICAgICBmdW5jdGlvbiAocG9zaXRpb24pIHtcbiAgICAvLyAgICAgICAgICAgcmVzb2x2ZShwb3NpdGlvbik7XG4gICAgLy8gICAgICAgICB9LFxuICAgIC8vICAgICAgICAgZnVuY3Rpb24gKGVycm9yKSB7XG4gICAgLy8gICAgICAgICAgIHJlamVjdChlcnJvcik7XG4gICAgLy8gICAgICAgICB9KTtcbiAgICAvLyAgICAgfVxuICAgIC8vICAgfSk7XG4gICAgLy8gfVxuXG4gIH1cblxufSkoKTsiXX0=
