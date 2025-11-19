package com.example.wifithing.network

import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*

actual fun provideEngine(): HttpClientEngineFactory<*> = Darwin
