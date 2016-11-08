echo @off
For /F "tokens=1* delims==" %%A IN (my-hc-settings.properties) DO (
    IF "%%A"=="glassfish_app_port" set glassfish_app_port=%%B
    IF "%%A"=="hc_home" set hc_home=%%B
    IF "%%A"=="api_doc_tomcat_base" set api_doc_tomcat_base=%%B
)
call mvn -f %hc_home%/hallocasamig-webapi/pom.xml clean package -U ^
 -Dglassfish.app.port=%glassfish_app_port% 
cd /d %api_doc_tomcat_base%/bin
call catalina stop
call catalina start
cd %~dp0