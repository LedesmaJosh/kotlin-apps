package com.example.pixeltracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pixeltracker.data.Workout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.pixeltracker.data.WorkoutDao
import com.example.pixeltracker.databinding.FragmentWorkoutListBinding
import com.example.pixeltracker.models.WorkoutListAdapter
import com.example.pixeltracker.models.WorkoutViewModel
import com.example.pixeltracker.models.WorkoutViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class WorkoutFragmentList : Fragment() {

    private val viewModel:WorkoutViewModel by activityViewModels{
        WorkoutViewModelFactory(
            (activity?.application as WorkoutApplication).database.workoutDao()
        )
    }
    private var _binding: FragmentWorkoutListBinding?= null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWorkoutListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = WorkoutListAdapter({
            val action = WorkoutFragmentListDirections.actionWorkoutFragmentListToUpdateWorkoutFragment(it.name)
            this.findNavController().navigate(action)
        },{
            createDeleteDialog(it)
        })
        binding.recyclerView.adapter= adapter
        viewModel.allWorkouts.observe(this.viewLifecycleOwner){workouts -> workouts.let{
            adapter.submitList(it)
        }}
        recyclerView = binding.recyclerView
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.addWorkoutFab.setOnClickListener {findNavController().navigate(WorkoutFragmentListDirections.actionWorkoutFragmentListToAddWorkoutFragment())
        }
    }

    fun createDeleteDialog(workout: Workout){
        MaterialAlertDialogBuilder(requireContext())

            .setTitle("Delete Workout")
            .setMessage("Would you like to delete ${workout.name} from the list?")
            .setCancelable(true)
            .setPositiveButton("Yes"){_,_ -> viewModel.deleteWorkout(workout)}
            .setNegativeButton("No"){_,_ -> null}
            .show()
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding= null
    }



}