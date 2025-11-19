package com.example.wifithing.network

import io.ktor.client.engine.*
import io.ktor.client.engine.android.*

actual fun provideEngine(): HttpClientEngineFactory<*> = Android
