package com.turemote.tv

import org.nanohttpd.protocols.http.IHTTPSession
import org.nanohttpd.protocols.http.NanoHTTPD
import org.nanohttpd.protocols.http.response.Response

class RemoteServer(port: Int = 8080) : NanoHTTPD(port) {
    override fun serve(session: IHTTPSession): Response {
        val uri = session.uri
        val params = session.parameters
        return when (uri) {
            "/keyevent" -> {
                val code = params["code"]?.firstOrNull() ?: "KEYCODE_HOME"
                Runtime.getRuntime().exec(arrayOf("input", "keyevent", code))
                Response.newFixedLengthResponse("OK: $code")
            }
            "/status" -> Response.newFixedLengthResponse("TV remote server running")
            else -> Response.newFixedLengthResponse("<html><body><h1>TV Remote</h1></body></html>")
        }
    }
}
"