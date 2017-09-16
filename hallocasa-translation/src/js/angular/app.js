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