package com.example.kotlin.examenmovil.domain

import com.example.kotlin.examenmovil.data.repository.CharacterRepository
import com.example.kotlin.examenmovil.data.network.model.CharacterObject

class CharacterListRequirement {
    private val repository = CharacterRepository()

    suspend operator fun invoke(): CharacterObject? = repository.getCharacterList()
}
