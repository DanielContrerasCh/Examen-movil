package com.example.kotlin.examenmovil.data.network

import com.example.kotlin.examenmovil.data.network.model.Planet
import com.example.kotlin.examenmovil.data.network.model.PlanetObject
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlanetApiService {
    @GET("planets")
    suspend fun getPlanetList(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): PlanetObject

    @GET("planets/{id}")
    suspend fun getPlanetDetail(
        @Path("id") id: Int
    ): Planet
}