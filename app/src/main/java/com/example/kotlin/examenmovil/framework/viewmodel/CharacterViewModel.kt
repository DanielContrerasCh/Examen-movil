package com.example.kotlin.examenmovil.framework.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.examenmovil.data.network.model.CharacterObject
import com.example.kotlin.examenmovil.domain.CharacterListRequirement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    val characterLiveData = MutableLiveData<CharacterObject>()
    private val characterListRequirement = CharacterListRequirement()

    fun getCharacterList(){
        viewModelScope.launch(Dispatchers.IO) {
            val result: CharacterObject? = characterListRequirement()
            CoroutineScope(Dispatchers.Main).launch {
                characterLiveData.postValue(result!!)
            }
        }
    }
}
