setlocal EnableDelayedExpansion
@Echo off
cd %~dp0
For /F "tokens=1* delims==" %%A IN (my-hc-settings.properties) DO (
    IF "%%A"=="sql_scripts_folder" set sql_scripts_folder=%%B
    IF "%%A"=="mysql_user" set mysql_user=%%B
    IF "%%A"=="mysql_loc" set mysql_loc=%%B
)
if exist mysql_config_edi.bat (
 call mysql_config_edi "%mysql_loc%" "%mysql_user%"
 call move mysql_config_edi.bat executed/mysql_config_edi.bat
)
if exist setup_mysql.bat (
 call setup_mysql
 call move setup_mysql.bat executed/setup_mysql.bat
)
for %%s in (start 1.1 1.2 1.3 1.4 1.5 1.6 1.7 1.8) DO (
    call execsql %sql_scripts_folder%/%%s "%mysql_loc%"
    IF !errorlevel! NEQ 0 GOTO Error
)
exit /b 0
:Error
exit /b 1