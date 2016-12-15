@Echo off
For /F "tokens=1* delims==" %%A IN (my-hc-settings.properties) DO (
    IF "%%A"=="war_location" set war_location=%%B
    IF "%%A"=="glassfish_base" set glassfish_base=%%B
    IF "%%A"=="glassfish_domain" set glassfish_domain=%%B
    IF "%%A"=="glassfish_admin_port" set glassfish_admin_port=%%B
    IF "%%A"=="glassfish_app_port" set glassfish_app_port=%%B
    IF "%%A"=="hc_java_home" set hc_java_home=%%B
    IF "%%A"=="hc_home" set hc_home=%%B
    IF "%%A"=="logs_dir" set logs_dir=%%B
    IF "%%A"=="mysql_jdbc" set mysql_jdbc=%%B
    IF "%%A"=="mysql_pass" set mysql_pass=%%B
    IF "%%A"=="hc_version" set hc_version=%%B
    IF "%%A"=="files_property_images_rootpath" set files_property_images_rootpath=%%B
    IF "%%A"=="files_user_images_rootpath" set files_user_images_rootpath=%%B
)
call copy "my-hc-settings.properties" "init_setup/my-hc-settings.properties"
call init_setup/do
IF %errorlevel% NEQ 0 GOTO Error
IF NOT "%hc_java_home%" == "" set JAVA_HOME=%hc_java_home%
@Echo on
call mvn -f %hc_home%/hallocasamig/pom.xml clean package ^
 -U -Dwar.output.location=%war_location% ^
 -Dglassfish.base=%glassfish_base% -Dglassfish.domain=%glassfish_domain% ^
 -Dglassfish.admin.port=%glassfish_admin_port% -Dlogsdir=%logs_dir% ^
 -Dglassfish.app.port=%glassfish_app_port% ^
 -Dapp.version=%hc_version% ^
 -Dmysql.jdbc=%mysql_jdbc% ^
 -Dmysql.pass=%mysql_pass% ^
 -Dfiles.propertyImages.rootPath=%files_property_images_rootpath% ^
 -Dfiles.userImages.rootPath=%files_user_images_rootpath%
GOTO Success
:Error
cmd /k
:Success
cmd /k