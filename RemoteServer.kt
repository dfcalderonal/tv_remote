// Ejemplo minimo: servidor HTTP en la app Android (Kotlin) usando NanoHTTPD
// Dependencia: implementation 'org.nanohttpd:nanohttpd:2.3.1'

class RemoteServer(port: Int = 8080) : NanoHTTPD(port) {
    override fun serve(session: IHTTPSession): Response {
        val uri = session.uri
        val params = session.parameters
        return when (uri) {
            "/keyevent" -> {
                val code = params["code"]?.firstOrNull() ?: "KEYCODE_HOME"
                Runtime.getRuntime().exec(arrayOf("input", "keyevent", code))
                newFixedLengthResponse("OK: $code")
            }
            "/launch" -> {
                val pkg = params["pkg"]?.firstOrNull() ?: return newFixedLengthResponse(Response.Status.BAD_REQUEST, MIME_PLAINTEXT, "pkg missing")
                Runtime.getRuntime().exec(arrayOf("am", "start", "-n", "$pkg/.MainActivity"))
                newFixedLengthResponse("Launched $pkg")
            }
            "/status" -> newFixedLengthResponse("TV remote server running")
            else -> newFixedLengthResponse("<html><body><h1>TV Remote</h1><p>Use /keyevent?code=KEYCODE_HOME</p></body></html>")
        }
    }
}
"