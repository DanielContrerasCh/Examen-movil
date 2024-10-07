package com.example.kotlin.examenmovil.data.network

import com.example.kotlin.examenmovil.data.network.model.CharacterObject
import com.example.kotlin.examenmovil.data.network.model.Planet
import com.example.kotlin.examenmovil.data.network.model.PlanetObject

class PlanetApiClient {
    private lateinit var api: PlanetApiService

    suspend fun getPlanetList(): PlanetObject? {
        api = PlanetNetworkModuleDI()
        return try {
            api.getPlanetList(1, 20)  // Asumiendo que quieres 10 por página
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getPlanetDetail(id: Int): Planet? {
        api = PlanetNetworkModuleDI()
        return try {
            api.getPlanetDetail(id)  // Asumiendo que quieres 10 por página
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}