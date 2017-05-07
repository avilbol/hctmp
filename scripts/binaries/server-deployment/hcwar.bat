@echo off
if "%1"=="" (
   echo the source environment is required.
   goto:end
) 

SET env=%2
SET devenv=%2
IF "%2"=="" (
  SET env=qa
  SET devenv=qa
)
IF "%2"=="dev" (SET devenv=%2.%1)

For /F "tokens=1* delims==" %%A IN (%~dp0\deploy.properties) DO (
    IF "%%A"=="%1.developroot" set developroot=%%B
    IF "%%A"=="%1.qatarget" set qatarget=%%B
    IF "%%A"=="%1.prodtarget" set prodtarget=%%B
    IF "%%A"=="%1.devtarget" set devtarget=%%B
    IF "%%A"=="%1.javahome" set javahome=%%B
    IF "%%A"=="%devenv%.propertyimagespath" set propertyimagespath=%%B
    IF "%%A"=="%devenv%.userimagespath" set userimagespath=%%B
    IF "%%A"=="%devenv%.logspath" set logspath=%%B
)

echo 'Develop Root: %developroot%'
echo 'Qa target: %qatarget%'
echo 'Prod target: %prodtarget%'
echo 'Dev target: %devtarget%'
echo 'Java home: %javahome%'
echo 'Property images path: %propertyimagespath%'
echo 'User images path: %userimagespath%'
echo 'Logs path: %logspath%'

IF "%2"=="" (SET warloc=%qatarget%)
IF "%2"=="prod" (SET warloc=%prodtarget%)
IF "%2"=="qa" (SET warloc=%qatarget%)
IF "%2"=="dev" (SET warloc=%devtarget%)

SET JAVA_HOME=%javahome%
call mvn -f %developroot%\hallocasamig\pom.xml clean package resources:resources -U ^
 -Dwar.output.location=%warloc% ^
 -Dglassfish.base=/usr/local/hallocasamig/api-server ^
 -Dglassfish.domain=hallocasa-mig -Dglassfish.admin.port=64646 ^
 -Dlogsdir=/usr/local/hallocasamig/logs-prod ^
 -Dglassfish.app.port=64647 ^
 -Dmysql.jdbc=jdbc:mysql://localhost:3306/hallocasaappmig ^
 -Dmysql.pass=htGp64SK ^
 -Dfiles.propertyImages.rootPath=%propertyimagespath% ^
 -Dfiles.userImages.rootPath=%userimagespath% ^
 -Dapidoc.war.location=%warloc%
call copy /y %developroot%\hallocasamig-endpoint\pom-local.xml %developroot%\hallocasamig-endpoint\pom.xml
cd /d %warloc%
call move /y hallocasamig-endpoint-1.1.0.war hallocasamig-endpoint-%env%-1.1.0.war
cd /d %cd%
:end
pause
