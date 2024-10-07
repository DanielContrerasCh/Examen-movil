package com.example.kotlin.examenmovil.data.network

import com.example.kotlin.examenmovil.data.network.model.CharacterObject
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApiService {
    @GET("characters")
    suspend fun getCharacterList(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): CharacterObject
}
