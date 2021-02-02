package com.example.pactometro

import retrofit2.Call
import retrofit2.http.GET

interface PartidosService {

    @GET("resultados.json")
    fun getResultados () : Call<List<Partido>>
}