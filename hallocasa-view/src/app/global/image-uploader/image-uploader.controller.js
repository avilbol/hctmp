(function() {
	'use strict';

	angular
		.module('HalloCasa.global')
		.controller('ImageUploaderController', ImageUploaderController);

	function ImageUploaderController($mdDialog, $scope) {
		var vm = this;

    vm.closeDialog = closeDialog;
    vm.imageLoaded = imageLoaded;
    vm.handleFileSelect = handleFileSelect;
    vm.myCroppedImage = null;

    function handleFileSelect(event) {
      var file = event.currentTarget.files[0];
      var reader = new FileReader();
      reader.onload = function (evt) {
        $scope.$apply(function (vm) {
          vm.myImage = evt.target.result;
        });
      };
      reader.readAsDataURL(file);
    }

    function closeDialog(){
      $mdDialog.cancel();
    }

    function imageLoaded() {
      $mdDialog.hide(vm.myCroppedImage);
    }

	}
})();
