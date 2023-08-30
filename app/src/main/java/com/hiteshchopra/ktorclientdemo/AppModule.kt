package com.hiteshchopra.ktorclientdemo

import com.hiteshchopra.ktorclientdemo.data.ktornetworking.MainRepository
import com.hiteshchopra.ktorclientdemo.data.ktornetworking.MainRepositoryImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.gson.gson
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClient(Android) {
            install(Logging)
            install(ContentNegotiation) {
                gson()
            }
        }
    }
    single<MainRepository> {
        MainRepositoryImpl(get())
    }

    viewModel{
        MainActivityVM(get())
    }
}