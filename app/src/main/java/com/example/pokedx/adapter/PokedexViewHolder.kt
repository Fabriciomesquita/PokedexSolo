package com.example.pokedx.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.pokedx.PokemonApiResultsByNumber
import com.example.pokedx.R

class PokedexViewHolder(itemView: View) : ViewHolder(itemView) {

    private val pokemonName = itemView.findViewById<TextView>(R.id.pokemon_name)
    private var image = itemView.findViewById<ImageView>(R.id.image)
    private val informationButton = itemView.findViewById<Button>(R.id.information_button)

    fun bind(pokedexModel: PokemonApiResultsByNumber) {
        pokemonName.text = "${pokedexModel.id} ${pokedexModel.name} "
        pokedexModel.sprites.image.let {
            Glide.with(itemView).load(it).into(image)
        }
        informationButton.setOnClickListener {
            // todo click
        }
    }
}
