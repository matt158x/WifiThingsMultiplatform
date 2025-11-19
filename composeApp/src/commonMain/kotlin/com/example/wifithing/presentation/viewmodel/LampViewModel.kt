package com.example.wifithing.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wifithing.domain.model.Lamp
import com.example.wifithing.domain.repository.LampRepository
import com.example.wifithing.network.toggleLamp as espToggle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LampViewModel(
    private val repository: LampRepository
) : ViewModel() {

    private val _lamps = MutableStateFlow<List<Lamp>>(emptyList())
    val lamps: StateFlow<List<Lamp>> = _lamps

    init {
        loadLamps()
    }

    private fun loadLamps() {
        viewModelScope.launch {
            _lamps.value = repository.getAllLamps()
        }
    }

    fun addLamp(name: String, ip: String) {
        viewModelScope.launch {
            repository.addLamp(name, ip)
            _lamps.value = repository.getAllLamps()
        }
    }

    fun deleteLamp(lamp: Lamp) {
        viewModelScope.launch {
            repository.deleteLamp(lamp.ip)
            _lamps.value = repository.getAllLamps()
        }
    }

    fun toggleLamp(lamp: Lamp) {
        viewModelScope.launch {
            val newState = !lamp.isOn

            println("🟡 toggleLamp() dla IP=${lamp.ip}, newState=$newState")


            espToggle(lamp.ip, newState)
            repository.toggleLamp(lamp.ip, newState)

            _lamps.value = _lamps.value.map {
                if (it.ip == lamp.ip) it.copy(isOn = newState) else it
            }
        }
    }
}
