(function() {
	'use strict';

	angular
		.module('HalloCasa.global')
		.controller('ImageUploaderController', ImageUploaderController);

	function ImageUploaderController($mdDialog, $scope, ImageValidatorService, toastr, translateFilter) {
		var vm = this;

    vm.closeDialog = closeDialog;
    vm.imageLoaded = imageLoaded;
    vm.handleFileSelect = handleFileSelect;
    vm.myCroppedImage = null;
    vm.submitButton = true;

    var isImageType = function(type,name){
      return (type.match('image.*') || name.match(/\.(gif|png|jpe?g)$/i)) ? true : false;
    };

    function handleFileSelect(event) {
      var file = event.currentTarget.files[0];
      var type =  file.type;
      var name =  file.name;
      if(isImageType(type,name)){
        vm.submitButton = false;
        var reader = new FileReader();
        reader.onload = function (evt) {
          $scope.$apply(function (vm) {
            vm.myImage = evt.target.result;
          });
        };
        reader.readAsDataURL(file);
      }  else {
        toastr.warning(
          translateFilter("Image.Edition.Invalid.NotImage")
        );
      }      
    }

    function closeDialog(){
      $mdDialog.cancel();
    }

    function imageLoaded() {
      $mdDialog.hide(vm.myCroppedImage);
    }

	}
})();
