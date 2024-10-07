package com.example.kotlin.examenmovil.domain

import com.example.kotlin.examenmovil.data.network.model.Planet
import com.example.kotlin.examenmovil.data.network.model.PlanetObject
import com.example.kotlin.examenmovil.data.repository.PlanetRepository

class PlanetDetailRequirement {
    private val repository = PlanetRepository()

    suspend operator fun invoke(id: Int): Planet? = repository.getPlanetDetail(id)
}