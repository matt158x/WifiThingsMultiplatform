// com.example.wifithing.di.android.kt
package com.example.wifithing.di

import com.example.wifithing.data.local.DatabaseDriverFactory
import com.example.wifithing.data.repository.LampRepositoryImpl
import com.example.wifithing.db.AppDatabase
import com.example.wifithing.domain.repository.LampRepository
import com.example.wifithing.presentation.viewmodel.LampViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

actual val appModule: Module = module {

    single { DatabaseDriverFactory(androidContext()) }

    single {
        AppDatabase(get<DatabaseDriverFactory>().createDriver())
    }

    single<LampRepository> { LampRepositoryImpl(get()) }

    viewModelOf(::LampViewModel)
}