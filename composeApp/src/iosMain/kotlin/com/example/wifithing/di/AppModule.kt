// com.example.wifithing.di.ios.kt
package com.example.wifithing.di

import com.example.wifithing.data.local.DatabaseDriverFactory
import com.example.wifithing.data.repository.LampRepositoryImpl
import com.example.wifithing.db.AppDatabase
import com.example.wifithing.domain.repository.LampRepository
import com.example.wifithing.presentation.viewmodel.LampViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

actual val appModule: Module = module {  // <--- ZMIENIĆ NA 'val'

    single { DatabaseDriverFactory() }

    single {
        AppDatabase(get<DatabaseDriverFactory>().createDriver())
    }

    single<LampRepository> { LampRepositoryImpl(get()) }

    viewModelOf(::LampViewModel)
}