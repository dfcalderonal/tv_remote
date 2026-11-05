@echo off
set ADB=C:\platform-tools\adb.exe
set IP=192.168.80.17:5555
set APK=%1

if "%APK%"=="" (
  echo Uso: install_test.bat ruta\app.apk
  exit /b 1
)

echo [1/5] Conectando ADB...
%ADB% connect %IP%

echo [2/5] Verificando dispositivo...
%ADB% -s %IP% devices

echo [3/5] Instalando APK...
%ADB% -s %IP% install -r -d "%APK%"

echo [4/5] Lanzando app...
%ADB% -s %IP% shell am start -n com.turemote.tv/.MainActivity

echo [5/5] Logs (Ctrl+C para salir)...
%ADB% -s %IP% logcat | findstr /i "RemoteServer"
"