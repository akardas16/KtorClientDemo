package com.hiteshchopra.ktorclientdemo.data.ktornetworking

import java.lang.Exception

sealed class Status {
    object Loading : Status()
    class Success<T>(val data: T) : Status()
    class Error(val exception: Exception) : Status()
}