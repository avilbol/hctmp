For /F "tokens=1* delims==" %%A IN (my-hc-settings.properties) DO (
    IF "%%A"=="war_location" set war_location=%%B
    IF "%%A"=="glassfish_base" set glassfish_base=%%B
    IF "%%A"=="glassfish_domain" set glassfish_domain=%%B
    IF "%%A"=="glassfish_admin_port" set glassfish_admin_port=%%B
    IF "%%A"=="hc_java_home" set hc_java_home=%%B
)
IF NOT "%hc_java_home%" == "" set JAVA_HOME=%hc_java_home%
echo %JAVA_HOME%
mvn -f %HPORMIG_LOC%/hallocasamig-endpoint/pom.xml clean install -Dwar.location=%war_location% -Dglassfish.base=%glassfish_base% -Dglassfish.domain=%glassfish_domain% -Dglassfish.admin.port=%glassfish_admin_port%
