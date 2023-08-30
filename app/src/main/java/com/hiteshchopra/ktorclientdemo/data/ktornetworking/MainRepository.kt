package com.hiteshchopra.ktorclientdemo.data.ktornetworking

import com.hiteshchopra.ktorclientdemo.data.model.CreateUserRequest
import com.hiteshchopra.ktorclientdemo.data.model.CreateUserResponse
import com.hiteshchopra.ktorclientdemo.data.model.ImageResponse
import com.hiteshchopra.ktorclientdemo.data.model.UserListResponse


interface MainRepository {
    suspend fun getImages(): ImageResponse
    suspend fun allUsers():UserListResponse

    suspend fun createNewUser(model:CreateUserRequest):CreateUserResponse

}