package com.example.pokedx.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.pokedx.PokemonApiResultsByNumber
import com.example.pokedx.R

class PokedexAdapter(
    private val pokedexList: MutableList<PokemonApiResultsByNumber> = mutableListOf(),
    private val context: Context
) : Adapter<PokedexViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.pokedex_item, parent, false
        )
        return PokedexViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokedexList.size
    }

    override fun onBindViewHolder(holder: PokedexViewHolder, position: Int) {
        holder.bind(pokedexList[position])
    }

    fun addNewList(listNewPokes: List<PokemonApiResultsByNumber>) {
        val currentCount = itemCount
        pokedexList.addAll(listNewPokes)
        notifyItemRangeInserted(currentCount,listNewPokes.size)
    }
}