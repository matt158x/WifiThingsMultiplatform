package com.example.wifithing

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform