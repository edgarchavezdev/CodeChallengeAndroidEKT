package com.edgarchavezdev.codechallengeandroidekt.repository.detail.retrofit

import com.edgarchavezdev.CodeChallengeAndroidEKT.home.module.fragment.detail.repository.model.PokemonDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailApi {
    @GET(DetailConfig.GET_DETAIL_POKEMON)
    suspend fun getDetailPokemonAsync(@Path("name") name: String): Response<PokemonDetail>
}