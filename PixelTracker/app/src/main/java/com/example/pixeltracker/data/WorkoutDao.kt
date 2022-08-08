package com.example.pixeltracker.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow



@Dao
interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkouts(workout : List<Workout>)

    @Query("SELECT * FROM workout WHERE name = :workoutName")
    fun getWorkoutInfo(workoutName: String) : Flow<Workout>

    @Query("SELECT * FROM workout")
    fun getAll():Flow<List<Workout>>
    @Delete
    suspend fun deleteWorkout(workout:Workout)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addWorkout(workout: Workout)
    @Update
    suspend fun updateWorkout(workout: Workout)
}