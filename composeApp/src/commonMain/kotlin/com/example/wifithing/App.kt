package com.example.wifithing


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.wifithing.di.appModule
import com.example.wifithing.presentation.ui.screens.MainScreen
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule)
    }) {
        MaterialTheme {
            MainScreen()
        }
    }
}
