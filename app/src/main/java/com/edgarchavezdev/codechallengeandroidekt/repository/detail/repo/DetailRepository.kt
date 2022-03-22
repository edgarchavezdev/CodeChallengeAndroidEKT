package com.edgarchavezdev.codechallengeandroidekt.repository.detail.repo

import com.edgarchavezdev.CodeChallengeAndroidEKT.home.module.fragment.detail.repository.model.PokemonDetail
import com.edgarchavezdev.codechallengeandroidekt.repository.detail.retrofit.DetailApi
import com.edgarchavezdev.codechallengeandroidekt.utils.Retrofit

class DetailRepository(
) {
    suspend fun getDetailPokemon(name : String) : PokemonDetail?{
        val call = Retrofit.getRetrofit().create(DetailApi::class.java).getDetailPokemonAsync(name)
        val detail = call.body()
        return if(call.isSuccessful){
            detail
        }else{
            null
        }
    }
}