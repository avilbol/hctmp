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
//# sourceMappingURL=data:application/json;charset=utf8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImFuZ3VsYXIvYXBwLmpzIiwiYW5ndWxhci9lbnZpcm9tZW50LmNvbnN0YW50cy5qcyIsImFuZ3VsYXIvaW5kZXguY29uc3RhbnRzLmpzIiwiYW5ndWxhci9jb250cm9sbGVycy9tYW5hZ2VUcmFuc2xhdGlvbnNDb250cm9sbGVyLmpzIiwiYW5ndWxhci9zZXJ2aWNlcy90cmFuc2xhdGlvbi5zZXJ2aWNlLmpzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQ2pFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUNMQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FDaEJBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQ3hNQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0EiLCJmaWxlIjoiYXBwLmpzIiwic291cmNlc0NvbnRlbnQiOlsiKGZ1bmN0aW9uICgpIHtcbiAgICAndXNlIHN0cmljdCc7XG5cbiAgICB2YXIgYXBwID0gYW5ndWxhci5tb2R1bGUoXCJIYWxsb0Nhc2FBZG1pblwiLCBbXG4gICAgICAgICduZ1JvdXRlJyxcbiAgICAgICAgJ25nTWF0ZXJpYWwnLFxuICAgICAgICAnTWFuYWdlVHJhbnNsYXRpb25zQ3RybCcsXG4gICAgICAgICduZ1Nhbml0aXplJyxcbiAgICAgICAgJ25nUmVzb3VyY2UnLFxuICAgICAgICAnbWREYXRhVGFibGUnLFxuICAgICAgICAnbmdBbmltYXRlJyxcbiAgICAgICAgJ3RvYXN0cicsXG4gICAgICAgIC8vICd1bmRlcnNjb3JlJyxcbiAgICAgICAgLy9BcHAgbW9kdWxlc1xuICAgICAgICAnSGFsbG9DYXNhQWRtaW4uZW52aXJvbm1lbnQuY29uc3RhbnRzJ1xuICAgIF0pO1xuXG4gICAgXG4gICAgYXBwXG4gICAgICAgIC5jb250cm9sbGVyKFwiTWFpbkNvbnRyb2xsZXJcIiwgZnVuY3Rpb24oJHNjb3BlLCAkcm91dGUsICRyb3V0ZVBhcmFtcywgJGxvY2F0aW9uKSB7XG4gICAgICAgICAgICAkc2NvcGUuJHJvdXRlID0gJHJvdXRlO1xuICAgICAgICAgICAgJHNjb3BlLiRsb2NhdGlvbiA9ICRsb2NhdGlvbjtcbiAgICAgICAgICAgICRzY29wZS4kcm91dGVQYXJhbXMgPSAkcm91dGVQYXJhbXM7XG4gICAgICAgICAgICBcbiAgICAgICAgICAgIC8vICRzY29wZS4kb24oJ2NmcExvYWRpbmdCYXI6Y29tcGxldGVkJywgZnVuY3Rpb24oZXZlbnQsIGRhdGEpIHtcbiAgICAgICAgICAgIC8vICAgICBhbmd1bGFyLmVsZW1lbnQoXCIuYW5pbWF0ZWRcIikuYWRkQ2xhc3MoXCJmYWRlSW5cIik7XG4gICAgICAgICAgICAvLyB9KTtcbiAgICAgICAgICAgIC8vICRzY29wZS5nb1RvID0gZnVuY3Rpb24odXJsLCBjbG9zZU1lbnUpIHtcbiAgICAgICAgICAgIC8vICAgICAkbG9jYXRpb24udXJsKHVybCk7XG4gICAgICAgICAgICAvLyAgICAgaWYoY2xvc2VNZW51KXtcbiAgICAgICAgICAgIC8vICAgICAgICAgdG9nZ2xlTWVudSgpO1xuICAgICAgICAgICAgLy8gICAgIH1cbiAgICAgICAgICAgIC8vIH1cbiAgICAgICAgfSlcbiAgICAgICAgLmZhY3RvcnkoJ18nLCBbJyR3aW5kb3cnLFxuICAgICAgICAgICAgICAgICAgICAgICAgZnVuY3Rpb24oJHdpbmRvdykge1xuICAgICAgICAgICAgICAgICAgICAgICAgICAgIC8vIHBsYWNlIGxvZGFzaCBpbmNsdWRlIGJlZm9yZSBhbmd1bGFyXG4gICAgICAgICAgICAgICAgICAgICAgICAgICAgcmV0dXJuICR3aW5kb3cuXztcbiAgICAgICAgICAgICAgICAgICAgICAgIH1cbiAgICAgICAgXSlcbiAgICAgICAgLmNvbmZpZyhmdW5jdGlvbigkcm91dGVQcm92aWRlciwgJGxvY2F0aW9uUHJvdmlkZXIsIHRvYXN0ckNvbmZpZykge1xuICAgICAgICAgICAgJHJvdXRlUHJvdmlkZXJcbiAgICAgICAgICAgICAgICAud2hlbignLycsIHtcbiAgICAgICAgICAgICAgICAgICAgLy8gdXJsOiAnL21hbmFnZS10cmFuc2xhdGlvbnMnLFxuICAgICAgICAgICAgICAgICAgICB0ZW1wbGF0ZVVybDogJy4uLy4uL21hbmFnZVRyYW5zbGF0aW9ucy5odG1sJyxcbiAgICAgICAgICAgICAgICAgICAgY29udHJvbGxlcjogJ01hbmFnZVRyYW5zbGF0aW9uc0NvbnRyb2xsZXInLFxuICAgICAgICAgICAgICAgICAgICBjb250cm9sbGVyQXM6ICd2bSdcbiAgICAgICAgICAgICAgICB9KTtcblxuICAgICAgICAgICAgLy8gU2V0IG9wdGlvbnMgdGhpcmQtcGFydHkgbGliXG4gICAgICAgICAgICB0b2FzdHJDb25maWcuYWxsb3dIdG1sID0gdHJ1ZTtcbiAgICAgICAgICAgIHRvYXN0ckNvbmZpZy50aW1lT3V0ID0gNzAwMDtcbiAgICAgICAgICAgIHRvYXN0ckNvbmZpZy5wb3NpdGlvbkNsYXNzID0gJ3RvYXN0LWJvdHRvbS1jZW50ZXInO1xuICAgICAgICAgICAgdG9hc3RyQ29uZmlnLnByb2dyZXNzQmFyID0gdHJ1ZTsgICBcbiAgICAgICAgIFxuXG4gICAgICAgICAgICAvLyAkbG9jYXRpb25Qcm92aWRlci5oYXNoUHJlZml4KCcnKTtcbiAgICAgICAgICAgICRsb2NhdGlvblByb3ZpZGVyLmh0bWw1TW9kZSh0cnVlKTtcbiAgICAgICAgfSlcbiAgICAgICAgLy8gLmZpbHRlcigndW5zYWZlJywgZnVuY3Rpb24oJHNjZSkge1xuICAgICAgICAvLyAgICAgcmV0dXJuIGZ1bmN0aW9uKHZhbHVlKSB7XG4gICAgICAgIC8vICAgICAgICAgaWYgKCF2YWx1ZSkgeyByZXR1cm4gJyc7IH1cbiAgICAgICAgLy8gICAgICAgICByZXR1cm4gJHNjZS50cnVzdEFzSHRtbCh2YWx1ZSk7XG4gICAgICAgIC8vICAgICB9O1xuICAgICAgICAvLyB9KTtcbn0pKCk7IiwiYW5ndWxhci5tb2R1bGUoXCJIYWxsb0Nhc2FBZG1pbi5lbnZpcm9ubWVudC5jb25zdGFudHNcIiwgW10pXG4uY29uc3RhbnQoXCJiYWNrZW5kX3VybFwiLCBcImh0dHA6Ly93d3cuaGFsbG9jYXNhLmNvbTo2NDY0Ny9oYWxsb2Nhc2EtYXBpL1wiKVxuLmNvbnN0YW50KFwidXNlcl9pbWFnZXNfdXJsXCIsIFwiaHR0cDovL3d3dy5oYWxsb2Nhc2EuY29tOjY0NjQ1L3Jlc291cmNlcy9pbWFnZXMvdXNlcnMvXCIpXG4uY29uc3RhbnQoXCJwcm9wZXJ0eV9pbWFnZXNfdXJsXCIsIFwiaHR0cDovL3d3dy5oYWxsb2Nhc2EuY29tOjY0NjQ1L3Jlc291cmNlcy9pbWFnZXMvcHJvcGVydGllcy9cIilcbi5jb25zdGFudChcInNlY3VyaXR5X2tleVwiLCBcIjRoNGg0JmdmaGdrNmVkZHk1JWdqaGRra2Q2bHN1XCIpO1xuIiwiLyogZ2xvYmFsIG1vbWVudDpmYWxzZSAqL1xuKGZ1bmN0aW9uKCkge1xuICAndXNlIHN0cmljdCc7XG5cbiAgYW5ndWxhclxuICAgIC5tb2R1bGUoJ0hhbGxvQ2FzYUFkbWluLmVudmlyb25tZW50LmNvbnN0YW50cycpXG4gICAgLmNvbnN0YW50KCdHZW5lcmljUkVTVFJlc291cmNlJywge1xuICAgICAgICBxdWVyeTogeyBtZXRob2Q6ICdHRVQnLCBpc0FycmF5OiB0cnVlfSxcbiAgICAgICAgY3JlYXRlOiB7IG1ldGhvZDogJ1BPU1QnfSxcbiAgICAgICAgY29uc3VsdDogeyBtZXRob2Q6ICdQT1NUJywgaXNBcnJheTogdHJ1ZX0sXG4gICAgICAgIGNvbnN1bHRPYmo6IHsgbWV0aG9kOiAnUE9TVCd9LFxuICAgICAgICBzaG93OiB7IG1ldGhvZDogJ0dFVCd9LFxuICAgICAgICB1cGRhdGU6IHsgbWV0aG9kOiAnUFVUJywgcGFyYW1zOiB7aWQ6ICdAaWQnfX0sXG4gICAgICAgIGRlbGV0ZTogeyBtZXRob2Q6ICdERUxFVEUnLCBwYXJhbXM6IHtpZDogJ0BpZCd9fVxuICAgIH0pXG5cbn0pKCk7ICAgICIsIihmdW5jdGlvbiAoKSB7XG4gICAgJ3VzZSBzdHJpY3QnO1xuICAgIHZhciBtb2R1bGVOYW1lID0gJ01hbmFnZVRyYW5zbGF0aW9uc0N0cmwnO1xuXG4gICAgYW5ndWxhci5tb2R1bGUobW9kdWxlTmFtZSwgW10pXG4gICAgLmRpcmVjdGl2ZSgnbWR0Q3VzdG9tQ2VsbEJ1dHRvbicsIGZ1bmN0aW9uICgpIHtcbiAgICAgICAgICAgIHJldHVybiB7XG4gICAgICAgICAgICAgICAgdGVtcGxhdGU6ICc8bWQtYnV0dG9uIGNsYXNzPVwibWQtaWNvbi1idXR0b25cIj48bWQtaWNvbiBtZC1mb250LXNldD1cIm1hdGVyaWFsLWljb25zXCI+YXNzaWdubWVudF90dXJuZWRfaW48L21kLWljb24+PC9tZC1idXR0b24+JyxcbiAgICAgICAgICAgIH07XG4gICAgICAgIH0pO1xuXG4gICAgdmFyIE1hbmFnZVRyYW5zbGF0aW9uc0NvbnRyb2xsZXIgPSBmdW5jdGlvbiAoJHNjb3BlLCBcbiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICRyb290U2NvcGUsIFxuICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgVHJhbnNsYXRpb25TZXJ2aWNlLCBcbiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICRtZERpYWxvZywgXG4gICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICBsb2Rhc2gsIFxuICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgdG9hc3RyKSB7XG5cbiAgICAgICAgdmFyIHZtID0gdGhpcztcblxuICAgICAgICAvL0luaXQgVmFyc1xuICAgICAgICAvLyB2bS5lbnZpcm9ubWVudCA9ICdxYSc7XG5cbiAgICAgICAgdm0uZW52aXJvbm1lbnRzID0gW1xuICAgICAgICAgICAgeyBpZDogMSwgbmFtZTogJ1FBJyB9LFxuICAgICAgICAgICAgeyBpZDogMiwgbmFtZTogJ1Byb2R1Y3Rpb24nIH0sXG4gICAgICAgICAgICB7IGlkOiAzLCBuYW1lOiAnUUEgKyBQcm9kdWN0aW9uJyB9XG4gICAgICAgIF07XG4gICAgICAgIHZtLmVudmlyb25tZW50ID0geyBpZDogMSwgbmFtZTogJ1FBJyB9O1xuXG4gICAgICAgIHZtLm9wZW5Nb2RhbCA9IG9wZW5Nb2RhbDtcbiAgICAgICAgdm0ubmV3VHJhbnNsYXRpb24gPSBuZXdUcmFuc2xhdGlvbjtcbiAgICAgICAgXG4gICAgICAgICRzY29wZS5lZGl0VHJhbnNsYXRpb24gPSBlZGl0VHJhbnNsYXRpb247XG4gICAgICAgICRzY29wZS5kZWxldGVUcmFuc2xhdGlvbiA9IGRlbGV0ZVRyYW5zbGF0aW9uO1xuXG4gICAgICAgIGNsZWFuTW9kZWwoKTtcblxuICAgICAgICBUcmFuc2xhdGlvblNlcnZpY2UubG9hZExvY2FsZXMoKS50aGVuKGZ1bmN0aW9uKGxvY2F0aW9ucyl7XG4gICAgICAgICAgICB2bS5sb2NhdGlvbnMgPSBsb2NhdGlvbnM7XG4gICAgICAgICAgICAvLyBjb25zb2xlLmxvZygnTG9jYXRpb25zICcsIHZtLmxvY2F0aW9ucyk7XG5cbiAgICAgICAgICAgIC8vIHZtLmxvY2F0aW9ucyA9IFtcbiAgICAgICAgICAgIC8vICAgICB7XG4gICAgICAgICAgICAvLyAgICAgICAgIGRlREU6XCJLZWluZVwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBkZXNjcmlwdGlvbjpcIlwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBlblVTOlwiTm9uZVwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBwbmVtb25pYzpcImhhbGxvY2FzYS5kcm9wZG93bm9wdGlvbi5oZWF0aW5nbm9uZVwiXG4gICAgICAgICAgICAvLyAgICAgfSxcbiAgICAgICAgICAgIC8vICAgICB7XG4gICAgICAgICAgICAvLyAgICAgICAgIGRlREU6XCJLZWluZVwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBkZXNjcmlwdGlvbjpcIlwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBlblVTOlwiTm9uZVwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBwbmVtb25pYzpcImhhbGxvY2FzYS5kcm9wZG93bm9wdGlvbi5oZWF0aW5nbm9uZVwiXG4gICAgICAgICAgICAvLyAgICAgfSxcbiAgICAgICAgICAgIC8vICAgICB7XG4gICAgICAgICAgICAvLyAgICAgICAgIGRlREU6XCJTw7xrb3JlYVwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBkZXNjcmlwdGlvbjpcIlwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBlblVTOlwiS29yZWEgKFNvdXRoKVwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBlc0VTOlwiQ29yZWEgKERlbCBTdXIpXCIsXG4gICAgICAgICAgICAvLyAgICAgICAgIHBuZW1vbmljOlwiaGFsbG9jYXNhLnRlbGVwaG9uZXByZWZpeC5rb3JlYW9wZW5wYXJzb3V0aGNsb3NlcGFyXCJcbiAgICAgICAgICAgIC8vICAgICB9XG4gICAgICAgICAgICAvLyBdO1xuXG4gICAgICAgIH0pO1xuXG4gICAgICAgICRzY29wZS4kd2F0Y2goJ3NlbGVjdGVkSW5kZXgnLCBmdW5jdGlvbihjdXJyZW50LCBvbGQpe1xuICAgICAgICAgICAgLy8gcHJldmlvdXMgPSBzZWxlY3RlZDtcbiAgICAgICAgICAgIC8vIHZhciBzZWxlY3RlZCA9ICRzY29wZS5sZW5ndWFnZXNbY3VycmVudF07XG5cbiAgICAgICAgICAgIC8vIGNvbnNvbGUubG9nKCdMZW5ndWFnZSBTZWxlY3RlZCAnLHNlbGVjdGVkKTtcbiAgICAgICAgICAgIC8vIGNvbnNvbGUubG9nKCdDb2RlICcsIHNlbGVjdGVkLmNvZGUpO1xuXG4gICAgICAgICAgICAvLyAkc2NvcGUudHJhbnNsYXRpb25zID0gdHJhbnNsYXRpb25zO1xuXG4gICAgICAgICAgICAvLyBjb25zb2xlLmxvZygnVHJhbnNsYXRpb25zICcsIHRyYW5zbGF0aW9ucyk7XG4gICAgICAgICAgICAvLyBpZiAoIG9sZCArIDEgJiYgKG9sZCAhPSBjdXJyZW50KSkgJGxvZy5kZWJ1ZygnR29vZGJ5ZSAnICsgcHJldmlvdXMudGl0bGUgKyAnIScpO1xuICAgICAgICAgICAgLy8gaWYgKCBjdXJyZW50ICsgMSApICAgICAgICAgICAgICAgICRsb2cuZGVidWcoJ0hlbGxvICcgKyBzZWxlY3RlZC50aXRsZSArICchJyk7XG4gICAgICAgIH0pO1xuXG4gICAgICAgIC8vIEZpbmQgZWxlbWVudCBieSBwbmVtb25pYyBhbmQgb3BlbiBtb2RhbFxuICAgICAgICBmdW5jdGlvbiBlZGl0VHJhbnNsYXRpb24oaWQpe1xuICAgICAgICAgICAgY29uc29sZS5sb2coJ1N0YXJ0IGZpbmQnLCBpZCk7XG5cbiAgICAgICAgICAgIGNsZWFuTW9kZWwoKTtcblxuICAgICAgICAgICAgdmFyIHRyYW5zbGF0aW9uID0gXy5maW5kKHZtLmxvY2F0aW9ucywgZnVuY3Rpb24oaXRlbSl7XG4gICAgICAgICAgICAgICAgcmV0dXJuIGl0ZW0ucG5lbW9uaWMgPT09IGlkO1xuICAgICAgICAgICAgfSk7XG5cbiAgICAgICAgICAgIGNvbnNvbGUubG9nKCdUcmFkdWNjaW9uICcsIHRyYW5zbGF0aW9uKTtcbiAgICAgICAgICAgIHZtLnRyYW5zbGF0aW9uID0gdHJhbnNsYXRpb247XG5cbiAgICAgICAgICAgIHZtLm9wZW5Nb2RhbCh0cnVlKTtcbiAgICAgICAgfTtcblxuICAgICAgICAvLyBGaW5kIGVsZW1lbnQgYnkgcG5lbW9uaWMgYW5kIGRlbGV0ZWQgZWxlbWVudFxuICAgICAgICBmdW5jdGlvbiBkZWxldGVUcmFuc2xhdGlvbihpZCl7XG5cbiAgICAgICAgICAgIHZhciB0cmFuc2xhdGlvbiA9IF8uZmluZCh2bS5sb2NhdGlvbnMsIGZ1bmN0aW9uKGl0ZW0pe1xuICAgICAgICAgICAgICAgIHJldHVybiBpdGVtLnBuZW1vbmljID09PSBpZDtcbiAgICAgICAgICAgIH0pO1xuICAgICAgICAgICAgY29uc29sZS5sb2coJ1RyYWR1Y2Npb24gJywgdHJhbnNsYXRpb24pXG5cbiAgICAgICAgICAgIHZhciBpc0NvbmZpcm1EZWxldGUgPSBjb25maXJtKCdEbyB5b3Ugd2FudCBkZWxldGUgdGhpcyB0cmFuc2xhdGlvbj8nKTtcbiAgICAgICAgICAgIGlmIChpc0NvbmZpcm1EZWxldGUpIHtcblxuICAgICAgICAgICAgICAgIFRyYW5zbGF0aW9uU2VydmljZS5kZWxldGVMb2NhbGUodHJhbnNsYXRpb24ucG5lbW9uaWMpXG4gICAgICAgICAgICAgICAgLnRoZW4oZnVuY3Rpb24oZWxlbSl7XG4gICAgICAgICAgICAgICAgICAgIC8vIHZtLmxvY2F0aW9ucyA9IGxvY2F0aW9ucztcbiAgICAgICAgICAgICAgICAgICAgdG9hc3RyLnN1Y2Nlc3MoJ0RlbGV0ZSBDb21wbGV0ZWQhJyk7XG5cbiAgICAgICAgICAgICAgICAgICAgVHJhbnNsYXRpb25TZXJ2aWNlLmxvYWRMb2NhbGVzKCkudGhlbihmdW5jdGlvbihsb2NhdGlvbnMpe1xuICAgICAgICAgICAgICAgICAgICAgICAgdm0ubG9jYXRpb25zID0gbG9jYXRpb25zO1xuICAgICAgICAgICAgICAgICAgICB9KTtcbiAgICAgICAgICAgICAgICB9KVxuICAgICAgICAgICAgICAgIC5jYXRjaChmdW5jdGlvbiAoKSB7XG4gICAgICAgICAgICAgICAgICAgIHRvYXN0ci5lcnJvcignVHJ5IGRlbGV0ZSB0aGlzIGVsZW1lbnQgTGF0ZScsICdFcnJvcicpO1xuICAgICAgICAgICAgICAgIH0pO1xuICAgICAgICAgICAgfSBlbHNlIHtcbiAgICAgICAgICAgICAgICByZXR1cm4gZmFsc2U7XG4gICAgICAgICAgICB9XG4gICAgICAgIH1cblxuICAgICAgICBmdW5jdGlvbiBuZXdUcmFuc2xhdGlvbigpe1xuICAgICAgICAgICAgY2xlYW5Nb2RlbCgpO1xuICAgICAgICAgICAgb3Blbk1vZGFsKGZhbHNlKTtcbiAgICAgICAgfVxuXG5cbiAgICAgICAgLy8gT3BlbiBtb2RhbCB3aGVuIGxpc3QgaXMgPiBtYXhJdGVtc1xuICAgICAgICBmdW5jdGlvbiBvcGVuTW9kYWwoZWRpdFZhbCkge1xuICAgICAgICAgICAgLy8gdG9hc3RyLmFjdGl2ZSgnSGVsbG8gd29ybGQhJywgJ1RvYXN0ciBmdW4hJyk7XG5cbiAgICAgICAgICAgICRtZERpYWxvZy5zaG93KHtcbiAgICAgICAgICAgICAgICBjb250cm9sbGVyOiBEaWFsb2dDb250cm9sbGVyLFxuICAgICAgICAgICAgICAgIHRlbXBsYXRlVXJsOiAnLi4vLi4vZGlhbG9nLm5ldy5sZW5ndWFnZXMudG1wbC5odG1sJyxcbiAgICAgICAgICAgICAgICBwYXJlbnQ6IGFuZ3VsYXIuZWxlbWVudChkb2N1bWVudC5ib2R5KSxcbiAgICAgICAgICAgICAgICBjbGlja091dHNpZGVUb0Nsb3NlOiB0cnVlLFxuICAgICAgICAgICAgICAgIGZ1bGxzY3JlZW46ZmFsc2UsXG4gICAgICAgICAgICAgICAgbG9jYWxzOiB7XG4gICAgICAgICAgICAgICAgICAgIGVsZW06IHZtLnRyYW5zbGF0aW9uLFxuICAgICAgICAgICAgICAgICAgICBlZGl0OiBlZGl0VmFsXG4gICAgICAgICAgICAgICAgfVxuICAgICAgICAgICAgfSk7XG4gICAgICAgIH1cblxuICAgICAgICAvLyBDb250cm9sbGVyIHRvIE1vZGFsXG4gICAgICAgIGZ1bmN0aW9uIERpYWxvZ0NvbnRyb2xsZXIoJHNjb3BlLCAkbWREaWFsb2csIGVsZW0sIGVkaXQsIFRyYW5zbGF0aW9uU2VydmljZSx0b2FzdHIpIHtcbiAgICAgICAgICAgICRzY29wZS50cmFuc2xhdGlvbiA9IGVsZW07XG4gICAgICAgICAgICAkc2NvcGUuZWRpdCA9IGVkaXQ7XG5cblxuICAgICAgICAgICAgJHNjb3BlLmNsb3NlRGlhbG9nID0gZnVuY3Rpb24gY2xvc2VEaWFsb2coKSB7XG4gICAgICAgICAgICAgICAgY29uc29sZS5sb2coJ0NlcnJhciBEaWFsb2dvJyk7XG4gICAgICAgICAgICAgICAgJG1kRGlhbG9nLmNhbmNlbCgpO1xuICAgICAgICAgICAgfTtcblxuICAgICAgICAgICAgJHNjb3BlLnNhdmVUcmFuc2xhdGlvbiA9IGZ1bmN0aW9uIHNhdmVUcmFuc2xhdGlvbigpIHtcbiAgICAgICAgICAgICAgICBjb25zb2xlLmxvZygnSW5mb3JtYXRpb24gU0FWSU5HJywgJHNjb3BlLnRyYW5zbGF0aW9uKTtcblxuICAgICAgICAgICAgICAgIFRyYW5zbGF0aW9uU2VydmljZS5zYXZlTG9jYWxlKCRzY29wZS50cmFuc2xhdGlvbilcbiAgICAgICAgICAgICAgICAudGhlbihmdW5jdGlvbihsb2NhdGlvbnMpe1xuICAgICAgICAgICAgICAgICAgICB0b2FzdHIuc3VjY2VzcygnU2F2ZSBDb21wbGV0ZWQhJyk7XG5cbiAgICAgICAgICAgICAgICAgICAgVHJhbnNsYXRpb25TZXJ2aWNlLmxvYWRMb2NhbGVzKCkudGhlbihmdW5jdGlvbihsb2NhdGlvbnMpe1xuICAgICAgICAgICAgICAgICAgICAgICAgdm0ubG9jYXRpb25zID0gbG9jYXRpb25zO1xuICAgICAgICAgICAgICAgICAgICB9KTtcblxuICAgICAgICAgICAgICAgICAgICAkc2NvcGUuY2xvc2VEaWFsb2coKTtcbiAgICAgICAgICAgICAgICB9KVxuICAgICAgICAgICAgICAgIC5jYXRjaChmdW5jdGlvbiAoKSB7XG4gICAgICAgICAgICAgICAgICAgIHRvYXN0ci5lcnJvcignVHJ5IExhdGUnLCAnRXJyb3InKTtcbiAgICAgICAgICAgICAgICB9KTtcbiAgICAgICAgICAgIH1cbiAgICAgICAgfVxuXG5cbiAgICAgICAgZnVuY3Rpb24gY2xlYW5Nb2RlbCgpe1xuICAgICAgICAgICAgdm0udHJhbnNsYXRpb24gPSB7XG4gICAgICAgICAgICAgICAgbGFiZWw6ICcnLFxuICAgICAgICAgICAgICAgIGRlc2NyaXB0aW9uOiAnJyxcbiAgICAgICAgICAgICAgICBlblVTOiAnJyxcbiAgICAgICAgICAgICAgICBlc0VTOiAnJyxcbiAgICAgICAgICAgICAgICBkZURFOiAnJ1xuICAgICAgICAgICAgfVxuICAgICAgICB9XG4gICAgICAgIFxuICAgIH07XG5cbiAgICAvL0RlY2xhcm8gY29udHJvbGxhZG9yXG4gICAgYW5ndWxhci5tb2R1bGUobW9kdWxlTmFtZSlcbiAgICAgICAgLmNvbnRyb2xsZXIoJ01hbmFnZVRyYW5zbGF0aW9uc0NvbnRyb2xsZXInLCBbXG4gICAgICAgICAgICAnJHNjb3BlJyxcbiAgICAgICAgICAgIFwiJHJvb3RTY29wZVwiLFxuICAgICAgICAgICAgXCJUcmFuc2xhdGlvblNlcnZpY2VcIixcbiAgICAgICAgICAgIFwiJG1kRGlhbG9nXCIsXG4gICAgICAgICAgICBcIl9cIixcbiAgICAgICAgICAgIFwidG9hc3RyXCIsXG4gICAgICAgICAgICBNYW5hZ2VUcmFuc2xhdGlvbnNDb250cm9sbGVyXSk7XG59KSgpOyIsIihmdW5jdGlvbigpIHtcbiAgJ3VzZSBzdHJpY3QnO1xuXG4gIGFuZ3VsYXJcbiAgICAubW9kdWxlKCdIYWxsb0Nhc2FBZG1pbicpXG4gICAgLnNlcnZpY2UoJ1RyYW5zbGF0aW9uU2VydmljZScsIFRyYW5zbGF0aW9uU2VydmljZSApO1xuXG4gIC8qKiBAbmdJbmplY3QgKi9cbiAgZnVuY3Rpb24gVHJhbnNsYXRpb25TZXJ2aWNlICgkcSwgXG4gICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICRyZXNvdXJjZSwgXG4gICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICRodHRwLFxuICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICBHZW5lcmljUkVTVFJlc291cmNlLCBcbiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgYmFja2VuZF91cmwsIFxuICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICBzZWN1cml0eV9rZXkpIHtcbiAgICB2YXIgc2VydmljZSA9IHtcbiAgICAgICAgbG9hZExvY2FsZXM6IGxvYWRMb2NhbGVzLFxuICAgICAgICBzYXZlTG9jYWxlOiBzYXZlTG9jYWxlLFxuICAgICAgICBkZWxldGVMb2NhbGU6IGRlbGV0ZUxvY2FsZVxuICAgIC8vICAgZ2V0Q3VycmVudFBvc2l0aW9uOiBnZXRDdXJyZW50UG9zaXRpb25cbiAgICB9O1xuXG4gICAgdmFyIHJlc291cmNlcyA9IHtcbiAgICAgIGxvY2FsZTogJHJlc291cmNlKGJhY2tlbmRfdXJsICsgXCJsb2NhbGVzXCIsIHt9LCBHZW5lcmljUkVTVFJlc291cmNlKSxcbiAgICAgIHNhdmVMb2NhbGU6ICRyZXNvdXJjZShiYWNrZW5kX3VybCArIFwibG9jYWxlc1wiLCB7fSBcbiAgICAgIClcbiAgICAvLyAgIGV4Y2hhbmdlOiAkcmVzb3VyY2UoYmFja2VuZF91cmwgKyBcImN1cnJlbmN5X2V4Y2hhbmdlX2RhdGFcIiwge30sIEdlbmVyaWNSRVNUUmVzb3VyY2UpXG4gICAgfTtcblxuICAgIHJldHVybiBzZXJ2aWNlO1xuXG4gICAgZnVuY3Rpb24gbG9hZExvY2FsZXMoKSB7XG4gICAgICByZXR1cm4gcmVzb3VyY2VzLmxvY2FsZS5xdWVyeSgpLiRwcm9taXNlO1xuICAgIH1cblxuICAgIGZ1bmN0aW9uIHNhdmVMb2NhbGUoaXRlbSl7XG4gICAgICB2YXIgZGF0YSA9IFtcbiAgICAgICAgeyBcbiAgICAgICAgICBcInBuZW1vbmljXCI6IGl0ZW0ucG5lbW9uaWMsXG4gICAgICAgICAgXCJkZXNjcmlwdGlvblwiOiBpdGVtLmRlc2NyaXB0aW9uLFxuICAgICAgICAgIFwiZW5VU1wiOiBpdGVtLmVuVVMsXG4gICAgICAgICAgXCJlc0VTXCI6IGl0ZW0uZXNFUyxcbiAgICAgICAgICBcImRlREVcIjogaXRlbS5kZURFXG4gICAgICAgIH1cbiAgICAgIF1cblxuICAgICAgdmFyIHJlcSA9IHtcbiAgICAgICAgbWV0aG9kOiAnUE9TVCcsXG4gICAgICAgIHVybDogYmFja2VuZF91cmwgKyBcImxvY2FsZXMvXCIsXG4gICAgICAgIGhlYWRlcnM6IHtcbiAgICAgICAgICAnQ29udGVudC1UeXBlJzonYXBwbGljYXRpb24vanNvbicsXG4gICAgICAgICAgJ3NlY3VyaXR5LWtleSc6IHNlY3VyaXR5X2tleVxuICAgICAgICB9LFxuICAgICAgICBkYXRhOiBkYXRhXG4gICAgICB9XG5cbiAgICAgIHJldHVybiAkaHR0cChyZXEpO1xuICAgIH1cblxuICAgIGZ1bmN0aW9uIGRlbGV0ZUxvY2FsZShpdGVtKXtcblxuICAgICAgdmFyIHJlcSA9IHtcbiAgICAgICAgbWV0aG9kOiAnREVMRVRFJyxcbiAgICAgICAgdXJsOiBiYWNrZW5kX3VybCArIFwibG9jYWxlcy9cIiArIGl0ZW0sXG4gICAgICAgIGhlYWRlcnM6IHtcbiAgICAgICAgICAnc2VjdXJpdHkta2V5Jzogc2VjdXJpdHlfa2V5XG4gICAgICAgIH1cbiAgICAgIH1cblxuICAgICAgcmV0dXJuICRodHRwKHJlcSk7XG4gICAgfVxuXG4gICAgLy8gZnVuY3Rpb24gZ2V0Q3VycmVudFBvc2l0aW9uKCkge1xuICAgIC8vICAgcmV0dXJuICRxKGZ1bmN0aW9uIChyZXNvbHZlLCByZWplY3QpIHtcbiAgICAvLyAgICAgaWYgKCEkd2luZG93Lm5hdmlnYXRvci5nZW9sb2NhdGlvbikge1xuICAgIC8vICAgICAgIHJlamVjdCgnR2VvbG9jYXRpb24gbm90IHN1cHBvcnRlZC4nKTtcbiAgICAvLyAgICAgfSBlbHNlIHtcbiAgICAvLyAgICAgICAkd2luZG93Lm5hdmlnYXRvci5nZW9sb2NhdGlvbi5nZXRDdXJyZW50UG9zaXRpb24oXG4gICAgLy8gICAgICAgICBmdW5jdGlvbiAocG9zaXRpb24pIHtcbiAgICAvLyAgICAgICAgICAgcmVzb2x2ZShwb3NpdGlvbik7XG4gICAgLy8gICAgICAgICB9LFxuICAgIC8vICAgICAgICAgZnVuY3Rpb24gKGVycm9yKSB7XG4gICAgLy8gICAgICAgICAgIHJlamVjdChlcnJvcik7XG4gICAgLy8gICAgICAgICB9KTtcbiAgICAvLyAgICAgfVxuICAgIC8vICAgfSk7XG4gICAgLy8gfVxuXG4gIH1cblxufSkoKTsiXX0=
