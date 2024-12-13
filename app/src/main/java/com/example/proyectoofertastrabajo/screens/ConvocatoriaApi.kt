package com.example.proyectoofertastrabajo.screens

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ConvocatoriaApi {
    @GET("api/convocatoriasApi")
    suspend fun getConvocatorias(@Header("Authorization") authToken: String): Response<List<Convocatorias>>
}

