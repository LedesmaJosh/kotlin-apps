package com.example.pixeltracker

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.pixeltracker.data.Workout
import com.example.pixeltracker.databinding.FragmentAddWorkoutBinding
import com.example.pixeltracker.models.WorkoutViewModel
import com.example.pixeltracker.models.WorkoutViewModelFactory


class AddWorkoutFragment : Fragment() {
    private val viewModel: WorkoutViewModel by activityViewModels{
        WorkoutViewModelFactory(
            (activity?.application as WorkoutApplication).database.workoutDao()
        )
    }

    private var _binding: FragmentAddWorkoutBinding? = null
    private val binding get() = _binding!!
    lateinit var workout: Workout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddWorkoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply{
            addWorkout.setEnabled(false)
            addWorkout.setOnClickListener{addWorkout()}
        }
        binding.addWorkoutName.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.addWorkout.isEnabled = p0.toString().isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun addWorkout(){
        if(binding.addCalmWeight.text.isBlank() && binding.addMaxWeight.text.isNotBlank()){
            workout = Workout(binding.addWorkoutName.text.toString(),0,binding.addMaxWeight.text.toString().toInt())
        }
        if(binding.addCalmWeight.text.isNotBlank() && binding.addMaxWeight.text.isBlank()){
            workout = Workout(binding.addWorkoutName.text.toString(),binding.addCalmWeight.text.toString().toInt(), 0)
        }else if(binding.addCalmWeight.text.isBlank()&& binding.addMaxWeight.text.isBlank()){
            workout = Workout(binding.addWorkoutName.text.toString(),0,0)
        }else {
            workout = Workout(binding.addWorkoutName.text.toString(),
                binding.addCalmWeight.text.toString().toInt(),
                binding.addMaxWeight.text.toString().toInt())
        }
        viewModel.addNewWorkout(workout)
        val action = AddWorkoutFragmentDirections.actionAddWorkoutFragmentToWorkoutFragmentList()
        findNavController().navigate(action)
    }

}