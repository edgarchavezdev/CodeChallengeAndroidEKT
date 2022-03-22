package com.edgarchavezdev.codechallengeandroidekt.repository.evolutionchain.repo

import com.edgarchavezdev.CodeChallengeAndroidEKT.home.module.fragment.detail.repository.model.EvolutionChain
import com.edgarchavezdev.CodeChallengeAndroidEKT.home.module.fragment.evolutionchain.repository.model.PokemonEvolution
import com.edgarchavezdev.codechallengeandroidekt.repository.evolutionchain.retrofit.EvolutionChainApi
import com.edgarchavezdev.codechallengeandroidekt.utils.Retrofit

class EvolutionChainRepository(
) {
    suspend fun getEvolutionChainPokemon(url : String) : PokemonEvolution?{
        val call = Retrofit.getRetrofit().create(EvolutionChainApi::class.java).getEvolutionChainPokemonAsync(url)
        val url = call.body()
        return if(call.isSuccessful){
            url
        }else{
            null
        }
    }
}