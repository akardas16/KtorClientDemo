package com.hiteshchopra.ktorclientdemo.data.ktornetworking

import com.hiteshchopra.ktorclientdemo.data.model.CreateUserRequest
import com.hiteshchopra.ktorclientdemo.data.model.CreateUserResponse
import com.hiteshchopra.ktorclientdemo.data.model.ImageResponse
import com.hiteshchopra.ktorclientdemo.data.model.UserListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.request.url

class MainRepositoryImpl(private val client: HttpClient) : MainRepository {
    override suspend fun getImages(): ImageResponse {
        return client.get {
            headers {
                //append(HttpHeaders.Authorization, "Client-ID jb-EjW223VFOntX6e8dY_KDNWy5SUvG8xILr0uZsZPk")
            }
            url(Endpoints.FETCH_IMAGES_URL)
        }.body()
    }

    override suspend fun allUsers(): UserListResponse {
        return client.get {
            //no header required
            url(Endpoints.allUsers())
        }.body()
    }

    override suspend fun createNewUser(model: CreateUserRequest): CreateUserResponse {
        return client.post {
            url(Endpoints.createUserUrl())
        }.body()
    }


}