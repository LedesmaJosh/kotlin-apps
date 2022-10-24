package com.delesma.perceptioncheck

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.delesma.perceptioncheck.data.classesList
import com.delesma.perceptioncheck.models.SpellListAdapter
import com.delesma.perceptioncheck.models.SpellViewModel
import com.delesma.perceptioncheck.databinding.FragmentSpellBookBinding


class SpellBookFragment : Fragment() {

    private var _binding : FragmentSpellBookBinding? =null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private val viewModel = SpellViewModel()
    var userSelect = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSpellBookBinding.inflate(inflater, container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SpellListAdapter{
            val action = SpellBookFragmentDirections.actionSpellBookToSingleSpellFragment(it.slug)
            this.findNavController().navigate(action)
            viewModel.pages = 1
        }
        binding.recyclerView.adapter = adapter
        viewModel.spell.observe(this.viewLifecycleOwner){
            spells -> spells.let{
                adapter.submitList(it)
        }
        }
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.classList.adapter= ArrayAdapter(this.requireContext(),android.R.layout.simple_spinner_item,
            classesList)
        binding.classList.setSelection(1,false)
        binding.classList.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val whatClass = p0?.getItemAtPosition(p2).toString()
                viewModel.pages = 1
                Log.d("Whatever", whatClass)
                viewModel.getSpells(whatClass,viewModel.pages.toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        }


    }




