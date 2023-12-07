package com.example.pokedx

import com.google.gson.annotations.SerializedName

data class Pokemons(
    val pokemons: List<PokemonApiResultsByNumber>
)

data class PokemonApiResultsByNumber(
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("sprites")
    val sprites: Sprites
)

data class Sprites(
    @SerializedName("front_default")
    val image: String
)
