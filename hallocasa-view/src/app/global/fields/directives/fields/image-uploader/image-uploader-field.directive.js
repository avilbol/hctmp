(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .directive('imageUploaderField', imageUploaderField);

  function imageUploaderField(FileReaderService, toastr, $log) {
    return {
      restrict: 'EA',
      templateUrl: "app/global/fields/directives/fields/image-uploader/image-uploader-field.html",
      scope: {
        fieldInformation: "="
      },
      link: function (scope) {
        scope.fieldInformation.fieldValueList = scope.fieldInformation.fieldValueList ? scope.fieldInformation.fieldValueList : [];
        scope.rawImages = [];
        var filesKeys = [];
        var primaryImage;
        scope.assignAsPrimaryImage = assignAsPrimaryImage;

        function parseNewImages(amountImages) {
          var imagesList = _.rest(scope.rawImages,scope.rawImages.length - amountImages);
          var bloblList = _.map(imagesList, _.property('lfFile'));


          FileReaderService.readFiles(bloblList)
            .then(function (imagesEncoded) {
              _.each(imagesEncoded, function (imageEncoded, imageIndex) {
                var imageData = {
                  data1: {},
                  data2: { boolVal: false }
                };
                var fileIndex = scope.rawImages.length - amountImages + imageIndex;
                imageData.data1.strVal = imageEncoded;
                scope.fieldInformation.fieldValueList.push(imageData);
                filesKeys.push(scope.rawImages[fileIndex].key);
              });

            })
            .catch(function (error) {
              $log.debug(error);
              //TODO: Traducción de mensaje de error
              toastr.info("Error al procesar imágen");

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
            filesKeys.splice(deleteFile, 1);
            primaryImage = primaryImage === deleteFile ? undefined : primaryImage;
          }
        }

        function loadPrimaryImage() {
          var fieldValueList = scope.fieldInformation.fieldValueList;

          if(fieldValueList && fieldValueList.length > 0){
            var isPrimaryImage;
            _.find(fieldValueList, function (fieldValue, valueIndex) {
              isPrimaryImage = fieldValue.data2.boolVal;
              if(isPrimaryImage){
                primaryImage = valueIndex;
              }
              return isPrimaryImage;
            });
          }
        }

        function assignAsPrimaryImage(image) {
          var imageIndex = filesKeys.indexOf(image.key);
          if(_.isNumber(primaryImage)){
            scope.fieldInformation.fieldValueList[primaryImage].data2.boolVal = false;
          }
          scope.fieldInformation.fieldValueList[imageIndex].data2.boolVal = true;
          primaryImage = imageIndex;
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

        watchRawImages();
        loadPrimaryImage();
      }
    };
  }
})();
