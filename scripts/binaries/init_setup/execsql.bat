setlocal EnableDelayedExpansion
cd /d "%1"
for %%a in (*.sql) do (
  if not exist executed/%%a (
    cd /d "%2"
    call mysql --login-path=hjhFDS2fREFd < %1/%%a
    IF !errorlevel! NEQ 0 GOTO Error
    cd /d "%1"
    IF not exist executed (mkdir executed)
    call copy "%%a" "executed/%%a"
  )
)
cd %~dp0
GOTO Success
:Error
exit /b 1
:Success
exit /b 0