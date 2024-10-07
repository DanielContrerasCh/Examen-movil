package com.example.kotlin.examenmovil.data.network

import com.example.kotlin.examenmovil.data.network.model.CharacterObject

class CharacterApiClient {
    private lateinit var api: CharacterApiService

    suspend fun getCharacterList(): CharacterObject? {
        api = NetworkModuleDI()
        return try {
            api.getCharacterList(1, 58)  // Asumiendo que quieres 10 por página
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
