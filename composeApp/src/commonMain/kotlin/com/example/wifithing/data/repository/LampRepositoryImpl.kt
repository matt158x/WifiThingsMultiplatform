package com.example.wifithing.data.repository

import com.example.wifithing.db.AppDatabase
import com.example.wifithing.domain.model.Lamp
import com.example.wifithing.domain.repository.LampRepository

class LampRepositoryImpl(
    db: AppDatabase
) : LampRepository {

    private val queries = db.appDatabaseQueries

    override suspend fun getAllLamps(): List<Lamp> {
        return queries.selectAll()
            .executeAsList()
            .map { entity ->
                Lamp(
                    name = entity.name,
                    ip = entity.ip,
                    isOn = entity.isOn != 0L
                )
            }
    }

    override suspend fun deleteLamp(ip: String) {
        queries.deleteLamp(ip)
    }

    override suspend fun addLamp(name: String, ip: String) {
        queries.insertLamp(
            name = name,
            ip = ip,
            isOn = 0
        )
    }

    override suspend fun toggleLamp(ip: String, state: Boolean) {
        queries.updateLampState(
            isOn = if (state) 1 else 0,
            ip = ip
        )
    }
}
