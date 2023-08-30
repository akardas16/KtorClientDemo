package com.hiteshchopra.ktorclientdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiteshchopra.ktorclientdemo.data.ktornetworking.Status
import com.hiteshchopra.ktorclientdemo.data.model.CreateUserRequest
import com.hiteshchopra.ktorclientdemo.data.ktornetworking.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivityVM(private val repository: MainRepository) : ViewModel() {

  private val _fetchImagesStatus = MutableStateFlow<Status>(Status.Loading)
  val fetchImagesStatus: MutableStateFlow<Status> = _fetchImagesStatus

  private val _fetchAllUsers = MutableStateFlow<Status>(Status.Loading)
  val fetchAllUsers:MutableStateFlow<Status> = _fetchAllUsers

  private val _createUser = MutableStateFlow<Status>(Status.Loading)
  val createUser:MutableStateFlow<Status> = _createUser



  private suspend fun fetchImages() {
    _fetchImagesStatus.value = Status.Loading
    viewModelScope.launch(Dispatchers.IO){
      try {
        val result = repository.getImages() // Network call
        _fetchImagesStatus.value = Status.Success(result)
      } catch (exception: Exception) {
        _fetchImagesStatus.value = Status.Error(exception)
      }
    }
  }


  fun allUsers(){
    _fetchAllUsers.value = Status.Loading

    viewModelScope.launch(Dispatchers.IO){
      try {
        val result = repository.allUsers() // Network call
        _fetchAllUsers.value = Status.Success(result)
      } catch (exception: Exception) {
        _fetchAllUsers.value = Status.Error(exception)
      }
    }

  }

  fun createUser(model:CreateUserRequest){
    _createUser.value = Status.Loading

    viewModelScope.launch(Dispatchers.IO){
      try {
        val result = repository.createNewUser(model) // Network call
        _createUser.value = Status.Success(result)
      } catch (exception: Exception) {
        _createUser.value = Status.Error(exception)
      }
    }
  }
}


