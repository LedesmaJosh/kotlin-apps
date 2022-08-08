package com.example.pixeltracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Update
import com.example.pixeltracker.data.Workout
import com.example.pixeltracker.databinding.FragmentUpdateWorkoutBinding
import com.example.pixeltracker.models.WorkoutViewModel
import com.example.pixeltracker.models.WorkoutViewModelFactory

class UpdateWorkoutFragment : Fragment() {
    private val viewModel:WorkoutViewModel by activityViewModels{
        WorkoutViewModelFactory(
            (activity?.application as WorkoutApplication).database.workoutDao()
        )
    }

    private val navigationArgs: UpdateWorkoutFragmentArgs by navArgs()

    private var _binding: FragmentUpdateWorkoutBinding? = null
    private val binding get() = _binding!!

    lateinit var workout:Workout

    private fun bind(workout:Workout){
        binding.apply{
            workoutName.text = workout.name
            makeChanges.setOnClickListener{submitChanges()}
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = navigationArgs.name
        viewModel.getWorkout(name).observe(this.viewLifecycleOwner){
            selectedItem ->
            workout = selectedItem
            bind(workout)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateWorkoutBinding.inflate(inflater, container,false)
        return binding.root
    }

    fun submitChanges(){
        if(binding.editCalmWeight.text.isBlank() && binding.editMaxWeight.text.isNotBlank()){
            viewModel.updateWorkout(workout.name,workout.calmWeight,binding.editMaxWeight.text.toString().toInt())
        }
        else if(binding.editCalmWeight.text.isNotBlank() && binding.editMaxWeight.text.isBlank()){
            viewModel.updateWorkout(workout.name,binding.editCalmWeight.text.toString().toInt(),workout.maxWeight)
        }
        else if(binding.editMaxWeight.text.isBlank() && binding.editCalmWeight.text.isBlank()){
            viewModel.updateWorkout(workout.name,workout.calmWeight,workout.maxWeight)
        }
        else{
            viewModel.updateWorkout(workout.name, binding.editCalmWeight.text.toString().toInt(), binding.editMaxWeight.text.toString().toInt())
        }
        val action = UpdateWorkoutFragmentDirections.actionUpdateWorkoutFragmentToWorkoutFragmentList()
        findNavController().navigate(action)


    }

}