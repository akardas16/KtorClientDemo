package com.hiteshchopra.ktorclientdemo.data.model

data class CreateUserRequest(val name: String, val job: String)
data class CreateUserResponse(val id: String, val createdAt: String)