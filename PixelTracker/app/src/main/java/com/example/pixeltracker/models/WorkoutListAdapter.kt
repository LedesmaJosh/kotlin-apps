package com.example.pixeltracker.models

import android.annotation.SuppressLint
import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pixeltracker.data.Workout
import com.example.pixeltracker.databinding.WorkoutListItemBinding

class WorkoutListAdapter(private val onItemClicked: (Workout)->Unit, private val onItemLongClicked: (Workout) -> Unit): ListAdapter<Workout,WorkoutListAdapter.WorkoutViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        return WorkoutViewHolder(
                    WorkoutListItemBinding.inflate(LayoutInflater.from(parent.context))
            )
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener{
            onItemClicked(current)
        }
        holder.itemView.setOnLongClickListener {
            onItemLongClicked(current)
            true
        }
        holder.bind(current)
    }



    class WorkoutViewHolder(private var binding: WorkoutListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(workout: Workout){
            binding.workoutName.text = workout.name
            binding.calmWeight.text = workout.calmWeight.toString()
            binding.maxWeight.text = workout.maxWeight.toString()
        }
    }
    companion object{
        private val DiffCallBack = object: DiffUtil.ItemCallback<Workout>(){
            override fun areItemsTheSame(oldItem: Workout, newItem: Workout): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Workout, newItem: Workout): Boolean {
                return oldItem.name == newItem.name
            }

        }
    }

}




