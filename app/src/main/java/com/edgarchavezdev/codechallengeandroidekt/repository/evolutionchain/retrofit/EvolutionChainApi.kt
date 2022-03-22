package com.edgarchavezdev.codechallengeandroidekt.repository.evolutionchain.retrofit

import com.edgarchavezdev.CodeChallengeAndroidEKT.home.module.fragment.evolutionchain.repository.model.PokemonEvolution
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface EvolutionChainApi {
    @GET
    suspend fun getEvolutionChainPokemonAsync(@Url url : String): Response<PokemonEvolution>
}