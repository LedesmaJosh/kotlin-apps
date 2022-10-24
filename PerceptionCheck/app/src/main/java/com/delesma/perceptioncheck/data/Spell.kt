package com.delesma.perceptioncheck.data

data class Spell (
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<SingleSpell>
    )
