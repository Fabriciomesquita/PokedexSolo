package com.example.pokedx

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedx.adapter.PokedexAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokedexMainActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private var listName = listOf<PokemonResults>()
    private val retrofitClient = NetworkUtils.getRetrofitInstance(
        "https://pokeapi.co/api/v2/"
    )
    private val endpoint = retrofitClient.create(Endpoint::class.java)
    private var pokemonAdapter: PokedexAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler = findViewById(R.id.recycler)

        pokemonAdapter = PokedexAdapter(context = this)
        recycler.adapter = pokemonAdapter
        recycler.layoutManager = LinearLayoutManager(this)

        getData()

    }

    private fun getData() {

        val callback = endpoint.getListPokemon()

        callback.enqueue(object : Callback<PokeApiResponse> {
            override fun onFailure(call: Call<PokeApiResponse>, t: Throwable) {
                Log.e("Pokemon_api", "error api result")
            }

            override fun onResponse(
                call: Call<PokeApiResponse>,
                response: Response<PokeApiResponse>
            ) {

                response.body()?.results?.let {
                    listName = it
                }
                getDataPokemon()
            }
        })
    }

    private fun getDataPokemon() {
        Thread {
            var count = 0
            val listNewPokes = mutableListOf<PokemonApiResultsByNumber>()

            for (x in listName) {
                val callback = endpoint.getPokemon(x.name)
                val response = callback.execute()

                if (response.isSuccessful) {
                    response.body()?.let {
                        listNewPokes.add(it)
                        count++
                        if (count >= 10) {
                            populateRecycler(listNewPokes.toList())
                            count = 0
                            listNewPokes.clear()
                        }
                    }
                } else {
                    Log.e("Pokemon_api", "error api result")
                }
            }
            populateRecycler(listNewPokes)
        }.start()
    }

    private fun populateRecycler(listNewPokes: List<PokemonApiResultsByNumber>) {
        recycler.post {
            pokemonAdapter?.addNewList(listNewPokes)
        }
    }

}

//    private fun getDataPokemon() {
//        for (x in listName) {
//            val callback = endpoint.getPokemon(x.name)
//            callback.enqueue(object : Callback<PokemonApiResultsByNumber> {
//                override fun onResponse(
//                    call: Call<PokemonApiResultsByNumber>,
//                    response: Response<PokemonApiResultsByNumber>
//                ) {
//                    response.body()?.let {
//                        listPokemons.add(it)
//                        pokemonAdapter?.notifyDataSetChanged()
//
//                    }
//                }
//
//                override fun onFailure(call: Call<PokemonApiResultsByNumber>, t: Throwable) {
//                    Log.e("Pokemon_api", "error api result")
//                }
//            })
//
//        }
//    }
