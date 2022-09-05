package com.example.perceptioncheck

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.perceptioncheck.databinding.FragmentSingleSpellBinding
import com.example.perceptioncheck.models.SpellViewModel
import kotlinx.coroutines.selects.select


class SingleSpellFragment : Fragment() {

    private var _binding: FragmentSingleSpellBinding?= null
    private val binding get() = _binding!!
    val viewModel = SpellViewModel()
    private val navigationArgs : SingleSpellFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSingleSpellBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val slug = navigationArgs.name
        binding.apply{
            viewModel.getSingleSpell(slug).observe(viewLifecycleOwner){
                selectedItem ->

                singleSpellName.text = selectedItem.name
                spellSchool.text = selectedItem.school
                spellLevel.text = selectedItem.level
                spellClasses.text = selectedItem.classes
                spellDescription.text = selectedItem.desc
                if(selectedItem.higherLevel == "" ){
                    spellHigherLevelLabel.visibility = View.INVISIBLE
                    spellHigherLevel.visibility = View.INVISIBLE
                }
                spellHigherLevel.text = selectedItem.higherLevel
                spellComponent.text = selectedItem.components
                spellMaterial.text = "(${selectedItem.material})"
                spellCastingTime.text = selectedItem.castingTime
                spellDuration.text = selectedItem.duration
                spellRange.text = selectedItem.range

               // viewModel.singleSpell.observe(viewLifecycleOwner, Observer {
                   // singleSpellName.text = viewModel.singleSpell.value!!.name})
            }



        }
    }

}