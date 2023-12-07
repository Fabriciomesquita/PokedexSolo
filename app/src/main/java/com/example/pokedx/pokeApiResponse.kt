package com.example.pokedx

import com.google.gson.annotations.SerializedName


data class PokeApiResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: String? = null,
    @SerializedName("results")
    val results: List<PokemonResults>
)

data class PokemonResults(
    val name: String,
    val url: String? = null
)

