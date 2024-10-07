package com.example.kotlin.examenmovil.data.network.model

import com.google.gson.annotations.SerializedName

data class PlanetObject(
    @SerializedName("items") val items: ArrayList<PlanetBase>,
)
