package com.example.perceptioncheck.data

import androidx.lifecycle.LiveData
import com.squareup.moshi.Json

data class Spell (
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<SingleSpell>
    )
