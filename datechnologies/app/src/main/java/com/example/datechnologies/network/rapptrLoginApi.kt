package com.example.datechnologies.network

import com.example.datechnologies.data.InitialResponse
import com.example.datechnologies.data.LoginModel
import com.example.datechnologies.data.LoginResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private val okHttp = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    .build()
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private const val BASE_URL = "http://dev.rapptrlabs.com/Tests/scripts/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(okHttp)
    .build()

interface rapptrLoginApi{
    @FormUrlEncoded
    @POST("login.php")
    suspend fun postLogin(@Field("email") email:String, @Field("password") password:String):Response<LoginResponse>

    @GET("chat_log.php")
    suspend fun getMessages():Response<InitialResponse>
}

object loginApi{
    val retrofitService: rapptrLoginApi by lazy{
        retrofit.create(rapptrLoginApi::class.java)
    }
}