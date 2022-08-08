package com.example.pixeltracker.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class Workout(
    @PrimaryKey val name: String,
    @NotNull @ColumnInfo(name="calm_weight") val calmWeight:Int,
    @NotNull @ColumnInfo(name="max_weight") val maxWeight:Int
)
