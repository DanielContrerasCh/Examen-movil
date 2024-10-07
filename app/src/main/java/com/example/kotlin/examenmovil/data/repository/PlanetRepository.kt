package com.example.kotlin.examenmovil.data.repository

import com.example.kotlin.examenmovil.data.network.CharacterApiClient
import com.example.kotlin.examenmovil.data.network.PlanetApiClient
import com.example.kotlin.examenmovil.data.network.model.CharacterObject
import com.example.kotlin.examenmovil.data.network.model.Planet
import com.example.kotlin.examenmovil.data.network.model.PlanetObject

class PlanetRepository {
    private val api = PlanetApiClient()

    suspend fun getPlanetList(): PlanetObject? {
        return api.getPlanetList()
    }

    suspend fun getPlanetDetail(id: Int): Planet? {
        return api.getPlanetDetail(id)
    }
}