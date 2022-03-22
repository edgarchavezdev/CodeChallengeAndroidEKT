package com.edgarchavezdev.codechallengeandroidekt.repository.home.retrofit

import com.edgarchavezdev.CodeChallengeAndroidEKT.home.module.fragment.home.repository.model.PokemonData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {
    @GET(HomeConfig.GET_ALL_POKEMON)
    suspend fun getAllPokemonAsync(@Query("limit") limit: Int): Response<PokemonData>
}