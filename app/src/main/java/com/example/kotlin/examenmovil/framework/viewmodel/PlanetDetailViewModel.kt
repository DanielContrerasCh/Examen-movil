package com.example.kotlin.examenmovil.framework.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.examenmovil.data.network.model.Planet
import com.example.kotlin.examenmovil.domain.PlanetDetailRequirement
import com.example.kotlin.examenmovil.domain.PlanetListRequirement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlanetDetailViewModel : ViewModel() {
    val planetDetailLiveData = MutableLiveData<Planet>()
    private val planetDetailRequirement = PlanetDetailRequirement()

    fun getPlanetDetail(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val result: Planet? = planetDetailRequirement(id)
            CoroutineScope(Dispatchers.Main).launch {
                planetDetailLiveData.postValue(result!!)
            }
        }
    }
}