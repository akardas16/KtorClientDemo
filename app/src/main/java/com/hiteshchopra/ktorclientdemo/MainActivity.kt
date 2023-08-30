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
import androidx.lifecycle.lifecycleScope
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.hiteshchopra.ktorclientdemo.data.Status
import com.hiteshchopra.ktorclientdemo.data.model.CreateUserRequest
import com.hiteshchopra.ktorclientdemo.data.model.CreateUserResponse
import com.hiteshchopra.ktorclientdemo.data.model.UserListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
  private val viewModel by viewModel<MainActivityVM>()


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
    lifecycleScope.launch {
      //viewModel.fetchImagesStatus.collect{status -> }
    }

    lifecycleScope.launch {
      viewModel.fetchAllUsers.collect{status ->
        when(status){
          //Loading started
          is Status.Loading -> { circularProgressIndicator.show() }
          //Loading finished
          is Status.Success<*> -> {
            circularProgressIndicator.hide()
            status.data.let {
              textView.text = (it as UserListResponse).toString()
            }

          }
          //Loading finished
          is Status.Error -> {
            circularProgressIndicator.hide()
            withContext(Dispatchers.Main){
              Toast.makeText(this@MainActivity,"Error happened! ${status.exception.message}",Toast.LENGTH_LONG).show()
            }

          }

        }

      }
    }


   lifecycleScope.launch {
     viewModel.createUser.collect{ status ->
       when(status){
         //Loading started
         is Status.Loading -> { circularProgressIndicator.show() }
         //Loading finished
         is Status.Success<*> -> {
           circularProgressIndicator.hide()
           status.data.let {
             textView.text = (it as CreateUserResponse).toString()
           }

         }
         //Loading finished
         is Status.Error -> {
           circularProgressIndicator.hide()
           Toast.makeText(this@MainActivity,"Error happened! ${status.exception.message}",Toast.LENGTH_LONG).show()
         }
       }

     }
   }

  }


}