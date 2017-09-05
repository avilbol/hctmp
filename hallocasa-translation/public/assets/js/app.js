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
        saveLocale: saveLocale
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

      // return $http(backend_url + "locales/", {
      //         "pnemonic": item.pnemonic,
      //         "description": item.description,
      //         "enUS": item.enUS,
      //         "esES": item.esES,
      //         "deDE": item.deDE
      //       }, {
      //       headers: {
      //         'Content-Type': 'application/json',
      //         'security-key': security_key
      //       }
      // });


      // var resource = $resource(backend_url + "locales", {}, {
      //   'save': {
      //     method: 'POST',
      //     headers: {
      //       'Content-Type': 'application/json ; charset=UTF-8',
      //       'security-key': security_key
      //     }
      //   }
      // });  
      // var DirectoryApi = $resource(backend_url, null, {
      //     move: {
      //         url: backend_url,
      //         headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
      //         transformRequest: function (param) {
      //             return $.param(param);
      //         },
      //         method: 'POST'
      //     },
      // });

      // return resource.save(item).$promise;
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
//# sourceMappingURL=data:application/json;charset=utf8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImFuZ3VsYXIvYXBwLmpzIiwiYW5ndWxhci9lbnZpcm9tZW50LmNvbnN0YW50cy5qcyIsImFuZ3VsYXIvaW5kZXguY29uc3RhbnRzLmpzIiwiYW5ndWxhci9jb250cm9sbGVycy9tYW5hZ2VUcmFuc2xhdGlvbnNDb250cm9sbGVyLmpzIiwiYW5ndWxhci9zZXJ2aWNlcy90cmFuc2xhdGlvbi5zZXJ2aWNlLmpzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQ2pFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUNMQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FDaEJBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUNqTUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0EiLCJmaWxlIjoiYXBwLmpzIiwic291cmNlc0NvbnRlbnQiOlsiKGZ1bmN0aW9uICgpIHtcbiAgICAndXNlIHN0cmljdCc7XG5cbiAgICB2YXIgYXBwID0gYW5ndWxhci5tb2R1bGUoXCJIYWxsb0Nhc2FBZG1pblwiLCBbXG4gICAgICAgICduZ1JvdXRlJyxcbiAgICAgICAgJ25nTWF0ZXJpYWwnLFxuICAgICAgICAnTWFuYWdlVHJhbnNsYXRpb25zQ3RybCcsXG4gICAgICAgICduZ1Nhbml0aXplJyxcbiAgICAgICAgJ25nUmVzb3VyY2UnLFxuICAgICAgICAnbWREYXRhVGFibGUnLFxuICAgICAgICAnbmdBbmltYXRlJyxcbiAgICAgICAgJ3RvYXN0cicsXG4gICAgICAgIC8vICd1bmRlcnNjb3JlJyxcbiAgICAgICAgLy9BcHAgbW9kdWxlc1xuICAgICAgICAnSGFsbG9DYXNhQWRtaW4uZW52aXJvbm1lbnQuY29uc3RhbnRzJ1xuICAgIF0pO1xuXG4gICAgXG4gICAgYXBwXG4gICAgICAgIC5jb250cm9sbGVyKFwiTWFpbkNvbnRyb2xsZXJcIiwgZnVuY3Rpb24oJHNjb3BlLCAkcm91dGUsICRyb3V0ZVBhcmFtcywgJGxvY2F0aW9uKSB7XG4gICAgICAgICAgICAkc2NvcGUuJHJvdXRlID0gJHJvdXRlO1xuICAgICAgICAgICAgJHNjb3BlLiRsb2NhdGlvbiA9ICRsb2NhdGlvbjtcbiAgICAgICAgICAgICRzY29wZS4kcm91dGVQYXJhbXMgPSAkcm91dGVQYXJhbXM7XG4gICAgICAgICAgICBcbiAgICAgICAgICAgIC8vICRzY29wZS4kb24oJ2NmcExvYWRpbmdCYXI6Y29tcGxldGVkJywgZnVuY3Rpb24oZXZlbnQsIGRhdGEpIHtcbiAgICAgICAgICAgIC8vICAgICBhbmd1bGFyLmVsZW1lbnQoXCIuYW5pbWF0ZWRcIikuYWRkQ2xhc3MoXCJmYWRlSW5cIik7XG4gICAgICAgICAgICAvLyB9KTtcbiAgICAgICAgICAgIC8vICRzY29wZS5nb1RvID0gZnVuY3Rpb24odXJsLCBjbG9zZU1lbnUpIHtcbiAgICAgICAgICAgIC8vICAgICAkbG9jYXRpb24udXJsKHVybCk7XG4gICAgICAgICAgICAvLyAgICAgaWYoY2xvc2VNZW51KXtcbiAgICAgICAgICAgIC8vICAgICAgICAgdG9nZ2xlTWVudSgpO1xuICAgICAgICAgICAgLy8gICAgIH1cbiAgICAgICAgICAgIC8vIH1cbiAgICAgICAgfSlcbiAgICAgICAgLmZhY3RvcnkoJ18nLCBbJyR3aW5kb3cnLFxuICAgICAgICAgICAgICAgICAgICAgICAgZnVuY3Rpb24oJHdpbmRvdykge1xuICAgICAgICAgICAgICAgICAgICAgICAgICAgIC8vIHBsYWNlIGxvZGFzaCBpbmNsdWRlIGJlZm9yZSBhbmd1bGFyXG4gICAgICAgICAgICAgICAgICAgICAgICAgICAgcmV0dXJuICR3aW5kb3cuXztcbiAgICAgICAgICAgICAgICAgICAgICAgIH1cbiAgICAgICAgXSlcbiAgICAgICAgLmNvbmZpZyhmdW5jdGlvbigkcm91dGVQcm92aWRlciwgJGxvY2F0aW9uUHJvdmlkZXIsIHRvYXN0ckNvbmZpZykge1xuICAgICAgICAgICAgJHJvdXRlUHJvdmlkZXJcbiAgICAgICAgICAgICAgICAud2hlbignLycsIHtcbiAgICAgICAgICAgICAgICAgICAgLy8gdXJsOiAnL21hbmFnZS10cmFuc2xhdGlvbnMnLFxuICAgICAgICAgICAgICAgICAgICB0ZW1wbGF0ZVVybDogJy4uLy4uL21hbmFnZVRyYW5zbGF0aW9ucy5odG1sJyxcbiAgICAgICAgICAgICAgICAgICAgY29udHJvbGxlcjogJ01hbmFnZVRyYW5zbGF0aW9uc0NvbnRyb2xsZXInLFxuICAgICAgICAgICAgICAgICAgICBjb250cm9sbGVyQXM6ICd2bSdcbiAgICAgICAgICAgICAgICB9KTtcblxuICAgICAgICAgICAgLy8gU2V0IG9wdGlvbnMgdGhpcmQtcGFydHkgbGliXG4gICAgICAgICAgICB0b2FzdHJDb25maWcuYWxsb3dIdG1sID0gdHJ1ZTtcbiAgICAgICAgICAgIHRvYXN0ckNvbmZpZy50aW1lT3V0ID0gNzAwMDtcbiAgICAgICAgICAgIHRvYXN0ckNvbmZpZy5wb3NpdGlvbkNsYXNzID0gJ3RvYXN0LWJvdHRvbS1jZW50ZXInO1xuICAgICAgICAgICAgdG9hc3RyQ29uZmlnLnByb2dyZXNzQmFyID0gdHJ1ZTsgICBcbiAgICAgICAgIFxuXG4gICAgICAgICAgICAvLyAkbG9jYXRpb25Qcm92aWRlci5oYXNoUHJlZml4KCcnKTtcbiAgICAgICAgICAgICRsb2NhdGlvblByb3ZpZGVyLmh0bWw1TW9kZSh0cnVlKTtcbiAgICAgICAgfSlcbiAgICAgICAgLy8gLmZpbHRlcigndW5zYWZlJywgZnVuY3Rpb24oJHNjZSkge1xuICAgICAgICAvLyAgICAgcmV0dXJuIGZ1bmN0aW9uKHZhbHVlKSB7XG4gICAgICAgIC8vICAgICAgICAgaWYgKCF2YWx1ZSkgeyByZXR1cm4gJyc7IH1cbiAgICAgICAgLy8gICAgICAgICByZXR1cm4gJHNjZS50cnVzdEFzSHRtbCh2YWx1ZSk7XG4gICAgICAgIC8vICAgICB9O1xuICAgICAgICAvLyB9KTtcbn0pKCk7IiwiYW5ndWxhci5tb2R1bGUoXCJIYWxsb0Nhc2FBZG1pbi5lbnZpcm9ubWVudC5jb25zdGFudHNcIiwgW10pXG4uY29uc3RhbnQoXCJiYWNrZW5kX3VybFwiLCBcImh0dHA6Ly93d3cuaGFsbG9jYXNhLmNvbTo2NDY0Ny9oYWxsb2Nhc2EtYXBpL1wiKVxuLmNvbnN0YW50KFwidXNlcl9pbWFnZXNfdXJsXCIsIFwiaHR0cDovL3d3dy5oYWxsb2Nhc2EuY29tOjY0NjQ1L3Jlc291cmNlcy9pbWFnZXMvdXNlcnMvXCIpXG4uY29uc3RhbnQoXCJwcm9wZXJ0eV9pbWFnZXNfdXJsXCIsIFwiaHR0cDovL3d3dy5oYWxsb2Nhc2EuY29tOjY0NjQ1L3Jlc291cmNlcy9pbWFnZXMvcHJvcGVydGllcy9cIilcbi5jb25zdGFudChcInNlY3VyaXR5X2tleVwiLCBcIjRoNGg0JmdmaGdrNmVkZHk1JWdqaGRra2Q2bHN1XCIpO1xuIiwiLyogZ2xvYmFsIG1vbWVudDpmYWxzZSAqL1xuKGZ1bmN0aW9uKCkge1xuICAndXNlIHN0cmljdCc7XG5cbiAgYW5ndWxhclxuICAgIC5tb2R1bGUoJ0hhbGxvQ2FzYUFkbWluLmVudmlyb25tZW50LmNvbnN0YW50cycpXG4gICAgLmNvbnN0YW50KCdHZW5lcmljUkVTVFJlc291cmNlJywge1xuICAgICAgICBxdWVyeTogeyBtZXRob2Q6ICdHRVQnLCBpc0FycmF5OiB0cnVlfSxcbiAgICAgICAgY3JlYXRlOiB7IG1ldGhvZDogJ1BPU1QnfSxcbiAgICAgICAgY29uc3VsdDogeyBtZXRob2Q6ICdQT1NUJywgaXNBcnJheTogdHJ1ZX0sXG4gICAgICAgIGNvbnN1bHRPYmo6IHsgbWV0aG9kOiAnUE9TVCd9LFxuICAgICAgICBzaG93OiB7IG1ldGhvZDogJ0dFVCd9LFxuICAgICAgICB1cGRhdGU6IHsgbWV0aG9kOiAnUFVUJywgcGFyYW1zOiB7aWQ6ICdAaWQnfX0sXG4gICAgICAgIGRlbGV0ZTogeyBtZXRob2Q6ICdERUxFVEUnLCBwYXJhbXM6IHtpZDogJ0BpZCd9fVxuICAgIH0pXG5cbn0pKCk7ICAgICIsIihmdW5jdGlvbiAoKSB7XG4gICAgJ3VzZSBzdHJpY3QnO1xuICAgIHZhciBtb2R1bGVOYW1lID0gJ01hbmFnZVRyYW5zbGF0aW9uc0N0cmwnO1xuXG4gICAgYW5ndWxhci5tb2R1bGUobW9kdWxlTmFtZSwgW10pXG4gICAgLmRpcmVjdGl2ZSgnbWR0Q3VzdG9tQ2VsbEJ1dHRvbicsIGZ1bmN0aW9uICgpIHtcbiAgICAgICAgICAgIHJldHVybiB7XG4gICAgICAgICAgICAgICAgdGVtcGxhdGU6ICc8bWQtYnV0dG9uIGNsYXNzPVwibWQtaWNvbi1idXR0b25cIj48bWQtaWNvbiBtZC1mb250LXNldD1cIm1hdGVyaWFsLWljb25zXCI+YXNzaWdubWVudF90dXJuZWRfaW48L21kLWljb24+PC9tZC1idXR0b24+JyxcbiAgICAgICAgICAgIH07XG4gICAgICAgIH0pO1xuXG4gICAgdmFyIE1hbmFnZVRyYW5zbGF0aW9uc0NvbnRyb2xsZXIgPSBmdW5jdGlvbiAoJHNjb3BlLCBcbiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICRyb290U2NvcGUsIFxuICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgVHJhbnNsYXRpb25TZXJ2aWNlLCBcbiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICRtZERpYWxvZywgXG4gICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICBsb2Rhc2gsIFxuICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgdG9hc3RyKSB7XG5cbiAgICAgICAgdmFyIHZtID0gdGhpcztcblxuICAgICAgICB2bS5vcGVuTW9kYWwgPSBvcGVuTW9kYWw7XG4gICAgICAgIHZtLm5ld1RyYW5zbGF0aW9uID0gbmV3VHJhbnNsYXRpb247XG4gICAgICAgICRzY29wZS5lZGl0VHJhbnNsYXRpb24gPSBlZGl0VHJhbnNsYXRpb247XG4gICAgICAgICRzY29wZS5kZWxldGVUcmFuc2xhdGlvbiA9IGRlbGV0ZVRyYW5zbGF0aW9uO1xuXG4gICAgICAgIGNsZWFuTW9kZWwoKTtcblxuICAgICAgICBUcmFuc2xhdGlvblNlcnZpY2UubG9hZExvY2FsZXMoKS50aGVuKGZ1bmN0aW9uKGxvY2F0aW9ucyl7XG4gICAgICAgICAgICB2bS5sb2NhdGlvbnMgPSBsb2NhdGlvbnM7XG4gICAgICAgICAgICAvLyBjb25zb2xlLmxvZygnTG9jYXRpb25zICcsIHZtLmxvY2F0aW9ucyk7XG5cbiAgICAgICAgICAgIC8vIHZtLmxvY2F0aW9ucyA9IFtcbiAgICAgICAgICAgIC8vICAgICB7XG4gICAgICAgICAgICAvLyAgICAgICAgIGRlREU6XCJLZWluZVwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBkZXNjcmlwdGlvbjpcIlwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBlblVTOlwiTm9uZVwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBwbmVtb25pYzpcImhhbGxvY2FzYS5kcm9wZG93bm9wdGlvbi5oZWF0aW5nbm9uZVwiXG4gICAgICAgICAgICAvLyAgICAgfSxcbiAgICAgICAgICAgIC8vICAgICB7XG4gICAgICAgICAgICAvLyAgICAgICAgIGRlREU6XCJLZWluZVwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBkZXNjcmlwdGlvbjpcIlwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBlblVTOlwiTm9uZVwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBwbmVtb25pYzpcImhhbGxvY2FzYS5kcm9wZG93bm9wdGlvbi5oZWF0aW5nbm9uZVwiXG4gICAgICAgICAgICAvLyAgICAgfSxcbiAgICAgICAgICAgIC8vICAgICB7XG4gICAgICAgICAgICAvLyAgICAgICAgIGRlREU6XCJTw7xrb3JlYVwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBkZXNjcmlwdGlvbjpcIlwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBlblVTOlwiS29yZWEgKFNvdXRoKVwiLFxuICAgICAgICAgICAgLy8gICAgICAgICBlc0VTOlwiQ29yZWEgKERlbCBTdXIpXCIsXG4gICAgICAgICAgICAvLyAgICAgICAgIHBuZW1vbmljOlwiaGFsbG9jYXNhLnRlbGVwaG9uZXByZWZpeC5rb3JlYW9wZW5wYXJzb3V0aGNsb3NlcGFyXCJcbiAgICAgICAgICAgIC8vICAgICB9XG4gICAgICAgICAgICAvLyBdO1xuXG4gICAgICAgIH0pO1xuXG4gICAgICAgICRzY29wZS4kd2F0Y2goJ3NlbGVjdGVkSW5kZXgnLCBmdW5jdGlvbihjdXJyZW50LCBvbGQpe1xuICAgICAgICAgICAgLy8gcHJldmlvdXMgPSBzZWxlY3RlZDtcbiAgICAgICAgICAgIC8vIHZhciBzZWxlY3RlZCA9ICRzY29wZS5sZW5ndWFnZXNbY3VycmVudF07XG5cbiAgICAgICAgICAgIC8vIGNvbnNvbGUubG9nKCdMZW5ndWFnZSBTZWxlY3RlZCAnLHNlbGVjdGVkKTtcbiAgICAgICAgICAgIC8vIGNvbnNvbGUubG9nKCdDb2RlICcsIHNlbGVjdGVkLmNvZGUpO1xuXG4gICAgICAgICAgICAvLyAkc2NvcGUudHJhbnNsYXRpb25zID0gdHJhbnNsYXRpb25zO1xuXG4gICAgICAgICAgICAvLyBjb25zb2xlLmxvZygnVHJhbnNsYXRpb25zICcsIHRyYW5zbGF0aW9ucyk7XG4gICAgICAgICAgICAvLyBpZiAoIG9sZCArIDEgJiYgKG9sZCAhPSBjdXJyZW50KSkgJGxvZy5kZWJ1ZygnR29vZGJ5ZSAnICsgcHJldmlvdXMudGl0bGUgKyAnIScpO1xuICAgICAgICAgICAgLy8gaWYgKCBjdXJyZW50ICsgMSApICAgICAgICAgICAgICAgICRsb2cuZGVidWcoJ0hlbGxvICcgKyBzZWxlY3RlZC50aXRsZSArICchJyk7XG4gICAgICAgIH0pO1xuXG4gICAgICAgIC8vIEZpbmQgZWxlbWVudCBieSBwbmVtb25pYyBhbmQgb3BlbiBtb2RhbFxuICAgICAgICBmdW5jdGlvbiBlZGl0VHJhbnNsYXRpb24oaWQpe1xuICAgICAgICAgICAgY29uc29sZS5sb2coJ1N0YXJ0IGZpbmQnLCBpZCk7XG5cbiAgICAgICAgICAgIGNsZWFuTW9kZWwoKTtcblxuICAgICAgICAgICAgdmFyIHRyYW5zbGF0aW9uID0gXy5maW5kKHZtLmxvY2F0aW9ucywgZnVuY3Rpb24oaXRlbSl7XG4gICAgICAgICAgICAgICAgcmV0dXJuIGl0ZW0ucG5lbW9uaWMgPT09IGlkO1xuICAgICAgICAgICAgfSk7XG5cbiAgICAgICAgICAgIGNvbnNvbGUubG9nKCdUcmFkdWNjaW9uICcsIHRyYW5zbGF0aW9uKTtcbiAgICAgICAgICAgIHZtLnRyYW5zbGF0aW9uID0gdHJhbnNsYXRpb247XG5cbiAgICAgICAgICAgIHZtLm9wZW5Nb2RhbCh0cnVlKTtcbiAgICAgICAgfTtcblxuICAgICAgICAvLyBGaW5kIGVsZW1lbnQgYnkgcG5lbW9uaWMgYW5kIGRlbGV0ZWQgZWxlbWVudFxuICAgICAgICBmdW5jdGlvbiBkZWxldGVUcmFuc2xhdGlvbihpZCl7XG5cbiAgICAgICAgICAgIHZhciB0cmFuc2xhdGlvbiA9IF8uZmluZCh2bS5sb2NhdGlvbnMsIGZ1bmN0aW9uKGl0ZW0pe1xuICAgICAgICAgICAgICAgIHJldHVybiBpdGVtLnBuZW1vbmljID09PSBpZDtcbiAgICAgICAgICAgIH0pO1xuICAgICAgICAgICAgY29uc29sZS5sb2coJ1RyYWR1Y2Npb24gJywgdHJhbnNsYXRpb24pXG5cbiAgICAgICAgICAgIHZhciBpc0NvbmZpcm1EZWxldGUgPSBjb25maXJtKCdEbyB5b3Ugd2FudCBkZWxldGUgdGhpcyB0cmFuc2xhdGlvbj8nKTtcbiAgICAgICAgICAgIGlmIChpc0NvbmZpcm1EZWxldGUpIHtcbiAgICAgICAgICAgICAgICBjb25zb2xlLmxvZygnRGVsZXRlZCBlbGVtZW50Jyk7XG4gICAgICAgICAgICB9IGVsc2Uge1xuICAgICAgICAgICAgICAgIHJldHVybiBmYWxzZTtcbiAgICAgICAgICAgIH1cbiAgICAgICAgfVxuXG4gICAgICAgIGZ1bmN0aW9uIG5ld1RyYW5zbGF0aW9uKCl7XG4gICAgICAgICAgICAvLyB0b2FzdHIuaW5mbygnV2UgYXJlIG9wZW4gdG9kYXkgZnJvbSAxMCB0byAyMicsICdJbmZvcm1hdGlvbicpO1xuXG4gICAgICAgICAgICBjbGVhbk1vZGVsKCk7XG4gICAgICAgICAgICBvcGVuTW9kYWwoZmFsc2UpO1xuICAgICAgICB9XG5cblxuICAgICAgICAvLyBPcGVuIG1vZGFsIHdoZW4gbGlzdCBpcyA+IG1heEl0ZW1zXG4gICAgICAgIGZ1bmN0aW9uIG9wZW5Nb2RhbChlZGl0VmFsKSB7XG4gICAgICAgICAgICAvLyB0b2FzdHIuYWN0aXZlKCdIZWxsbyB3b3JsZCEnLCAnVG9hc3RyIGZ1biEnKTtcblxuICAgICAgICAgICAgJG1kRGlhbG9nLnNob3coe1xuICAgICAgICAgICAgICAgIGNvbnRyb2xsZXI6IERpYWxvZ0NvbnRyb2xsZXIsXG4gICAgICAgICAgICAgICAgdGVtcGxhdGVVcmw6ICcuLi8uLi9kaWFsb2cubmV3Lmxlbmd1YWdlcy50bXBsLmh0bWwnLFxuICAgICAgICAgICAgICAgIHBhcmVudDogYW5ndWxhci5lbGVtZW50KGRvY3VtZW50LmJvZHkpLFxuICAgICAgICAgICAgICAgIGNsaWNrT3V0c2lkZVRvQ2xvc2U6IHRydWUsXG4gICAgICAgICAgICAgICAgZnVsbHNjcmVlbjpmYWxzZSxcbiAgICAgICAgICAgICAgICBsb2NhbHM6IHtcbiAgICAgICAgICAgICAgICAgICAgZWxlbTogdm0udHJhbnNsYXRpb24sXG4gICAgICAgICAgICAgICAgICAgIGVkaXQ6IGVkaXRWYWxcbiAgICAgICAgICAgICAgICB9XG4gICAgICAgICAgICB9KTtcbiAgICAgICAgfVxuXG4gICAgICAgIC8vIENvbnRyb2xsZXIgdG8gTW9kYWxcbiAgICAgICAgZnVuY3Rpb24gRGlhbG9nQ29udHJvbGxlcigkc2NvcGUsICRtZERpYWxvZywgZWxlbSwgZWRpdCwgVHJhbnNsYXRpb25TZXJ2aWNlLHRvYXN0cikge1xuICAgICAgICAgICAgLy8gdmFyIHZtbSA9IHRoaXM7XG5cbiAgICAgICAgICAgICRzY29wZS50cmFuc2xhdGlvbiA9IGVsZW07XG4gICAgICAgICAgICAvLyAkc2NvcGUubW9kZWwgPSB7XG4gICAgICAgICAgICAvLyAgICAgZWRpdDogZWRpdFxuICAgICAgICAgICAgLy8gfTtcbiAgICAgICAgICAgICRzY29wZS5lZGl0ID0gZWRpdDtcblxuICAgICAgICAgICAgLy8gdm1tLmVkaXQgPSBlZGl0O1xuICAgICAgICAgICAgLy8gdm1tLmNsb3NlRGlhbG9nID0gY2xvc2VEaWFsb2c7XG4gICAgICAgICAgICAvLyB2bW0uc2F2ZVRyYW5zbGF0aW9uID0gc2F2ZVRyYW5zbGF0aW9uO1xuXG5cbiAgICAgICAgICAgICRzY29wZS5jbG9zZURpYWxvZyA9IGZ1bmN0aW9uIGNsb3NlRGlhbG9nKCkge1xuICAgICAgICAgICAgICAgIGNvbnNvbGUubG9nKCdDZXJyYXIgRGlhbG9nbycpO1xuICAgICAgICAgICAgICAgICRtZERpYWxvZy5jYW5jZWwoKTtcbiAgICAgICAgICAgIH07XG5cbiAgICAgICAgICAgIFxuXG4gICAgICAgICAgICAkc2NvcGUuc2F2ZVRyYW5zbGF0aW9uID0gZnVuY3Rpb24gc2F2ZVRyYW5zbGF0aW9uKCkge1xuICAgICAgICAgICAgICAgIGNvbnNvbGUubG9nKCdJbmZvcm1hdGlvbiBTQVZJTkcnLCAkc2NvcGUudHJhbnNsYXRpb24pO1xuXG4gICAgICAgICAgICAgICAgVHJhbnNsYXRpb25TZXJ2aWNlLnNhdmVMb2NhbGUoJHNjb3BlLnRyYW5zbGF0aW9uKVxuICAgICAgICAgICAgICAgIC50aGVuKGZ1bmN0aW9uKGxvY2F0aW9ucyl7XG4gICAgICAgICAgICAgICAgICAgIC8vIHZtLmxvY2F0aW9ucyA9IGxvY2F0aW9ucztcbiAgICAgICAgICAgICAgICAgICAgdG9hc3RyLnN1Y2Nlc3MoJ1NhdmUgQ29tcGxldGVkIScpO1xuICAgICAgICAgICAgICAgICAgICBjb25zb2xlLmxvZygnR3VhcmRvICcsIGxvY2F0aW9ucyk7XG5cbiAgICAgICAgICAgICAgICAgICAgVHJhbnNsYXRpb25TZXJ2aWNlLmxvYWRMb2NhbGVzKCkudGhlbihmdW5jdGlvbihsb2NhdGlvbnMpe1xuICAgICAgICAgICAgICAgICAgICAgICAgdm0ubG9jYXRpb25zID0gbG9jYXRpb25zO1xuICAgICAgICAgICAgICAgICAgICB9KTtcblxuICAgICAgICAgICAgICAgICAgICAkc2NvcGUuY2xvc2VEaWFsb2coKTtcbiAgICAgICAgICAgICAgICB9KVxuICAgICAgICAgICAgICAgIC5jYXRjaChmdW5jdGlvbiAoKSB7XG4gICAgICAgICAgICAgICAgICAgIGNvbnNvbGUubG9nKCdPY3VycmlvIHVuIGVycm9yIGFsIGd1YXJkYXInKVxuICAgICAgICAgICAgICAgICAgICB0b2FzdHIuZXJyb3IoJ1RyeSBMYXRlJywgJ0Vycm9yJyk7XG4gICAgICAgICAgICAgICAgfSk7XG4gICAgICAgICAgICB9XG4gICAgICAgIH1cblxuXG4gICAgICAgIGZ1bmN0aW9uIGNsZWFuTW9kZWwoKXtcbiAgICAgICAgICAgIHZtLnRyYW5zbGF0aW9uID0ge1xuICAgICAgICAgICAgICAgIGxhYmVsOiAnJyxcbiAgICAgICAgICAgICAgICBkZXNjcmlwdGlvbjogJycsXG4gICAgICAgICAgICAgICAgZW5VUzogJycsXG4gICAgICAgICAgICAgICAgZXNFUzogJycsXG4gICAgICAgICAgICAgICAgZGVERTogJydcbiAgICAgICAgICAgIH1cbiAgICAgICAgfVxuICAgICAgICBcbiAgICB9O1xuXG4gICAgLy9EZWNsYXJvIGNvbnRyb2xsYWRvclxuICAgIGFuZ3VsYXIubW9kdWxlKG1vZHVsZU5hbWUpXG4gICAgICAgIC5jb250cm9sbGVyKCdNYW5hZ2VUcmFuc2xhdGlvbnNDb250cm9sbGVyJywgW1xuICAgICAgICAgICAgJyRzY29wZScsXG4gICAgICAgICAgICBcIiRyb290U2NvcGVcIixcbiAgICAgICAgICAgIFwiVHJhbnNsYXRpb25TZXJ2aWNlXCIsXG4gICAgICAgICAgICBcIiRtZERpYWxvZ1wiLFxuICAgICAgICAgICAgXCJfXCIsXG4gICAgICAgICAgICBcInRvYXN0clwiLFxuICAgICAgICAgICAgTWFuYWdlVHJhbnNsYXRpb25zQ29udHJvbGxlcl0pO1xufSkoKTsiLCIoZnVuY3Rpb24oKSB7XG4gICd1c2Ugc3RyaWN0JztcblxuICBhbmd1bGFyXG4gICAgLm1vZHVsZSgnSGFsbG9DYXNhQWRtaW4nKVxuICAgIC5zZXJ2aWNlKCdUcmFuc2xhdGlvblNlcnZpY2UnLCBUcmFuc2xhdGlvblNlcnZpY2UgKTtcblxuICAvKiogQG5nSW5qZWN0ICovXG4gIGZ1bmN0aW9uIFRyYW5zbGF0aW9uU2VydmljZSAoJHEsIFxuICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAkcmVzb3VyY2UsIFxuICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAkaHR0cCxcbiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgR2VuZXJpY1JFU1RSZXNvdXJjZSwgXG4gICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIGJhY2tlbmRfdXJsLCBcbiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgc2VjdXJpdHlfa2V5KSB7XG4gICAgdmFyIHNlcnZpY2UgPSB7XG4gICAgICAgIGxvYWRMb2NhbGVzOiBsb2FkTG9jYWxlcyxcbiAgICAgICAgc2F2ZUxvY2FsZTogc2F2ZUxvY2FsZVxuICAgIC8vICAgZ2V0Q3VycmVudFBvc2l0aW9uOiBnZXRDdXJyZW50UG9zaXRpb25cbiAgICB9O1xuXG4gICAgdmFyIHJlc291cmNlcyA9IHtcbiAgICAgIGxvY2FsZTogJHJlc291cmNlKGJhY2tlbmRfdXJsICsgXCJsb2NhbGVzXCIsIHt9LCBHZW5lcmljUkVTVFJlc291cmNlKSxcbiAgICAgIHNhdmVMb2NhbGU6ICRyZXNvdXJjZShiYWNrZW5kX3VybCArIFwibG9jYWxlc1wiLCB7fSBcbiAgICAgIClcbiAgICAvLyAgIGV4Y2hhbmdlOiAkcmVzb3VyY2UoYmFja2VuZF91cmwgKyBcImN1cnJlbmN5X2V4Y2hhbmdlX2RhdGFcIiwge30sIEdlbmVyaWNSRVNUUmVzb3VyY2UpXG4gICAgfTtcblxuICAgIHJldHVybiBzZXJ2aWNlO1xuXG4gICAgZnVuY3Rpb24gbG9hZExvY2FsZXMoKSB7XG4gICAgICByZXR1cm4gcmVzb3VyY2VzLmxvY2FsZS5xdWVyeSgpLiRwcm9taXNlO1xuICAgIH1cblxuICAgIGZ1bmN0aW9uIHNhdmVMb2NhbGUoaXRlbSl7XG4gICAgICB2YXIgZGF0YSA9IFtcbiAgICAgICAgeyBcbiAgICAgICAgICBcInBuZW1vbmljXCI6IGl0ZW0ucG5lbW9uaWMsXG4gICAgICAgICAgXCJkZXNjcmlwdGlvblwiOiBpdGVtLmRlc2NyaXB0aW9uLFxuICAgICAgICAgIFwiZW5VU1wiOiBpdGVtLmVuVVMsXG4gICAgICAgICAgXCJlc0VTXCI6IGl0ZW0uZXNFUyxcbiAgICAgICAgICBcImRlREVcIjogaXRlbS5kZURFXG4gICAgICAgIH1cbiAgICAgIF1cblxuICAgICAgdmFyIHJlcSA9IHtcbiAgICAgICAgbWV0aG9kOiAnUE9TVCcsXG4gICAgICAgIHVybDogYmFja2VuZF91cmwgKyBcImxvY2FsZXMvXCIsXG4gICAgICAgIGhlYWRlcnM6IHtcbiAgICAgICAgICAnQ29udGVudC1UeXBlJzonYXBwbGljYXRpb24vanNvbicsXG4gICAgICAgICAgJ3NlY3VyaXR5LWtleSc6IHNlY3VyaXR5X2tleVxuICAgICAgICB9LFxuICAgICAgICBkYXRhOiBkYXRhXG4gICAgICB9XG5cbiAgICAgIHJldHVybiAkaHR0cChyZXEpO1xuXG4gICAgICAvLyByZXR1cm4gJGh0dHAoYmFja2VuZF91cmwgKyBcImxvY2FsZXMvXCIsIHtcbiAgICAgIC8vICAgICAgICAgXCJwbmVtb25pY1wiOiBpdGVtLnBuZW1vbmljLFxuICAgICAgLy8gICAgICAgICBcImRlc2NyaXB0aW9uXCI6IGl0ZW0uZGVzY3JpcHRpb24sXG4gICAgICAvLyAgICAgICAgIFwiZW5VU1wiOiBpdGVtLmVuVVMsXG4gICAgICAvLyAgICAgICAgIFwiZXNFU1wiOiBpdGVtLmVzRVMsXG4gICAgICAvLyAgICAgICAgIFwiZGVERVwiOiBpdGVtLmRlREVcbiAgICAgIC8vICAgICAgIH0sIHtcbiAgICAgIC8vICAgICAgIGhlYWRlcnM6IHtcbiAgICAgIC8vICAgICAgICAgJ0NvbnRlbnQtVHlwZSc6ICdhcHBsaWNhdGlvbi9qc29uJyxcbiAgICAgIC8vICAgICAgICAgJ3NlY3VyaXR5LWtleSc6IHNlY3VyaXR5X2tleVxuICAgICAgLy8gICAgICAgfVxuICAgICAgLy8gfSk7XG5cblxuICAgICAgLy8gdmFyIHJlc291cmNlID0gJHJlc291cmNlKGJhY2tlbmRfdXJsICsgXCJsb2NhbGVzXCIsIHt9LCB7XG4gICAgICAvLyAgICdzYXZlJzoge1xuICAgICAgLy8gICAgIG1ldGhvZDogJ1BPU1QnLFxuICAgICAgLy8gICAgIGhlYWRlcnM6IHtcbiAgICAgIC8vICAgICAgICdDb250ZW50LVR5cGUnOiAnYXBwbGljYXRpb24vanNvbiA7IGNoYXJzZXQ9VVRGLTgnLFxuICAgICAgLy8gICAgICAgJ3NlY3VyaXR5LWtleSc6IHNlY3VyaXR5X2tleVxuICAgICAgLy8gICAgIH1cbiAgICAgIC8vICAgfVxuICAgICAgLy8gfSk7ICBcbiAgICAgIC8vIHZhciBEaXJlY3RvcnlBcGkgPSAkcmVzb3VyY2UoYmFja2VuZF91cmwsIG51bGwsIHtcbiAgICAgIC8vICAgICBtb3ZlOiB7XG4gICAgICAvLyAgICAgICAgIHVybDogYmFja2VuZF91cmwsXG4gICAgICAvLyAgICAgICAgIGhlYWRlcnM6IHsgJ0NvbnRlbnQtVHlwZSc6ICdhcHBsaWNhdGlvbi94LXd3dy1mb3JtLXVybGVuY29kZWQ7IGNoYXJzZXQ9VVRGLTgnfSxcbiAgICAgIC8vICAgICAgICAgdHJhbnNmb3JtUmVxdWVzdDogZnVuY3Rpb24gKHBhcmFtKSB7XG4gICAgICAvLyAgICAgICAgICAgICByZXR1cm4gJC5wYXJhbShwYXJhbSk7XG4gICAgICAvLyAgICAgICAgIH0sXG4gICAgICAvLyAgICAgICAgIG1ldGhvZDogJ1BPU1QnXG4gICAgICAvLyAgICAgfSxcbiAgICAgIC8vIH0pO1xuXG4gICAgICAvLyByZXR1cm4gcmVzb3VyY2Uuc2F2ZShpdGVtKS4kcHJvbWlzZTtcbiAgICB9XG5cbiAgICAvLyBmdW5jdGlvbiBnZXRDdXJyZW50UG9zaXRpb24oKSB7XG4gICAgLy8gICByZXR1cm4gJHEoZnVuY3Rpb24gKHJlc29sdmUsIHJlamVjdCkge1xuICAgIC8vICAgICBpZiAoISR3aW5kb3cubmF2aWdhdG9yLmdlb2xvY2F0aW9uKSB7XG4gICAgLy8gICAgICAgcmVqZWN0KCdHZW9sb2NhdGlvbiBub3Qgc3VwcG9ydGVkLicpO1xuICAgIC8vICAgICB9IGVsc2Uge1xuICAgIC8vICAgICAgICR3aW5kb3cubmF2aWdhdG9yLmdlb2xvY2F0aW9uLmdldEN1cnJlbnRQb3NpdGlvbihcbiAgICAvLyAgICAgICAgIGZ1bmN0aW9uIChwb3NpdGlvbikge1xuICAgIC8vICAgICAgICAgICByZXNvbHZlKHBvc2l0aW9uKTtcbiAgICAvLyAgICAgICAgIH0sXG4gICAgLy8gICAgICAgICBmdW5jdGlvbiAoZXJyb3IpIHtcbiAgICAvLyAgICAgICAgICAgcmVqZWN0KGVycm9yKTtcbiAgICAvLyAgICAgICAgIH0pO1xuICAgIC8vICAgICB9XG4gICAgLy8gICB9KTtcbiAgICAvLyB9XG5cbiAgfVxuXG59KSgpOyJdfQ==
