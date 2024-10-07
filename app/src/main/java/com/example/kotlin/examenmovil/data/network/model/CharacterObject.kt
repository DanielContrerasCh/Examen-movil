package com.example.kotlin.examenmovil.data.network.model

import com.google.gson.annotations.SerializedName

data class CharacterObject(
    @SerializedName("items") val items: ArrayList<CharacterBase>,  // Cambié a List para que se adapte al JSON
    @SerializedName("meta") val meta: MetaData,
    @SerializedName("links") val links: LinksData
)

data class MetaData(
    @SerializedName("totalItems") val totalItems: Int,
    @SerializedName("itemCount") val itemCount: Int,
    @SerializedName("itemsPerPage") val itemsPerPage: Int,
    @SerializedName("totalPages") val totalPages: Int,
    @SerializedName("currentPage") val currentPage: Int
)

data class LinksData(
    @SerializedName("first") val first: String,
    @SerializedName("previous") val previous: String?,
    @SerializedName("next") val next: String?,
    @SerializedName("last") val last: String
)
