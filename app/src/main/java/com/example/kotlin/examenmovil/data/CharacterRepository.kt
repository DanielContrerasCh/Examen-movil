package com.example.kotlin.examenmovil.data

import com.example.kotlin.examenmovil.data.network.CharacterApiClient
import com.example.kotlin.examenmovil.data.network.model.CharacterObject

class CharacterRepository {
    private val apiPokemon = CharacterApiClient()

    suspend fun getCharacterList(): CharacterObject? {
        return apiPokemon.getCharacterList()
    }
}
