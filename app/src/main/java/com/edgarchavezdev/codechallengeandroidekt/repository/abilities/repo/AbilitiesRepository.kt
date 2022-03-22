package com.edgarchavezdev.codechallengeandroidekt.repository.abilities.repo

import com.edgarchavezdev.CodeChallengeAndroidEKT.home.module.fragment.abilities.repository.model.AbilitiesPokemon
import com.edgarchavezdev.CodeChallengeAndroidEKT.home.module.fragment.abilities.repository.retrofit.AbilityApi
import com.edgarchavezdev.codechallengeandroidekt.utils.Retrofit


class AbilitiesRepository(
) {
    suspend fun getAbilitiesPokemon(name : String) : AbilitiesPokemon?{
        val call = Retrofit.getRetrofit().create(AbilityApi::class.java).getAbilitiesPokemonAsync(name)
        val ability = call.body()
        return if(call.isSuccessful){
            ability
        }else{
            null
        }
    }
}