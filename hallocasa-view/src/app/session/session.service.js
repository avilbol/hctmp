(function() {
  'use strict';

  angular
    .module('HalloCasa.session')
    .service('SessionService', SessionService);

  /** @ngInject */
  function SessionService($mdMedia, $mdDialog, $document, $auth, $q, GenericRESTResource, backend_url ,$resource,
                          ApplicationCredentials, localStorageService, $intercom, WootricService) {
    var service = {
      login: login,
      logout: logout,
      sendRecoveryRequest: sendRecoveryRequest,
      sendRecoveryPassword: sendRecoveryPassword,
      activateUser: activateUser,
      validateToken: validateToken,
      getCurrentUser: getCurrentUser,
      launchLoginDialog: launchLoginDialog,
      validateActiveSession: validateActiveSession,
      isAuthenticated: isAuthenticated
    };

    var currentUser;

    var resource = {
      sendEmail: $resource(backend_url + "password_recovery/send_email", {}, GenericRESTResource),
      updatePassword: $resource(backend_url + "password_recovery/update_password", {}, GenericRESTResource),
      validateToken: $resource(backend_url + "password_recovery/validate_token", {}, GenericRESTResource),
      activateUser: $resource(backend_url + "user/activate_user", {}, GenericRESTResource)
    };

    return service;

    function login(userCredentials) {
      return $q(function (resolve, reject) {
        _.extend(userCredentials, ApplicationCredentials);

        $auth.login(userCredentials, {
          headers: {'Content-Type': 'application/x-www-form-urlencoded'},
          transformRequest: function(obj) {
            var str = [];
            for(var p in obj)
              str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            return str.join("&");
          }
        })
          .then(function (auth) {
            $auth.setToken(auth.data.securityToken.tokenValue);
            setCurrentUser(auth.data.user);
            setSessionExpiration(auth.data.securityToken);

            $intercom.boot(currentUser);
            WootricService.boot(currentUser);
            resolve(currentUser);
          })
          .catch(function(error){
            reject(error);
          })
      });
    }

    function logout() {
      if(!isAuthenticated()){
        return {};
      }
      clearCurrentUser();
      localStorageService.remove("sessionExpiration");
      $auth.logout();
      $intercom.shutdown();
    }

    function setSessionExpiration(securityToken) {
      var expiresIn = securityToken.expiresIn;
      var registered = securityToken.registered;
      var expirationDate = new Date(registered).setMilliseconds(expiresIn);
      localStorageService.set("sessionExpiration", expirationDate);
    }

    function setCurrentUser(userData) {
      currentUser = userData;
      localStorageService.set("currentUser", userData);
    }

    function getCurrentUser() {
      if(!isAuthenticated()){
        return {};
      }
      currentUser = currentUser ? currentUser : localStorageService.get("currentUser");
      return currentUser;
    }

    function clearCurrentUser() {
      currentUser = undefined;
      localStorageService.remove("currentUser");
    }

    function sendRecoveryRequest(email) {
      return resource.sendEmail.show({"email": email}).$promise;
    }

    function sendRecoveryPassword(password, token) {
      var recoveryData = {
        newPassword: password,
        passwordRecoveryToken: token.passwordRecoveryToken
      };
      return resource.updatePassword.create(recoveryData).$promise;
    }

    function validateToken(token) {
      return resource.validateToken.show({"password_recovery_token": token}).$promise;
    }

    function activateUser(email, activationKey) {
      return resource.activateUser.show({"email": email, "activation_key": activationKey}).$promise;
    }

    function validateActiveSession(message) {
      var activeSession = isAuthenticated();

      if(!activeSession) {
        var options = {
          description: message,
          allowClose: false,
          escapeToClose: false
        };
        launchLoginDialog(options)
      }

      return activeSession;
    }

    function isAuthenticated() {
      if(!$auth.isAuthenticated()){
        return false;
      }

      var sessionExpiration = localStorageService.get("sessionExpiration");

      if(!sessionExpiration){
        return false;
      }

      return (Date.now() < sessionExpiration);
    }

    function launchLoginDialog(options) {
      options = options ? options : {};
      options.escapeToClose = _.isUndefined(options.escapeToClose) ? true : options.escapeToClose;
      var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'));
      return $mdDialog.show({
        controller: "LoginController",
        controllerAs: "vm",
        templateUrl: 'app/session/login/login.html',
        parent: $document.body,
        locals: {
          description: options.description,
          allowClose: options.allowClose
        },
        targetEvent: options.targetEvent,
        clickOutsideToClose: options.clickOutsideToClose,
        escapeToClose: options.escapeToClose,
        fullscreen: useFullScreen
      });
    }
  }
})();
