(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .service('ImageValidatorService', ImageValidatorService);

  /** @ngInject */
  function ImageValidatorService ($log) {
    var service = {
      validateBase64: validateBase64
    };

    return service;

    function validateBase64(base64) {
      try {
        var rgb = getAverageRGB(base64);
        var sumRGB = rgb.r + rgb.b + rgb.g;
        return sumRGB !== 765 ? base64 : false;
      }
      catch (err){
        $log.log("Imagen invalida: ", base64, err);
        return false;
      }
    }

    function getAverageRGB(src) {
      var imgEl = new Image();
      imgEl.src = src;

      var blockSize = 1, // visit every pixels
        defaultRGB = {r:255,g:255,b:255}, // for non-supporting envs
        canvas = document.createElement('canvas'),
        context = canvas.getContext && canvas.getContext('2d'),
        data, width, height,
        i = -4,
        length,
        rgb = {r:0,g:0,b:0},
        count = 0;

      if (!context) {
        return defaultRGB;
      }

      height = canvas.height = imgEl.naturalHeight || imgEl.offsetHeight || imgEl.height;
      width = canvas.width = imgEl.naturalWidth || imgEl.offsetWidth || imgEl.width;

      if(height === 0 || width === 0){
        return defaultRGB;
      }

      context.drawImage(imgEl, 0, 0);

      try {
        data = context.getImageData(0, 0, width, height);
      } catch(e) {
        return defaultRGB;
      }

      length = data.data.length;

      while ( (i += blockSize * 4) < length ) {
        ++count;
        rgb.r += data.data[i];
        rgb.g += data.data[i+1];
        rgb.b += data.data[i+2];
      }

      // ~~ used to floor values
      rgb.r = ~~(rgb.r/count);
      rgb.g = ~~(rgb.g/count);
      rgb.b = ~~(rgb.b/count);

      return rgb;

    }
  }
})();
