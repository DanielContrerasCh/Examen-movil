package com.example.kotlin.examenmovil.framework.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.examenmovil.data.network.model.CharacterBase
import com.example.kotlin.examenmovil.data.network.model.CharacterObject
import com.example.kotlin.examenmovil.data.network.model.PlanetObject
import com.example.kotlin.examenmovil.domain.CharacterListRequirement
import com.example.kotlin.examenmovil.domain.PlanetListRequirement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlanetViewModel : ViewModel() {

    val planetLiveData = MutableLiveData<PlanetObject>()
    private val planetListRequirement = PlanetListRequirement()

    fun getPlanetList(){
        viewModelScope.launch(Dispatchers.IO) {
            val result: PlanetObject? = planetListRequirement()
            CoroutineScope(Dispatchers.Main).launch {
                planetLiveData.postValue(result!!)
            }
        }
    }
}