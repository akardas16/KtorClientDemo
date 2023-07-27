package com.hiteshchopra.ktorclientdemo.data

object Endpoints {
  private const val BASE_URL = "https://reqres.in"
  const val FETCH_IMAGES_URL = "${BASE_URL}/photos?page=1"
   fun allUsers() = "$BASE_URL/api/users?page=2"
    fun createUserUrl() = "$BASE_URL/api/users"
}