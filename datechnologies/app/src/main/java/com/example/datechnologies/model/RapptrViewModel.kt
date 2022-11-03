package com.example.datechnologies.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datechnologies.activities.LoginActivity
import com.example.datechnologies.data.ChatLogMessageModel
import com.example.datechnologies.data.InitialResponse
import com.example.datechnologies.data.LoginModel
import com.example.datechnologies.data.LoginResponse
import com.example.datechnologies.network.loginApi
import com.example.datechnologies.network.rapptrLoginApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Exception

class RapptrViewModel : ViewModel() {
    var responseTime :Long? = null
    var firstResponse = MutableLiveData<Response<InitialResponse>>()
    var loginResponse = MutableLiveData<Response<LoginResponse>>()
    var allMessages = MutableLiveData<List<ChatLogMessageModel>>()

    fun attemptLogin(email: String, password:String){
        responseTime = null
        viewModelScope.launch{
                var doTheThing = loginApi.retrofitService.postLogin(email,password)
                responseTime = doTheThing.raw().receivedResponseAtMillis() - doTheThing.raw().sentRequestAtMillis()
                loginResponse.value = doTheThing
                Log.d("Login Response", loginResponse.value!!.message())
                Log.d("Login Response", "The API call took ${responseTime}")
            }
        }


    fun retrieveMessages(){
        viewModelScope.launch{
            allMessages.value = loginApi.retrofitService.getMessages().body()!!.data
            Log.d("Chat Response",allMessages.value!!.size.toString())

        }
    }
}