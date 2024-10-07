package com.example.kotlin.examenmovil.data.repository

import com.example.kotlin.examenmovil.data.network.CharacterApiClient
import com.example.kotlin.examenmovil.data.network.model.CharacterObject

class CharacterRepository {
    private val api = CharacterApiClient()

    suspend fun getCharacterList(): CharacterObject? {
        return api.getCharacterList()
    }
}
