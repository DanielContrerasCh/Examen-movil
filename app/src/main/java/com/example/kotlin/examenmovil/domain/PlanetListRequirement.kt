package com.example.kotlin.examenmovil.domain

import com.example.kotlin.examenmovil.data.network.model.CharacterObject
import com.example.kotlin.examenmovil.data.network.model.PlanetObject
import com.example.kotlin.examenmovil.data.repository.CharacterRepository
import com.example.kotlin.examenmovil.data.repository.PlanetRepository

class PlanetListRequirement {
    private val repository = PlanetRepository()

    suspend operator fun invoke(): PlanetObject? = repository.getPlanetList()
}