package com.hiteshchopra.ktorclientdemo.data.ktornetworking

import com.hiteshchopra.ktorclientdemo.BuildConfig

object Endpoints {
  const val FETCH_IMAGES_URL = "${BuildConfig.BASE_URL}/photos?page=1"
   fun allUsers() = "${BuildConfig.BASE_URL}/api/users?page=2"
   fun createUserUrl() = "${BuildConfig.BASE_URL}/api/users"
}