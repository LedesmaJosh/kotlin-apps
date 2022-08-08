package com.example.pixeltracker.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import com.example.pixeltracker.data.WorkoutDao
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Workout::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database"
                ).addCallback(object: Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        GlobalScope.launch{getDatabase(context).workoutDao().insertWorkouts(workoutList)}
                    }
                })
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                Log.d("PLEASE","Database finally created.")
                instance


            }

            }

        }

        }










