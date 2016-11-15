echo @off
For /F "tokens=1* delims==" %%A IN (my-hc-settings.properties) DO (
    IF "%%A"=="api_doc_tomcat_base" set api_doc_tomcat_base=%%B
)
cd /d %api_doc_tomcat_base%/bin
set CATALINA_HOME = %api_doc_tomcat_base%
call catalina stop
call catalina start
cmd /k cd %~dp0
pause