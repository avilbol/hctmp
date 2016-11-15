@Echo off
For /F "tokens=1* delims==" %%A IN (my-hc-settings.properties) DO (
    IF "%%A"=="glassfish_base" set glassfish_base=%%B
    IF "%%A"=="glassfish_domain" set glassfish_domain=%%B
    IF "%%A"=="mysql_jdbc" set mysql_jdbc=%%B
    IF "%%A"=="mysql_pass" set mysql_pass=%%B
    IF "%%A"=="mysql_port" set mysql_port=%%B
)
SET "loc_url=%glassfish_base%/domains/%glassfish_domain%/config"
setlocal EnableDelayedExpansion
cd "%loc_url%"
call copy domain-dyn.xml domain.xml
cd %~dp0
cd ../../lib
set locsl_url=%loc_url:/=\%
fart.exe %locsl_url%\domain.xml @@MYSQL_URL@@ %mysql_jdbc%
fart.exe %locsl_url%\domain.xml @@MYSQL_PASS@@ %mysql_pass%
fart.exe %locsl_url%\domain.xml @@MYSQL_PORT@@ %mysql_port%
cd %~dp0