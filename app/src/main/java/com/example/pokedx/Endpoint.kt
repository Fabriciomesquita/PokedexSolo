package com.example.pokedx

import retrofit2.*
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {
    @GET("pokemon?limit=151")
    fun getListPokemon(): Call<PokeApiResponse>

    @GET("pokemon/{name}")
    fun getPokemon(@Path("name") name: String): Call<PokemonApiResultsByNumber>

}