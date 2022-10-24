package com.delesma.perceptioncheck.data

import com.squareup.moshi.Json

data class SingleSpell (
    val slug: String,
    val name :String,
    val desc: String,
    @Json(name="higher_level") val higherLevel: String,
    val page: String,
    val range: String,
    val components: String,
    val material: String,
    val ritual: String,
    val duration: String,
    val concentration: String,
    @Json(name="casting_time") val castingTime :String,
    val level: String,
    @Json(name= "level_int") val levelInt:Int,
    val school: String,
    @Json(name="dnd_class") val classes: String,
    val archetype :String,
    val circles: String,
    @Json(name="document__slug") val dSlug:String,
    @Json(name="document__title") val docTitle:String,
    @Json(name = "document__license_url") val docLicense:String
        )