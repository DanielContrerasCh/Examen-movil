package com.example.kotlin.examenmovil.data.network.model

import com.google.gson.annotations.SerializedName

data class Planet (
    val id: Int,
    val name: String,
    val isDestroyed: Boolean,
    val description: String,
    val image: String,
    val characters: List<CharacterBase>,
)