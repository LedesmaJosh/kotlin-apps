package com.delesma.perceptioncheck.network

import com.delesma.perceptioncheck.data.SingleSpell
import com.delesma.perceptioncheck.data.Spell
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private val okHttp = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    .build()
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private const val BASE_URL = "https://api.open5e.com/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(okHttp)
    .build()

interface DnDApiService {
    @GET("spells/?document__slug__in=wotc-srd")
    suspend fun getSpells(@Query("dnd_class__icontains") type:String, @Query("page") page:String): Spell

    @GET("spells/{slug}")
    suspend fun getSingleSpell(@Path("slug") slug:String): SingleSpell

}

object dndApi{
    val retrofitService: DnDApiService by lazy{
        retrofit.create(DnDApiService::class.java)
    }
}