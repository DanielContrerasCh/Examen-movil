package com.example.kotlin.examenmovil.framework.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.examenmovil.data.network.model.CharacterBase
import com.example.kotlin.examenmovil.data.network.model.CharacterObject
import com.example.kotlin.examenmovil.domain.CharacterListRequirement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    val characterLiveData = MutableLiveData<CharacterObject>()
    private val characterListRequirement = CharacterListRequirement()

    private var characterList: ArrayList<CharacterBase> = ArrayList()

    fun getCharacterList(){
        viewModelScope.launch(Dispatchers.IO) {
            val result: CharacterObject? = characterListRequirement()
            result?.let {
                characterList = it.items
                characterLiveData.postValue(it)
            }
        }
    }

    // Filtrar por una sola raza
    fun filterByRaces(selectedRaces: List<String>) {
        val filteredList = characterList.filter { selectedRaces.contains(it.race) }
        characterLiveData.postValue(CharacterObject(ArrayList(filteredList))) // Convertir a ArrayList
    }

    // Filtrar por una sola afiliación
    fun filterByAffiliations(selectedAffiliations: List<String>) {
        val filteredList = characterList.filter { selectedAffiliations.contains(it.affiliation) }
        characterLiveData.postValue(CharacterObject(ArrayList(filteredList))) // Convertir a ArrayList
    }

    // Restablecer filtros
    fun resetFilters() {
        characterLiveData.postValue(CharacterObject(ArrayList(characterList))) // Convertir a ArrayList
    }
}