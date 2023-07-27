package com.hiteshchopra.ktorclientdemo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.hiteshchopra.ktorclientdemo.Status.Error
import com.hiteshchopra.ktorclientdemo.Status.Loading
import com.hiteshchopra.ktorclientdemo.Status.Success
import com.hiteshchopra.ktorclientdemo.data.model.CreateUserRequest
import com.hiteshchopra.ktorclientdemo.data.model.CreateUserResponse
import com.hiteshchopra.ktorclientdemo.data.model.ImageResponse
import com.hiteshchopra.ktorclientdemo.data.model.UserListResponse

class MainActivity : AppCompatActivity() {
  private val viewModel: MainActivityVM by viewModels()
  lateinit var circularProgressIndicator: CircularProgressIndicator
  lateinit var textView: TextView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    circularProgressIndicator = findViewById(R.id.progress)
    textView = findViewById(R.id.textResult)
    addObservers()

    findViewById<Button>(R.id.buttonData).setOnClickListener {
     // viewModel.allUsers()
      viewModel.createUser(CreateUserRequest("Abdullah_Kardas","Mobile Developer"))
    }
  }



  private fun addObservers() {

    viewModel.fetchAllUsers.observe(this){status ->
      when(status){
        //Loading started
        is Loading -> { circularProgressIndicator.show() }
        //Loading finished
        is Success<*> -> {
          circularProgressIndicator.hide()
          status.data.let {
            textView.text = (it as UserListResponse).toString()
          }

        }
        //Loading finished
        is Error -> {
          circularProgressIndicator.hide()
          Log.i("sdfsdfsdfs", "addObservers: ${status.exception.message}")
          Toast.makeText(this,"Error happened! ${status.exception.message}",Toast.LENGTH_LONG).show()
        }
      }

    }

    //Create USER


    viewModel.createUser.observe(this){ status ->
      when(status){
        //Loading started
        is Loading -> { circularProgressIndicator.show() }
        //Loading finished
        is Success<*> -> {
          circularProgressIndicator.hide()
          status.data.let {
            textView.text = (it as CreateUserResponse).toString()
          }

        }
        //Loading finished
        is Error -> {
          circularProgressIndicator.hide()
          Log.i("sdfsdfsdfs", "addObservers: ${status.exception.message}")
          Toast.makeText(this,"Error happened! ${status.exception.message}",Toast.LENGTH_LONG).show()
        }
      }

    }
  }


}