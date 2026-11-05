# Control remoto personalizado para Google TV (IP 192.168.80.17)

## Arquitectura recomendada (100% independiente de la PC)

App Android (Kotlin) instalada en el Google TV que expone un servidor HTTP en la LAN.
El iPhone (Safari o app) envia comandos HTTP a http://192.168.80.17:8080.
La app ejecuta keyevents internamente con `input keyevent` y gestiona paquetes con `pm`.

Componentes:
- Servicio en primer plano (Foreground Service) con notificacion persistente.
- Auto-inicio al encender el TV (BroadcastReceiver BOOT_COMPLETED).
- WakeLock parcial para mantener el servidor activo.
- Endpoints: /keyevent, /launch, /status, / (UI web).

## Scripts
- install_test.bat / install_test.sh: conecta ADB, instala APK, lanza app, muestra logs.
"