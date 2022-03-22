package com.edgarchavezdev.CodeChallengeAndroidEKT.home.module.fragment.abilities.repository.retrofit

import com.edgarchavezdev.CodeChallengeAndroidEKT.home.module.fragment.abilities.repository.model.AbilitiesPokemon
import com.edgarchavezdev.codechallengeandroidekt.repository.abilities.retrofit.AbilityConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AbilityApi {
    @GET(AbilityConfig.GET_ABILITY_POKEMON)
    suspend fun getAbilitiesPokemonAsync(@Path("name") name: String): Response<AbilitiesPokemon>
}