package com.example.datechnologies.data

import com.squareup.moshi.Json

data class ChatLogMessageModel (
    @Json(name="user_id") val id :Int,
    @Json(name="name") val name :String,
    @Json(name="avatar_url") var avatarUrl:String,
    @Json(name="message") var message:String
)