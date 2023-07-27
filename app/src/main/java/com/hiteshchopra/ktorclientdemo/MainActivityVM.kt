package com.hiteshchopra.ktorclientdemo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiteshchopra.ktorclientdemo.Status.Loading
import com.hiteshchopra.ktorclientdemo.data.model.CreateUserRequest
import com.hiteshchopra.ktorclientdemo.data.repo.ApiRepository
import com.hiteshchopra.ktorclientdemo.data.repo.Result
import com.hiteshchopra.ktorclientdemo.data.repo.Result.Error
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivityVM : ViewModel() {

  private val _fetchImagesStatus = MutableLiveData<Status>()
  val fetchImagesStatus: LiveData<Status> = _fetchImagesStatus

  private val _fetchAllUsers = MutableLiveData<Status>()
  val fetchAllUsers:LiveData<Status> = _fetchAllUsers

  private val _createUser = MutableLiveData<Status>()
  val createUser:LiveData<Status> = _createUser

  fun fetchImages(context: Context) {
    _fetchImagesStatus.value = Loading
    viewModelScope.launch {
      when (val response = ApiRepository().fetchImages()) {
        is Result.Success<*> -> {
          _fetchImagesStatus.value = Status.Success(response.data)
        }
        is Error -> {
          _fetchImagesStatus.value = Status.Error(response.exception)
        }
      }
    }
  }

  fun allUsers(){
    _fetchAllUsers.value = Loading
    viewModelScope.launch {
      when(val response = ApiRepository().allUsers()){
        is Result.Success<*> -> {
          _fetchAllUsers.value = Status.Success(response.data)
        }
        is Error -> {
          _fetchAllUsers.value = Status.Error(response.exception)
        }
      }
    }
  }

  fun createUser(model:CreateUserRequest){
    _createUser.value = Loading
    viewModelScope.launch {
      when(val response = ApiRepository().createUser(model)){
        is Result.Success<*> -> {
          _createUser.value = Status.Success(response.data)
        }
        is Error -> {
          _createUser.value = Status.Error(response.exception)
        }
      }
    }
  }
}

sealed class Status {
  object Loading : Status()
  class Success<T>(val data: T) : Status()
  class Error(val exception: Exception) : Status()
}
