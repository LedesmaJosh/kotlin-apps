package com.example.pixeltracker

import android.app.Application
import com.example.pixeltracker.data.AppDatabase

class WorkoutApplication : Application() {
    val database : AppDatabase by lazy{ AppDatabase.getDatabase(this)
    }
}