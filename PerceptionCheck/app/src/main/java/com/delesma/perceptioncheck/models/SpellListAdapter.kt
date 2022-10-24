package com.delesma.perceptioncheck.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.delesma.perceptioncheck.data.SingleSpell
import com.delesma.perceptioncheck.databinding.SpellListItemBinding


class SpellListAdapter(private val onItemClicked: (SingleSpell)->Unit): ListAdapter<SingleSpell, SpellListAdapter.SpellViewHolder>(
    DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellViewHolder {
        return SpellViewHolder(
           SpellListItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: SpellViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener{
            onItemClicked(current)
        }
        holder.bind(current)
    }



    class SpellViewHolder(private var binding: SpellListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(singleSpell: SingleSpell){
            binding.spellNameText.text = singleSpell.name

        }
    }
    companion object{
        private val DiffCallBack = object: DiffUtil.ItemCallback<SingleSpell>(){
            override fun areItemsTheSame(oldItem: SingleSpell, newItem: SingleSpell): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: SingleSpell, newItem: SingleSpell): Boolean {
                return oldItem.name == newItem.name
            }

        }
    }

}
