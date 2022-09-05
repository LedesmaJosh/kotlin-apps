package com.example.perceptioncheck.models

import android.util.Log
import androidx.lifecycle.*
import com.example.perceptioncheck.SpellBookFragment
import com.example.perceptioncheck.data.SingleSpell
import com.example.perceptioncheck.data.Spell
import com.example.perceptioncheck.network.dndApi
import kotlinx.coroutines.launch



class SpellViewModel : ViewModel() {
   private val _singleSpell = MutableLiveData<SingleSpell>()
    val singleSpell get() = _singleSpell as LiveData<SingleSpell>
    val _spell = MutableLiveData<Spell>()
    val what get() = _spell as LiveData<Spell>
    val _yeehaw = MutableLiveData<List<SingleSpell>>()
    val spell get() = _yeehaw as LiveData<List<SingleSpell>>
    val type :String = ""
    var pages : Int = 1

    fun getSingleSpell(slug: String):LiveData<SingleSpell>{
        viewModelScope.launch{
            _singleSpell.value = dndApi.retrofitService.getSingleSpell(slug)
            Log.d("SingleSpell", _singleSpell.value!!.name)
        }
        return _singleSpell
    }

    fun getSpells(type:String, page:String){
        viewModelScope.launch{
           _spell.value = dndApi.retrofitService.getSpells(type, page)
            Log.d("Spell", pages.toString())
            _yeehaw.value = _spell.value!!.results
           while(what.value?.next != null){
                pages ++
                _spell.value = dndApi.retrofitService.getSpells(type,pages.toString())
                _yeehaw.value = _yeehaw.value?.plus(_spell.value!!.results)

            }
        }
        pages = 1
    }
}


