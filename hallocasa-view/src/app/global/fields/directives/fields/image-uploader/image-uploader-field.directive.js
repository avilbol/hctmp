(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('imageUploaderField', imageUploaderField);

  function imageUploaderField(FileReaderService, toastr, $log, $timeout, translateFilter, ImageValidatorService) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/image-uploader/image-uploader-field.html",
      scope: {
        fieldInformation: "=",
        additionalParameters: "=?",
        form: "=?",
        readonly: "=?",
        maxWidth: "="
      },
      link: function (scope) {
        scope.fieldName = scope.$id;
        scope.fieldInformation.fieldValueList = scope.fieldInformation.fieldValueList ? scope.fieldInformation.fieldValueList : [];
        scope.rawImages = [];
        var filesKeys = [];
        var primaryImage;
        var previewFrame = '.lf-ng-md-file-input-thumbnails .lf-ng-md-file-input-frame';
        var previewContainerFrame = '.lf-ng-md-file-input-frame';
        var previewTitle = '.lf-ng-md-file-input-x';
        scope.assignAsPrimaryImage = assignAsPrimaryImage;

        function applyValidations() {
          if (scope.fieldInformation.validations) {
            scope.fieldInformation.validations += ", mimetype, filesize";
            scope.requireOnce = scope.fieldInformation.validations.includes("requireOnce");
          }
        }

        function parseNewImages(amountImages) {

          var imagesList = _.rest(scope.rawImages,scope.rawImages.length - amountImages);
          var blobList = _.map(_.reject(imagesList, _.property('isRemote')), _.property('lfFile'));

          if(_.isEmpty(blobList)){
            _.each(scope.rawImages, function (rawImage) {
              filesKeys.push(rawImage.key);
            })
          }


          FileReaderService.readFiles(blobList)
            .then(function (imagesEncoded) {
              _.each(imagesEncoded, function (imageEncoded, imageIndex) {
                var fileIndex = scope.rawImages.length - amountImages + imageIndex;

                var validImage = ImageValidatorService.validateBase64(imageEncoded);

                if(validImage){
                  var imageData = {
                    data1: {},
                    data2: { boolVal: false }
                  };

                  imageData.data1.strVal = imageEncoded;
                  scope.fieldInformation.fieldValueList.push(imageData);
                  filesKeys.push(scope.rawImages[fileIndex].key);

                  // check image width, this is because image.onload is async
                  var image = new Image();
                  var width = 0;
                  image.src = imageEncoded;
                  image.onload = function() {
                    width = this.width;
                    if(width < scope.maxWidth){
                      toastr.warning(translateFilter("Error.invalidorSizeimage") + scope.maxWidth + 'px');
                      scope.api.removeByName(scope.rawImages[fileIndex].lfFileName);
                    }
                  };
                }
                else{
                  toastr.warning(translateFilter("Error.invalidoremptyimage"));
                  scope.api.removeByName(scope.rawImages[fileIndex].lfFileName);
                }

              });
              if(_.isUndefined(primaryImage) && !_.isEmpty(scope.rawImages)){
                var firstImageIndex = scope.rawImages.length - amountImages;
                assignAsPrimaryImage(scope.rawImages[firstImageIndex]);
              }
            })
            .catch(function (error) {
              $log.debug(error);
              toastr.info(translateFilter("Error.whenprocessingimage"));
              _.each(imagesList, function (image) {
                scope.api.removeByName(image.lfFileName);
              });
            });
        }

        function detectAndDeleteImage() {
          var deleteFile;
          _.find(filesKeys, function (fileKey, fileIndex) {
            if(!scope.rawImages[fileIndex] || fileKey !== scope.rawImages[fileIndex].key){
              deleteFile = fileIndex;
              return true;
            }
          });

          if(!_.isUndefined(deleteFile)){
            scope.fieldInformation.fieldValueList.splice(deleteFile, 1);
            var totalImages = scope.fieldInformation.fieldValueList.length;
            filesKeys.splice(deleteFile, 1);
            primaryImage = primaryImage === deleteFile ? undefined : primaryImage;
            primaryImage = primaryImage > deleteFile ? primaryImage - 1 : primaryImage;
            primaryImage = _.isUndefined(primaryImage) && totalImages ? totalImages - 1 : primaryImage;
            primaryImage = primaryImage === deleteFile ? 0 : primaryImage;

            setPreviewAsPrimary(primaryImage);
          }
        }

        function unsetPreviewAsPrimary(primaryImage) {
          var imageFrame = angular.element(previewFrame)[primaryImage];
          if(!imageFrame){
            $timeout(function () {
              unsetPreviewAsPrimary(primaryImage);
            },500);
          }
          else {
            var titleFrame = angular.element(imageFrame).find(previewTitle);
            angular.element(imageFrame).removeClass('primary-image-container');
            // var containerFrame = angular.element(imageFrame).find('.lf-ng-md-file-input-frame');
            // containerFrame.removeClass("primary-image-container");
            titleFrame.removeClass("primary-image");
            titleFrame.find(".titleText").html("");
          }
        }

        function setPreviewAsPrimary(primaryImage) {
          var imageFrame = angular.element(previewFrame)[primaryImage];
          if(!imageFrame){
            $timeout(function () {
              setPreviewAsPrimary(primaryImage);
            },500);
          }
          else {
            var titleFrame = angular.element(imageFrame).find(previewTitle);
            angular.element(imageFrame).addClass('primary-image-container');
            // console.log('conainer-frame');
            // console.log(containerFrame);
            
            titleFrame.addClass("primary-image");
            // containerFrame.addClass("primary-image-container");
            titleFrame.find(".titleText").html("("+translateFilter('Properties.multi.image.button')+") ");
          }
        }

        function loadPrimaryImage() {
          var isPrimaryImage;
          var imageFound = _.find(scope.fieldInformation.fieldValueList, function (fieldValue, valueIndex) {
            isPrimaryImage = fieldValue.data2.boolVal;
            if(isPrimaryImage){
              primaryImage = valueIndex;
            }
            return isPrimaryImage;
          });

          if(!imageFound){
            $log.debug("Imagen primaria no encontrada!",scope.fieldInformation.fieldValueList);
          }
          else{
            var totalImages = scope.fieldInformation.fieldValueList.length;
            var deleteWatcher = scope.$watch('rawImages.length',function(imagesLoaded){
              var loadFinish = (imagesLoaded === totalImages);
              if(loadFinish){
                deleteWatcher();
                $timeout(setPreviewAsPrimary(primaryImage),1000);
              }
            });
          }
        }

        function assignAsPrimaryImage(image) {
          if(_.isNumber(primaryImage)){
            unsetPreviewAsPrimary(primaryImage);
            scope.fieldInformation.fieldValueList[primaryImage].data2.boolVal = false;
          }
          var imageIndex = filesKeys.indexOf(image.key);
          scope.fieldInformation.fieldValueList[imageIndex].data2.boolVal = true;
          primaryImage = imageIndex;
          setPreviewAsPrimary(primaryImage);
        }

        function watchRawImages() {
          var deleteWatcher = scope.$watch('rawImages.length',function(newVal,oldVal){
            if(newVal > oldVal){
              parseNewImages(newVal - oldVal);
            }
            if(newVal < oldVal){
              detectAndDeleteImage();
            }
          });

          scope.$on("$destroy",deleteWatcher);
        }

        function loadImages(){
          var fieldValueList = scope.fieldInformation.fieldValueList;

          if(fieldValueList && fieldValueList.length > 0){
            loadPrimaryImage();
            $timeout(function(){
              _.each(fieldValueList,function (fieldValue, imageIndex) {
                var urlPrefix = scope.fieldInformation.imageURLPrefix;
                urlPrefix = urlPrefix ? scope.additionalParameters[urlPrefix] : "";
                var imageURL = urlPrefix + fieldValue.data1.strVal;

                scope.api.addRemoteFile(imageURL,'image-'+imageIndex,'image');
              });
            });
          }
        }

        watchRawImages();
        loadImages();
        applyValidations();
      }
    };
  }
})();
