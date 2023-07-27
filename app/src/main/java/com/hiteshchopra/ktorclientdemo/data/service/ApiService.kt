package com.hiteshchopra.ktorclientdemo.data.service

import android.content.Context
import com.hiteshchopra.ktorclientdemo.data.model.CreateUserRequest
import com.hiteshchopra.ktorclientdemo.data.model.CreateUserResponse
import com.hiteshchopra.ktorclientdemo.data.model.ImageResponse
import com.hiteshchopra.ktorclientdemo.data.model.UserListResponse
import io.ktor.client.HttpClient
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.serialization.gson.gson

interface ApiService {
    suspend fun getImages(): ImageResponse
    suspend fun allUsers():UserListResponse

    suspend fun createNewUser(model:CreateUserRequest):CreateUserResponse


    companion object {
        fun create(): ApiService {
            return ApiServiceImpl(
                client = HttpClient(Android) {
                    install(Logging)
                    install(ContentNegotiation) {
                        gson()
                    }
                }
            )
        }
    }
}