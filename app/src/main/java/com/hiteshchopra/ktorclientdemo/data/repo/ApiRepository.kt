package com.hiteshchopra.ktorclientdemo.data.repo

import android.content.Context
import com.hiteshchopra.ktorclientdemo.data.model.CreateUserRequest
import com.hiteshchopra.ktorclientdemo.data.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiRepository {
    private val service = ApiService.create()

    suspend fun allUsers():Result {
        return withContext(Dispatchers.IO){
            try {
                val result = service.allUsers()
                Result.Success(result)
            }catch (exception:Exception){
                Result.Error(exception)
            }
        }
    }

    suspend fun createUser(model: CreateUserRequest): Result{
        return withContext(Dispatchers.IO){
            try {
                val result = service.createNewUser(model)
                Result.Success(result)
            }catch (exception:Exception){
                Result.Error(exception)
            }
        }
    }

    suspend fun fetchImages(): Result {
        return withContext(Dispatchers.IO) {
            try {
                val result = service.getImages()
                Result.Success(result)
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }
    }
}

sealed class Result {
    class Success<T>(val data: T) : Result()
    class Error(val exception: Exception) : Result()
}