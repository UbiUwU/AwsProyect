package com.example.proyectoofertastrabajo.screens

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://18.215.183.15/"

    val api: ConvocatoriaApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ConvocatoriaApi::class.java)
    }
}
