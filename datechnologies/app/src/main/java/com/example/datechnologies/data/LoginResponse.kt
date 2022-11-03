package com.example.datechnologies.data

import com.squareup.moshi.Json

data class LoginResponse (
    @Json(name="code")  val code :String,
    @Json(name="message") val message: String
        )