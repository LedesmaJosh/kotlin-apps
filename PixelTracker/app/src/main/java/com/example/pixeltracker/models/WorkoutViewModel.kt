package com.example.pixeltracker.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.pixeltracker.data.Workout
import com.example.pixeltracker.data.WorkoutDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WorkoutViewModel(private val workoutDao: WorkoutDao):ViewModel(){
    val allWorkouts: LiveData<List<Workout>> = workoutDao.getAll().asLiveData()

    fun getWorkout(id:String) : LiveData<Workout>{
        return  workoutDao.getWorkoutInfo(id).asLiveData()
    }

    private fun getUpdatedWorkoutObject(workoutName: String, workoutCalmWeight:Int, workoutMaxWeight: Int):Workout{
        return Workout(name=workoutName, calmWeight = workoutCalmWeight, maxWeight = workoutMaxWeight)
    }
    fun updateWorkout(workoutName: String, workoutCalmWeight: Int, workoutMaxWeight: Int) {
        val updatedWorkout = getUpdatedWorkoutObject(workoutName, workoutCalmWeight, workoutMaxWeight)
        GlobalScope.launch{workoutDao.updateWorkout(updatedWorkout)}

    }

    fun addNewWorkout(workout:Workout){
        GlobalScope.launch{workoutDao.addWorkout(workout)}
    }
    fun deleteWorkout(workout:Workout){
        GlobalScope.launch{workoutDao.deleteWorkout(workout)}
    }
}

class WorkoutViewModelFactory(private val workoutDao: WorkoutDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WorkoutViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return WorkoutViewModel(workoutDao)as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}

