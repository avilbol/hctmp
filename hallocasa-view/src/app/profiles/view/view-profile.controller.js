(function() {
  'use strict';

  angular
    .module('HalloCasa.profiles')
    .controller('ViewProfileController', ViewProfileController);

  /** @ngInject */
  function ViewProfileController(ProfilesService, $location, translateFilter, toastr, $log, LocaleService, Mailto, $window) {
    var vm = this;
    vm.openDialogRenren = openDialogRenren;
    vm.sharedMailInfo = '';
    vm.sharedURL = '';
    vm.sharedURLWhatsApp = '';
    vm.textWhatsApp = '';

    function openDialogRenren(){
      var left = Math.round((screen.width/2)-(600/2));
      var top = Math.round((screen.height/2)-(600/2));
      var url = 'http://widget.renren.com/dialog/share?resourceUrl=' + vm.sharedURL + '&title=' + translateFilter("Profile.Shared.TextInfo") + '&lang=' + LocaleService.getCurrentLenguage();
      $window.open(url,'popup','width=600,height=600' + ', top=' + top + ', left=' + left); 
      return false;
    }

    function sharedEmailInfo() {
      var newPathProfileEn = $location.host() + '/profile?id=' + $location.search().id + '&lang=en';
      var newPathProfileEs = $location.host() + '/profile?id=' + $location.search().id + '&lang=es';
      var newPathProfileDe = $location.host() + '/profile?id=' + $location.search().id + '&lang=de';
      var options = {
        subject: "HalloCasa",
        body: "Check out this real estate expert on HalloCasa! " + newPathProfileEn + "\n" +
              "Sehen Sie diesen Immobilienexperten auf HalloCasa! " + newPathProfileDe + "\n" +
              "Mira este experto inmobiliario en HalloCasa! " + newPathProfileEs
      };

      vm.sharedMailInfo = Mailto.url('', options);
    }

    function loadURLShared() {
      var url = $location.host() + '/profile?id=' + $location.search().id + '&lang=' + LocaleService.getCurrentLenguage();
      vm.sharedURL = url;

      vm.textWhatsApp = translateFilter("Profile.Shared.TextInfo") + ': ';
      var urlWs = 'whatsapp://send?text=' + encodeURIComponent(vm.textWhatsApp) + '%0A' + encodeURIComponent(vm.sharedURL);
      vm.sharedURLWhatsApp = urlWs;
    }

    function loadProfile() {
      var profileID = $location.search().id;
      if(!profileID){
        $location.url("/profile/browser");
      }
      else{
        ProfilesService.loadProfile(profileID)
          .then(function (data) {
            data = ProfilesService.validateUserData(data);
            vm.userData = data;
            console.log(vm.userData.profile.userDescriptions);
            _.find(vm.userData.profile.userDescriptions, function (description) {
              if(description.language.id === data.profile.mainLanguage.id){
                vm.selectedDescription = description;
                loadURLShared();
                sharedEmailInfo();
                return true;
              }
            });
            if(!vm.selectedDescription){
              $log.warn("No se ha encontrado el lenguaje principal para el perfil");
              vm.selectedDescription = _.first(vm.userData.profile.userDescriptions);
            }
          })
          .catch(function (error) {
            if(error.status === 404){
              toastr.error(
                translateFilter("Warning.usernotfound"));
            }
            if(error.status === 500){
              toastr.error(
                translateFilter("Error.whenloadinguser"));
            }
            $location.url("/profile/browser");
          });
      }
    }

    loadProfile();
  }
})();
