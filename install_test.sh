#!/usr/bin/env bash
ADB="${ADB:-adb}"
IP="192.168.80.17:5555"
APK="$1"

if [ -z "$APK" ]; then
  echo "Uso: ./install_test.sh ruta/app.apk"
  exit 1
fi

echo "[1/5] Conectando ADB..."
$ADB connect "$IP"

echo "[2/5] Verificando dispositivo..."
$ADB -s "$IP" devices

echo "[3/5] Instalando APK..."
$ADB -s "$IP" install -r -d "$APK"

echo "[4/5] Lanzando app..."
$ADB -s "$IP" shell am start -n com.turemote.tv/.MainActivity

echo "[5/5] Logs (Ctrl+C para salir)..."
$ADB -s "$IP" logcat | grep -i "RemoteServer"
"