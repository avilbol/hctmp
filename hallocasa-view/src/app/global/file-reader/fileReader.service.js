(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .service('FileReaderService', FileReaderService);

  /** @ngInject */
  function FileReaderService ($q) {
    var service = {
      readFile: readFile,
      readFiles: readFiles
    };

    return service;

    function readFile(file) {
      return $q(function(resolve, reject) {
        try {
          var reader = new FileReader();
          reader.onloadend = function() {
            resolve(reader.result);
          };
          reader.readAsDataURL(file);
        }
        catch(error) {
          reject(error);
        }
      });
    }

    function readFiles(files) {
      var promises = _.map(files, function (file) {
        return readFile(file);
      });
      return $q.all(promises);
    }

  }
})();
