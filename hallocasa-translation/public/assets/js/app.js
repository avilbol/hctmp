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
                                security_key) {
    var service = {
        loadLocales: loadLocales,
        saveLocale: saveLocale,
        deleteLocale: deleteLocale
    //   getCurrentPosition: getCurrentPosition
    };

    var resources = {
      locale: $resource(backend_url + "locales", {}, GenericRESTResource),
      saveLocale: $resource(backend_url + "locales", {} 
      )
    //   exchange: $resource(backend_url + "currency_exchange_data", {}, GenericRESTResource)
    };

    return service;

    function loadLocales() {
      return resources.locale.query().$promise;
    }

    function saveLocale(item){
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
        url: backend_url + "locales/",
        headers: {
          'Content-Type':'application/json',
          'security-key': security_key
        },
        data: data
      }

      return $http(req);
    }

    function deleteLocale(item){

      var req = {
        method: 'DELETE',
        url: backend_url + "locales/" + item,
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

                TranslationService.deleteLocale(translation.pnemonic)
                .then(function(elem){
                    // vm.locations = locations;
                    toastr.success('Delete Completed!');

                    TranslationService.loadLocales().then(function(locations){
                        vm.locations = locations;
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
                    edit: editVal
                }
            });
        }

        // Controller to Modal
        function DialogController($scope, $mdDialog, elem, edit, TranslationService,toastr) {
            $scope.translation = elem;
            $scope.edit = edit;


            $scope.closeDialog = function closeDialog() {
                console.log('Cerrar Dialogo');
                $mdDialog.cancel();
            };

            $scope.saveTranslation = function saveTranslation() {
                console.log('Information SAVING', $scope.translation);

                TranslationService.saveLocale($scope.translation)
                .then(function(locations){
                    toastr.success('Save Completed!');

                    TranslationService.loadLocales().then(function(locations){
                        vm.locations = locations;
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
//# sourceMappingURL=data:application/json;charset=utf8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImFuZ3VsYXIvYXBwLmpzIiwiYW5ndWxhci9lbnZpcm9tZW50LmNvbnN0YW50cy5qcyIsImFuZ3VsYXIvaW5kZXguY29uc3RhbnRzLmpzIiwiYW5ndWxhci9zZXJ2aWNlcy90cmFuc2xhdGlvbi5zZXJ2aWNlLmpzIiwiYW5ndWxhci9jb250cm9sbGVycy9tYW5hZ2VUcmFuc2xhdGlvbnNDb250cm9sbGVyLmpzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQ2pFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUNMQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FDaEJBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQ3pGQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0EiLCJmaWxlIjoiYXBwLmpzIiwic291cmNlc0NvbnRlbnQiOlsiKGZ1bmN0aW9uICgpIHtcbiAgICAndXNlIHN0cmljdCc7XG5cbiAgICB2YXIgYXBwID0gYW5ndWxhci5tb2R1bGUoXCJIYWxsb0Nhc2FBZG1pblwiLCBbXG4gICAgICAgICduZ1JvdXRlJyxcbiAgICAgICAgJ25nTWF0ZXJpYWwnLFxuICAgICAgICAnTWFuYWdlVHJhbnNsYXRpb25zQ3RybCcsXG4gICAgICAgICduZ1Nhbml0aXplJyxcbiAgICAgICAgJ25nUmVzb3VyY2UnLFxuICAgICAgICAnbWREYXRhVGFibGUnLFxuICAgICAgICAnbmdBbmltYXRlJyxcbiAgICAgICAgJ3RvYXN0cicsXG4gICAgICAgIC8vICd1bmRlcnNjb3JlJyxcbiAgICAgICAgLy9BcHAgbW9kdWxlc1xuICAgICAgICAnSGFsbG9DYXNhQWRtaW4uZW52aXJvbm1lbnQuY29uc3RhbnRzJ1xuICAgIF0pO1xuXG4gICAgXG4gICAgYXBwXG4gICAgICAgIC5jb250cm9sbGVyKFwiTWFpbkNvbnRyb2xsZXJcIiwgZnVuY3Rpb24oJHNjb3BlLCAkcm91dGUsICRyb3V0ZVBhcmFtcywgJGxvY2F0aW9uKSB7XG4gICAgICAgICAgICAkc2NvcGUuJHJvdXRlID0gJHJvdXRlO1xuICAgICAgICAgICAgJHNjb3BlLiRsb2NhdGlvbiA9ICRsb2NhdGlvbjtcbiAgICAgICAgICAgICRzY29wZS4kcm91dGVQYXJhbXMgPSAkcm91dGVQYXJhbXM7XG4gICAgICAgICAgICBcbiAgICAgICAgICAgIC8vICRzY29wZS4kb24oJ2NmcExvYWRpbmdCYXI6Y29tcGxldGVkJywgZnVuY3Rpb24oZXZlbnQsIGRhdGEpIHtcbiAgICAgICAgICAgIC8vICAgICBhbmd1bGFyLmVsZW1lbnQoXCIuYW5pbWF0ZWRcIikuYWRkQ2xhc3MoXCJmYWRlSW5cIik7XG4gICAgICAgICAgICAvLyB9KTtcbiAgICAgICAgICAgIC8vICRzY29wZS5nb1RvID0gZnVuY3Rpb24odXJsLCBjbG9zZU1lbnUpIHtcbiAgICAgICAgICAgIC8vICAgICAkbG9jYXRpb24udXJsKHVybCk7XG4gICAgICAgICAgICAvLyAgICAgaWYoY2xvc2VNZW51KXtcbiAgICAgICAgICAgIC8vICAgICAgICAgdG9nZ2xlTWVudSgpO1xuICAgICAgICAgICAgLy8gICAgIH1cbiAgICAgICAgICAgIC8vIH1cbiAgICAgICAgfSlcbiAgICAgICAgLmZhY3RvcnkoJ18nLCBbJyR3aW5kb3cnLFxuICAgICAgICAgICAgICAgICAgICAgICAgZnVuY3Rpb24oJHdpbmRvdykge1xuICAgICAgICAgICAgICAgICAgICAgICAgICAgIC8vIHBsYWNlIGxvZGFzaCBpbmNsdWRlIGJlZm9yZSBhbmd1bGFyXG4gICAgICAgICAgICAgICAgICAgICAgICAgICAgcmV0dXJuICR3aW5kb3cuXztcbiAgICAgICAgICAgICAgICAgICAgICAgIH1cbiAgICAgICAgXSlcbiAgICAgICAgLmNvbmZpZyhmdW5jdGlvbigkcm91dGVQcm92aWRlciwgJGxvY2F0aW9uUHJvdmlkZXIsIHRvYXN0ckNvbmZpZykge1xuICAgICAgICAgICAgJHJvdXRlUHJvdmlkZXJcbiAgICAgICAgICAgICAgICAud2hlbignLycsIHtcbiAgICAgICAgICAgICAgICAgICAgLy8gdXJsOiAnL21hbmFnZS10cmFuc2xhdGlvbnMnLFxuICAgICAgICAgICAgICAgICAgICB0ZW1wbGF0ZVVybDogJy4uLy4uL21hbmFnZVRyYW5zbGF0aW9ucy5odG1sJyxcbiAgICAgICAgICAgICAgICAgICAgY29udHJvbGxlcjogJ01hbmFnZVRyYW5zbGF0aW9uc0NvbnRyb2xsZXInLFxuICAgICAgICAgICAgICAgICAgICBjb250cm9sbGVyQXM6ICd2bSdcbiAgICAgICAgICAgICAgICB9KTtcblxuICAgICAgICAgICAgLy8gU2V0IG9wdGlvbnMgdGhpcmQtcGFydHkgbGliXG4gICAgICAgICAgICB0b2FzdHJDb25maWcuYWxsb3dIdG1sID0gdHJ1ZTtcbiAgICAgICAgICAgIHRvYXN0ckNvbmZpZy50aW1lT3V0ID0gNzAwMDtcbiAgICAgICAgICAgIHRvYXN0ckNvbmZpZy5wb3NpdGlvbkNsYXNzID0gJ3RvYXN0LWJvdHRvbS1jZW50ZXInO1xuICAgICAgICAgICAgdG9hc3RyQ29uZmlnLnByb2dyZXNzQmFyID0gdHJ1ZTsgICBcbiAgICAgICAgIFxuXG4gICAgICAgICAgICAvLyAkbG9jYXRpb25Qcm92aWRlci5oYXNoUHJlZml4KCcnKTtcbiAgICAgICAgICAgICRsb2NhdGlvblByb3ZpZGVyLmh0bWw1TW9kZSh0cnVlKTtcbiAgICAgICAgfSlcbiAgICAgICAgLy8gLmZpbHRlcigndW5zYWZlJywgZnVuY3Rpb24oJHNjZSkge1xuICAgICAgICAvLyAgICAgcmV0dXJuIGZ1bmN0aW9uKHZhbHVlKSB7XG4gICAgICAgIC8vICAgICAgICAgaWYgKCF2YWx1ZSkgeyByZXR1cm4gJyc7IH1cbiAgICAgICAgLy8gICAgICAgICByZXR1cm4gJHNjZS50cnVzdEFzSHRtbCh2YWx1ZSk7XG4gICAgICAgIC8vICAgICB9O1xuICAgICAgICAvLyB9KTtcbn0pKCk7IiwiYW5ndWxhci5tb2R1bGUoXCJIYWxsb0Nhc2FBZG1pbi5lbnZpcm9ubWVudC5jb25zdGFudHNcIiwgW10pXG4uY29uc3RhbnQoXCJiYWNrZW5kX3VybFwiLCBcImh0dHA6Ly93d3cuaGFsbG9jYXNhLmNvbTo2NDY0Ny9oYWxsb2Nhc2EtYXBpL1wiKVxuLmNvbnN0YW50KFwidXNlcl9pbWFnZXNfdXJsXCIsIFwiaHR0cDovL3d3dy5oYWxsb2Nhc2EuY29tOjY0NjQ1L3Jlc291cmNlcy9pbWFnZXMvdXNlcnMvXCIpXG4uY29uc3RhbnQoXCJwcm9wZXJ0eV9pbWFnZXNfdXJsXCIsIFwiaHR0cDovL3d3dy5oYWxsb2Nhc2EuY29tOjY0NjQ1L3Jlc291cmNlcy9pbWFnZXMvcHJvcGVydGllcy9cIilcbi5jb25zdGFudChcInNlY3VyaXR5X2tleVwiLCBcIjRoNGg0JmdmaGdrNmVkZHk1JWdqaGRra2Q2bHN1XCIpO1xuIiwiLyogZ2xvYmFsIG1vbWVudDpmYWxzZSAqL1xuKGZ1bmN0aW9uKCkge1xuICAndXNlIHN0cmljdCc7XG5cbiAgYW5ndWxhclxuICAgIC5tb2R1bGUoJ0hhbGxvQ2FzYUFkbWluLmVudmlyb25tZW50LmNvbnN0YW50cycpXG4gICAgLmNvbnN0YW50KCdHZW5lcmljUkVTVFJlc291cmNlJywge1xuICAgICAgICBxdWVyeTogeyBtZXRob2Q6ICdHRVQnLCBpc0FycmF5OiB0cnVlfSxcbiAgICAgICAgY3JlYXRlOiB7IG1ldGhvZDogJ1BPU1QnfSxcbiAgICAgICAgY29uc3VsdDogeyBtZXRob2Q6ICdQT1NUJywgaXNBcnJheTogdHJ1ZX0sXG4gICAgICAgIGNvbnN1bHRPYmo6IHsgbWV0aG9kOiAnUE9TVCd9LFxuICAgICAgICBzaG93OiB7IG1ldGhvZDogJ0dFVCd9LFxuICAgICAgICB1cGRhdGU6IHsgbWV0aG9kOiAnUFVUJywgcGFyYW1zOiB7aWQ6ICdAaWQnfX0sXG4gICAgICAgIGRlbGV0ZTogeyBtZXRob2Q6ICdERUxFVEUnLCBwYXJhbXM6IHtpZDogJ0BpZCd9fVxuICAgIH0pXG5cbn0pKCk7ICAgICIsIihmdW5jdGlvbigpIHtcbiAgJ3VzZSBzdHJpY3QnO1xuXG4gIGFuZ3VsYXJcbiAgICAubW9kdWxlKCdIYWxsb0Nhc2FBZG1pbicpXG4gICAgLnNlcnZpY2UoJ1RyYW5zbGF0aW9uU2VydmljZScsIFRyYW5zbGF0aW9uU2VydmljZSApO1xuXG4gIC8qKiBAbmdJbmplY3QgKi9cbiAgZnVuY3Rpb24gVHJhbnNsYXRpb25TZXJ2aWNlICgkcSwgXG4gICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICRyZXNvdXJjZSwgXG4gICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICRodHRwLFxuICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICBHZW5lcmljUkVTVFJlc291cmNlLCBcbiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgYmFja2VuZF91cmwsIFxuICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICBzZWN1cml0eV9rZXkpIHtcbiAgICB2YXIgc2VydmljZSA9IHtcbiAgICAgICAgbG9hZExvY2FsZXM6IGxvYWRMb2NhbGVzLFxuICAgICAgICBzYXZlTG9jYWxlOiBzYXZlTG9jYWxlLFxuICAgICAgICBkZWxldGVMb2NhbGU6IGRlbGV0ZUxvY2FsZVxuICAgIC8vICAgZ2V0Q3VycmVudFBvc2l0aW9uOiBnZXRDdXJyZW50UG9zaXRpb25cbiAgICB9O1xuXG4gICAgdmFyIHJlc291cmNlcyA9IHtcbiAgICAgIGxvY2FsZTogJHJlc291cmNlKGJhY2tlbmRfdXJsICsgXCJsb2NhbGVzXCIsIHt9LCBHZW5lcmljUkVTVFJlc291cmNlKSxcbiAgICAgIHNhdmVMb2NhbGU6ICRyZXNvdXJjZShiYWNrZW5kX3VybCArIFwibG9jYWxlc1wiLCB7fSBcbiAgICAgIClcbiAgICAvLyAgIGV4Y2hhbmdlOiAkcmVzb3VyY2UoYmFja2VuZF91cmwgKyBcImN1cnJlbmN5X2V4Y2hhbmdlX2RhdGFcIiwge30sIEdlbmVyaWNSRVNUUmVzb3VyY2UpXG4gICAgfTtcblxuICAgIHJldHVybiBzZXJ2aWNlO1xuXG4gICAgZnVuY3Rpb24gbG9hZExvY2FsZXMoKSB7XG4gICAgICByZXR1cm4gcmVzb3VyY2VzLmxvY2FsZS5xdWVyeSgpLiRwcm9taXNlO1xuICAgIH1cblxuICAgIGZ1bmN0aW9uIHNhdmVMb2NhbGUoaXRlbSl7XG4gICAgICB2YXIgZGF0YSA9IFtcbiAgICAgICAgeyBcbiAgICAgICAgICBcInBuZW1vbmljXCI6IGl0ZW0ucG5lbW9uaWMsXG4gICAgICAgICAgXCJkZXNjcmlwdGlvblwiOiBpdGVtLmRlc2NyaXB0aW9uLFxuICAgICAgICAgIFwiZW5VU1wiOiBpdGVtLmVuVVMsXG4gICAgICAgICAgXCJlc0VTXCI6IGl0ZW0uZXNFUyxcbiAgICAgICAgICBcImRlREVcIjogaXRlbS5kZURFXG4gICAgICAgIH1cbiAgICAgIF1cblxuICAgICAgdmFyIHJlcSA9IHtcbiAgICAgICAgbWV0aG9kOiAnUE9TVCcsXG4gICAgICAgIHVybDogYmFja2VuZF91cmwgKyBcImxvY2FsZXMvXCIsXG4gICAgICAgIGhlYWRlcnM6IHtcbiAgICAgICAgICAnQ29udGVudC1UeXBlJzonYXBwbGljYXRpb24vanNvbicsXG4gICAgICAgICAgJ3NlY3VyaXR5LWtleSc6IHNlY3VyaXR5X2tleVxuICAgICAgICB9LFxuICAgICAgICBkYXRhOiBkYXRhXG4gICAgICB9XG5cbiAgICAgIHJldHVybiAkaHR0cChyZXEpO1xuICAgIH1cblxuICAgIGZ1bmN0aW9uIGRlbGV0ZUxvY2FsZShpdGVtKXtcblxuICAgICAgdmFyIHJlcSA9IHtcbiAgICAgICAgbWV0aG9kOiAnREVMRVRFJyxcbiAgICAgICAgdXJsOiBiYWNrZW5kX3VybCArIFwibG9jYWxlcy9cIiArIGl0ZW0sXG4gICAgICAgIGhlYWRlcnM6IHtcbiAgICAgICAgICAnc2VjdXJpdHkta2V5Jzogc2VjdXJpdHlfa2V5XG4gICAgICAgIH1cbiAgICAgIH1cblxuICAgICAgcmV0dXJuICRodHRwKHJlcSk7XG4gICAgfVxuXG4gICAgLy8gZnVuY3Rpb24gZ2V0Q3VycmVudFBvc2l0aW9uKCkge1xuICAgIC8vICAgcmV0dXJuICRxKGZ1bmN0aW9uIChyZXNvbHZlLCByZWplY3QpIHtcbiAgICAvLyAgICAgaWYgKCEkd2luZG93Lm5hdmlnYXRvci5nZW9sb2NhdGlvbikge1xuICAgIC8vICAgICAgIHJlamVjdCgnR2VvbG9jYXRpb24gbm90IHN1cHBvcnRlZC4nKTtcbiAgICAvLyAgICAgfSBlbHNlIHtcbiAgICAvLyAgICAgICAkd2luZG93Lm5hdmlnYXRvci5nZW9sb2NhdGlvbi5nZXRDdXJyZW50UG9zaXRpb24oXG4gICAgLy8gICAgICAgICBmdW5jdGlvbiAocG9zaXRpb24pIHtcbiAgICAvLyAgICAgICAgICAgcmVzb2x2ZShwb3NpdGlvbik7XG4gICAgLy8gICAgICAgICB9LFxuICAgIC8vICAgICAgICAgZnVuY3Rpb24gKGVycm9yKSB7XG4gICAgLy8gICAgICAgICAgIHJlamVjdChlcnJvcik7XG4gICAgLy8gICAgICAgICB9KTtcbiAgICAvLyAgICAgfVxuICAgIC8vICAgfSk7XG4gICAgLy8gfVxuXG4gIH1cblxufSkoKTsiLCIoZnVuY3Rpb24gKCkge1xuICAgICd1c2Ugc3RyaWN0JztcbiAgICB2YXIgbW9kdWxlTmFtZSA9ICdNYW5hZ2VUcmFuc2xhdGlvbnNDdHJsJztcblxuICAgIGFuZ3VsYXIubW9kdWxlKG1vZHVsZU5hbWUsIFtdKVxuICAgIC5kaXJlY3RpdmUoJ21kdEN1c3RvbUNlbGxCdXR0b24nLCBmdW5jdGlvbiAoKSB7XG4gICAgICAgICAgICByZXR1cm4ge1xuICAgICAgICAgICAgICAgIHRlbXBsYXRlOiAnPG1kLWJ1dHRvbiBjbGFzcz1cIm1kLWljb24tYnV0dG9uXCI+PG1kLWljb24gbWQtZm9udC1zZXQ9XCJtYXRlcmlhbC1pY29uc1wiPmFzc2lnbm1lbnRfdHVybmVkX2luPC9tZC1pY29uPjwvbWQtYnV0dG9uPicsXG4gICAgICAgICAgICB9O1xuICAgICAgICB9KTtcblxuICAgIHZhciBNYW5hZ2VUcmFuc2xhdGlvbnNDb250cm9sbGVyID0gZnVuY3Rpb24gKCRzY29wZSwgXG4gICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAkcm9vdFNjb3BlLCBcbiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIFRyYW5zbGF0aW9uU2VydmljZSwgXG4gICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAkbWREaWFsb2csIFxuICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgbG9kYXNoLCBcbiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIHRvYXN0cikge1xuXG4gICAgICAgIHZhciB2bSA9IHRoaXM7XG5cbiAgICAgICAgLy9Jbml0IFZhcnNcbiAgICAgICAgLy8gdm0uZW52aXJvbm1lbnQgPSAncWEnO1xuXG4gICAgICAgIHZtLmVudmlyb25tZW50cyA9IFtcbiAgICAgICAgICAgIHsgaWQ6IDEsIG5hbWU6ICdRQScgfSxcbiAgICAgICAgICAgIHsgaWQ6IDIsIG5hbWU6ICdQcm9kdWN0aW9uJyB9LFxuICAgICAgICAgICAgeyBpZDogMywgbmFtZTogJ1FBICsgUHJvZHVjdGlvbicgfVxuICAgICAgICBdO1xuICAgICAgICB2bS5lbnZpcm9ubWVudCA9IHsgaWQ6IDEsIG5hbWU6ICdRQScgfTtcblxuICAgICAgICB2bS5vcGVuTW9kYWwgPSBvcGVuTW9kYWw7XG4gICAgICAgIHZtLm5ld1RyYW5zbGF0aW9uID0gbmV3VHJhbnNsYXRpb247XG4gICAgICAgIFxuICAgICAgICAkc2NvcGUuZWRpdFRyYW5zbGF0aW9uID0gZWRpdFRyYW5zbGF0aW9uO1xuICAgICAgICAkc2NvcGUuZGVsZXRlVHJhbnNsYXRpb24gPSBkZWxldGVUcmFuc2xhdGlvbjtcblxuICAgICAgICBjbGVhbk1vZGVsKCk7XG5cbiAgICAgICAgVHJhbnNsYXRpb25TZXJ2aWNlLmxvYWRMb2NhbGVzKCkudGhlbihmdW5jdGlvbihsb2NhdGlvbnMpe1xuICAgICAgICAgICAgdm0ubG9jYXRpb25zID0gbG9jYXRpb25zO1xuICAgICAgICAgICAgLy8gY29uc29sZS5sb2coJ0xvY2F0aW9ucyAnLCB2bS5sb2NhdGlvbnMpO1xuXG4gICAgICAgICAgICAvLyB2bS5sb2NhdGlvbnMgPSBbXG4gICAgICAgICAgICAvLyAgICAge1xuICAgICAgICAgICAgLy8gICAgICAgICBkZURFOlwiS2VpbmVcIixcbiAgICAgICAgICAgIC8vICAgICAgICAgZGVzY3JpcHRpb246XCJcIixcbiAgICAgICAgICAgIC8vICAgICAgICAgZW5VUzpcIk5vbmVcIixcbiAgICAgICAgICAgIC8vICAgICAgICAgcG5lbW9uaWM6XCJoYWxsb2Nhc2EuZHJvcGRvd25vcHRpb24uaGVhdGluZ25vbmVcIlxuICAgICAgICAgICAgLy8gICAgIH0sXG4gICAgICAgICAgICAvLyAgICAge1xuICAgICAgICAgICAgLy8gICAgICAgICBkZURFOlwiS2VpbmVcIixcbiAgICAgICAgICAgIC8vICAgICAgICAgZGVzY3JpcHRpb246XCJcIixcbiAgICAgICAgICAgIC8vICAgICAgICAgZW5VUzpcIk5vbmVcIixcbiAgICAgICAgICAgIC8vICAgICAgICAgcG5lbW9uaWM6XCJoYWxsb2Nhc2EuZHJvcGRvd25vcHRpb24uaGVhdGluZ25vbmVcIlxuICAgICAgICAgICAgLy8gICAgIH0sXG4gICAgICAgICAgICAvLyAgICAge1xuICAgICAgICAgICAgLy8gICAgICAgICBkZURFOlwiU8O8a29yZWFcIixcbiAgICAgICAgICAgIC8vICAgICAgICAgZGVzY3JpcHRpb246XCJcIixcbiAgICAgICAgICAgIC8vICAgICAgICAgZW5VUzpcIktvcmVhIChTb3V0aClcIixcbiAgICAgICAgICAgIC8vICAgICAgICAgZXNFUzpcIkNvcmVhIChEZWwgU3VyKVwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBwbmVtb25pYzpcImhhbGxvY2FzYS50ZWxlcGhvbmVwcmVmaXgua29yZWFvcGVucGFyc291dGhjbG9zZXBhclwiXG4gICAgICAgICAgICAvLyAgICAgfVxuICAgICAgICAgICAgLy8gXTtcblxuICAgICAgICB9KTtcblxuICAgICAgICAkc2NvcGUuJHdhdGNoKCdzZWxlY3RlZEluZGV4JywgZnVuY3Rpb24oY3VycmVudCwgb2xkKXtcbiAgICAgICAgICAgIC8vIHByZXZpb3VzID0gc2VsZWN0ZWQ7XG4gICAgICAgICAgICAvLyB2YXIgc2VsZWN0ZWQgPSAkc2NvcGUubGVuZ3VhZ2VzW2N1cnJlbnRdO1xuXG4gICAgICAgICAgICAvLyBjb25zb2xlLmxvZygnTGVuZ3VhZ2UgU2VsZWN0ZWQgJyxzZWxlY3RlZCk7XG4gICAgICAgICAgICAvLyBjb25zb2xlLmxvZygnQ29kZSAnLCBzZWxlY3RlZC5jb2RlKTtcblxuICAgICAgICAgICAgLy8gJHNjb3BlLnRyYW5zbGF0aW9ucyA9IHRyYW5zbGF0aW9ucztcblxuICAgICAgICAgICAgLy8gY29uc29sZS5sb2coJ1RyYW5zbGF0aW9ucyAnLCB0cmFuc2xhdGlvbnMpO1xuICAgICAgICAgICAgLy8gaWYgKCBvbGQgKyAxICYmIChvbGQgIT0gY3VycmVudCkpICRsb2cuZGVidWcoJ0dvb2RieWUgJyArIHByZXZpb3VzLnRpdGxlICsgJyEnKTtcbiAgICAgICAgICAgIC8vIGlmICggY3VycmVudCArIDEgKSAgICAgICAgICAgICAgICAkbG9nLmRlYnVnKCdIZWxsbyAnICsgc2VsZWN0ZWQudGl0bGUgKyAnIScpO1xuICAgICAgICB9KTtcblxuICAgICAgICAvLyBGaW5kIGVsZW1lbnQgYnkgcG5lbW9uaWMgYW5kIG9wZW4gbW9kYWxcbiAgICAgICAgZnVuY3Rpb24gZWRpdFRyYW5zbGF0aW9uKGlkKXtcbiAgICAgICAgICAgIGNvbnNvbGUubG9nKCdTdGFydCBmaW5kJywgaWQpO1xuXG4gICAgICAgICAgICBjbGVhbk1vZGVsKCk7XG5cbiAgICAgICAgICAgIHZhciB0cmFuc2xhdGlvbiA9IF8uZmluZCh2bS5sb2NhdGlvbnMsIGZ1bmN0aW9uKGl0ZW0pe1xuICAgICAgICAgICAgICAgIHJldHVybiBpdGVtLnBuZW1vbmljID09PSBpZDtcbiAgICAgICAgICAgIH0pO1xuXG4gICAgICAgICAgICBjb25zb2xlLmxvZygnVHJhZHVjY2lvbiAnLCB0cmFuc2xhdGlvbik7XG4gICAgICAgICAgICB2bS50cmFuc2xhdGlvbiA9IHRyYW5zbGF0aW9uO1xuXG4gICAgICAgICAgICB2bS5vcGVuTW9kYWwodHJ1ZSk7XG4gICAgICAgIH07XG5cbiAgICAgICAgLy8gRmluZCBlbGVtZW50IGJ5IHBuZW1vbmljIGFuZCBkZWxldGVkIGVsZW1lbnRcbiAgICAgICAgZnVuY3Rpb24gZGVsZXRlVHJhbnNsYXRpb24oaWQpe1xuXG4gICAgICAgICAgICB2YXIgdHJhbnNsYXRpb24gPSBfLmZpbmQodm0ubG9jYXRpb25zLCBmdW5jdGlvbihpdGVtKXtcbiAgICAgICAgICAgICAgICByZXR1cm4gaXRlbS5wbmVtb25pYyA9PT0gaWQ7XG4gICAgICAgICAgICB9KTtcbiAgICAgICAgICAgIGNvbnNvbGUubG9nKCdUcmFkdWNjaW9uICcsIHRyYW5zbGF0aW9uKVxuXG4gICAgICAgICAgICB2YXIgaXNDb25maXJtRGVsZXRlID0gY29uZmlybSgnRG8geW91IHdhbnQgZGVsZXRlIHRoaXMgdHJhbnNsYXRpb24/Jyk7XG4gICAgICAgICAgICBpZiAoaXNDb25maXJtRGVsZXRlKSB7XG5cbiAgICAgICAgICAgICAgICBUcmFuc2xhdGlvblNlcnZpY2UuZGVsZXRlTG9jYWxlKHRyYW5zbGF0aW9uLnBuZW1vbmljKVxuICAgICAgICAgICAgICAgIC50aGVuKGZ1bmN0aW9uKGVsZW0pe1xuICAgICAgICAgICAgICAgICAgICAvLyB2bS5sb2NhdGlvbnMgPSBsb2NhdGlvbnM7XG4gICAgICAgICAgICAgICAgICAgIHRvYXN0ci5zdWNjZXNzKCdEZWxldGUgQ29tcGxldGVkIScpO1xuXG4gICAgICAgICAgICAgICAgICAgIFRyYW5zbGF0aW9uU2VydmljZS5sb2FkTG9jYWxlcygpLnRoZW4oZnVuY3Rpb24obG9jYXRpb25zKXtcbiAgICAgICAgICAgICAgICAgICAgICAgIHZtLmxvY2F0aW9ucyA9IGxvY2F0aW9ucztcbiAgICAgICAgICAgICAgICAgICAgfSk7XG4gICAgICAgICAgICAgICAgfSlcbiAgICAgICAgICAgICAgICAuY2F0Y2goZnVuY3Rpb24gKCkge1xuICAgICAgICAgICAgICAgICAgICB0b2FzdHIuZXJyb3IoJ1RyeSBkZWxldGUgdGhpcyBlbGVtZW50IExhdGUnLCAnRXJyb3InKTtcbiAgICAgICAgICAgICAgICB9KTtcbiAgICAgICAgICAgIH0gZWxzZSB7XG4gICAgICAgICAgICAgICAgcmV0dXJuIGZhbHNlO1xuICAgICAgICAgICAgfVxuICAgICAgICB9XG5cbiAgICAgICAgZnVuY3Rpb24gbmV3VHJhbnNsYXRpb24oKXtcbiAgICAgICAgICAgIGNsZWFuTW9kZWwoKTtcbiAgICAgICAgICAgIG9wZW5Nb2RhbChmYWxzZSk7XG4gICAgICAgIH1cblxuXG4gICAgICAgIC8vIE9wZW4gbW9kYWwgd2hlbiBsaXN0IGlzID4gbWF4SXRlbXNcbiAgICAgICAgZnVuY3Rpb24gb3Blbk1vZGFsKGVkaXRWYWwpIHtcbiAgICAgICAgICAgIC8vIHRvYXN0ci5hY3RpdmUoJ0hlbGxvIHdvcmxkIScsICdUb2FzdHIgZnVuIScpO1xuXG4gICAgICAgICAgICAkbWREaWFsb2cuc2hvdyh7XG4gICAgICAgICAgICAgICAgY29udHJvbGxlcjogRGlhbG9nQ29udHJvbGxlcixcbiAgICAgICAgICAgICAgICB0ZW1wbGF0ZVVybDogJy4uLy4uL2RpYWxvZy5uZXcubGVuZ3VhZ2VzLnRtcGwuaHRtbCcsXG4gICAgICAgICAgICAgICAgcGFyZW50OiBhbmd1bGFyLmVsZW1lbnQoZG9jdW1lbnQuYm9keSksXG4gICAgICAgICAgICAgICAgY2xpY2tPdXRzaWRlVG9DbG9zZTogdHJ1ZSxcbiAgICAgICAgICAgICAgICBmdWxsc2NyZWVuOmZhbHNlLFxuICAgICAgICAgICAgICAgIGxvY2Fsczoge1xuICAgICAgICAgICAgICAgICAgICBlbGVtOiB2bS50cmFuc2xhdGlvbixcbiAgICAgICAgICAgICAgICAgICAgZWRpdDogZWRpdFZhbFxuICAgICAgICAgICAgICAgIH1cbiAgICAgICAgICAgIH0pO1xuICAgICAgICB9XG5cbiAgICAgICAgLy8gQ29udHJvbGxlciB0byBNb2RhbFxuICAgICAgICBmdW5jdGlvbiBEaWFsb2dDb250cm9sbGVyKCRzY29wZSwgJG1kRGlhbG9nLCBlbGVtLCBlZGl0LCBUcmFuc2xhdGlvblNlcnZpY2UsdG9hc3RyKSB7XG4gICAgICAgICAgICAkc2NvcGUudHJhbnNsYXRpb24gPSBlbGVtO1xuICAgICAgICAgICAgJHNjb3BlLmVkaXQgPSBlZGl0O1xuXG5cbiAgICAgICAgICAgICRzY29wZS5jbG9zZURpYWxvZyA9IGZ1bmN0aW9uIGNsb3NlRGlhbG9nKCkge1xuICAgICAgICAgICAgICAgIGNvbnNvbGUubG9nKCdDZXJyYXIgRGlhbG9nbycpO1xuICAgICAgICAgICAgICAgICRtZERpYWxvZy5jYW5jZWwoKTtcbiAgICAgICAgICAgIH07XG5cbiAgICAgICAgICAgICRzY29wZS5zYXZlVHJhbnNsYXRpb24gPSBmdW5jdGlvbiBzYXZlVHJhbnNsYXRpb24oKSB7XG4gICAgICAgICAgICAgICAgY29uc29sZS5sb2coJ0luZm9ybWF0aW9uIFNBVklORycsICRzY29wZS50cmFuc2xhdGlvbik7XG5cbiAgICAgICAgICAgICAgICBUcmFuc2xhdGlvblNlcnZpY2Uuc2F2ZUxvY2FsZSgkc2NvcGUudHJhbnNsYXRpb24pXG4gICAgICAgICAgICAgICAgLnRoZW4oZnVuY3Rpb24obG9jYXRpb25zKXtcbiAgICAgICAgICAgICAgICAgICAgdG9hc3RyLnN1Y2Nlc3MoJ1NhdmUgQ29tcGxldGVkIScpO1xuXG4gICAgICAgICAgICAgICAgICAgIFRyYW5zbGF0aW9uU2VydmljZS5sb2FkTG9jYWxlcygpLnRoZW4oZnVuY3Rpb24obG9jYXRpb25zKXtcbiAgICAgICAgICAgICAgICAgICAgICAgIHZtLmxvY2F0aW9ucyA9IGxvY2F0aW9ucztcbiAgICAgICAgICAgICAgICAgICAgfSk7XG5cbiAgICAgICAgICAgICAgICAgICAgJHNjb3BlLmNsb3NlRGlhbG9nKCk7XG4gICAgICAgICAgICAgICAgfSlcbiAgICAgICAgICAgICAgICAuY2F0Y2goZnVuY3Rpb24gKCkge1xuICAgICAgICAgICAgICAgICAgICB0b2FzdHIuZXJyb3IoJ1RyeSBMYXRlJywgJ0Vycm9yJyk7XG4gICAgICAgICAgICAgICAgfSk7XG4gICAgICAgICAgICB9XG4gICAgICAgIH1cblxuXG4gICAgICAgIGZ1bmN0aW9uIGNsZWFuTW9kZWwoKXtcbiAgICAgICAgICAgIHZtLnRyYW5zbGF0aW9uID0ge1xuICAgICAgICAgICAgICAgIGxhYmVsOiAnJyxcbiAgICAgICAgICAgICAgICBkZXNjcmlwdGlvbjogJycsXG4gICAgICAgICAgICAgICAgZW5VUzogJycsXG4gICAgICAgICAgICAgICAgZXNFUzogJycsXG4gICAgICAgICAgICAgICAgZGVERTogJydcbiAgICAgICAgICAgIH1cbiAgICAgICAgfVxuICAgICAgICBcbiAgICB9O1xuXG4gICAgLy9EZWNsYXJvIGNvbnRyb2xsYWRvclxuICAgIGFuZ3VsYXIubW9kdWxlKG1vZHVsZU5hbWUpXG4gICAgICAgIC5jb250cm9sbGVyKCdNYW5hZ2VUcmFuc2xhdGlvbnNDb250cm9sbGVyJywgW1xuICAgICAgICAgICAgJyRzY29wZScsXG4gICAgICAgICAgICBcIiRyb290U2NvcGVcIixcbiAgICAgICAgICAgIFwiVHJhbnNsYXRpb25TZXJ2aWNlXCIsXG4gICAgICAgICAgICBcIiRtZERpYWxvZ1wiLFxuICAgICAgICAgICAgXCJfXCIsXG4gICAgICAgICAgICBcInRvYXN0clwiLFxuICAgICAgICAgICAgTWFuYWdlVHJhbnNsYXRpb25zQ29udHJvbGxlcl0pO1xufSkoKTsiXX0=
