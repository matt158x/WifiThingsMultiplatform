package com.example.wifithing.domain.repository

import com.example.wifithing.domain.model.Lamp

interface LampRepository {
    suspend fun getAllLamps(): List<Lamp>
    suspend fun addLamp(name: String, ip: String)
    suspend fun toggleLamp(ip: String, state: Boolean)
    suspend fun deleteLamp(ip: String)
}