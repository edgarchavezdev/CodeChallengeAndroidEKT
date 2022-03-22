package com.edgarchavezdev.codechallengeandroidekt.repository.home.repo

import com.edgarchavezdev.CodeChallengeAndroidEKT.home.module.fragment.home.repository.model.PokemonData
import com.edgarchavezdev.codechallengeandroidekt.repository.home.retrofit.HomeApi
import com.edgarchavezdev.codechallengeandroidekt.repository.room.instance.DataBaseEKT
import com.edgarchavezdev.codechallengeandroidekt.repository.room.model.PokemonLocal
import com.edgarchavezdev.codechallengeandroidekt.utils.Retrofit.getRetrofit

class HomeRepository(
    private val dataBaseEKT : DataBaseEKT
) {
    private val LIMIT_OF_POKEMONS = 151

    suspend fun getAllPokemons() : List<PokemonLocal>?{
        val localList = getAllPokemon()

        return if(localList.isEmpty()){
            val response = getAllPokemon(LIMIT_OF_POKEMONS)
            response?.let { r ->
                saveData(r)
            }
            getAllPokemons()
        }else{
            localList
        }
    }

    private fun saveData(response : PokemonData){
        addPokemon(convertDataLocal(response))
    }

    private fun getAllPokemon() : List<PokemonLocal>{

        return dataBaseEKT.pokemon().getAll()
    }

    private fun addPokemon(pokemonList : List<PokemonLocal>) {
        dataBaseEKT.pokemon().addPokemons(pokemonList)
    }

    private suspend fun getAllPokemon(limit : Int) : PokemonData?{
        val call = getRetrofit().create(HomeApi::class.java).getAllPokemonAsync(limit)
        val pokemons = call.body()
        return if(call.isSuccessful){
            pokemons
        }else{
            null
        }
    }

    fun convertDataLocal(pokemonData: PokemonData) : List<PokemonLocal> {
        return pokemonData.results.map { item
            -> PokemonLocal(
            name=  item.name,
            isFavorite =  false
        ) }.toList()
    }
}