package com.example.datechnologies.data

import com.squareup.moshi.Json

data class InitialResponse (
    @Json(name="data") var data :List<ChatLogMessageModel>
        )