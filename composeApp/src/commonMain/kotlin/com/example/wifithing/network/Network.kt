package com.example.wifithing.network

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.request.*

expect fun provideEngine(): HttpClientEngineFactory<*>

val httpClient by lazy {
    HttpClient(provideEngine())
}

suspend fun toggleLamp(ip: String, state: Boolean) {
    val url = "http://$ip/${if (state) "on" else "off"}"

    println("🌐 NETWORK toggleLamp() => url=$url")

    try {
        httpClient.get(url)
        println("✅ Wysłano żądanie: $url")
    } catch (e: Exception) {
        println("❌ Błąd przy wysyłaniu żądania do ESP8266: $e")
    }
}