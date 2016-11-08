echo @off
For /F "tokens=1* delims==" %%A IN (my-hc-settings.properties) DO (
    IF "%%A"=="war_location" set war_location=%%B
    IF "%%A"=="glassfish_base" set glassfish_base=%%B
    IF "%%A"=="glassfish_user" set glassfish_user=%%B
    IF "%%A"=="glassfish_domain" set glassfish_domain=%%B
    IF "%%A"=="glassfish_admin_port" set glassfish_admin_port=%%B
    IF "%%A"=="hc_java_home" set hc_java_home=%%B
    IF "%%A"=="hc_home" set hc_home=%%B
    IF "%%A"=="hc_version" set hc_version=%%B
    IF "%%A"=="logs_dir" set logs_dir=%%B
)
cd /d %glassfish_base%/bin
set glassfish_domain_root=%glassfish_base%/domains/%glassfish_domain%
set password_file=%glassfish_domain_root%/config/domain-passwords
call asadmin --user=%glassfish_user% --passwordfile=%password_file% --port=%glassfish_admin_port% --host=localhost undeploy hallocasamig-endpoint-%hc_version%
call asadmin --user=%glassfish_user% --passwordfile=%password_file% --port=%glassfish_admin_port% --host=localhost stop-domain %glassfish_domain%
IF %ERRORLEVEL% NEQ 0 GOTO Finally
rd /s /q "%glassfish_domain_root%/applications/hallocasamig-endpoint-%hc_version%"
rd /s /q "%glassfish_domain_root%/osgi-cache"
rd /s /q "%glassfish_domain_root%/generated"
:Finally
cmd /k cd %~dp0