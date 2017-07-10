'use strict';

var argv = require('yargs').argv;
var gulp = require('gulp');
var gulpNgConfig = require('gulp-ng-config');
var gutil = require("gulp-util");

gulp.task('environment-config', function () {
  var environment;
  if(argv.env && (argv.env === "qa" || argv.env === "prod")){
    environment = argv.env;
  }
  else{
    environment = "qa";
  }

  gutil.log("Configuring application for: " + environment);

  gulp.src('environment.constants.json')
    .pipe(gulpNgConfig('HalloCasa.enviroment.constants', {
      environment: environment
    }))
    .pipe(gulp.dest('./src/app'));
});
