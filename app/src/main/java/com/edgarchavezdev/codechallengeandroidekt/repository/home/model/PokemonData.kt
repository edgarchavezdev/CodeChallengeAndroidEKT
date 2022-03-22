package com.edgarchavezdev.CodeChallengeAndroidEKT.home.module.fragment.home.repository.model

import com.edgarchavezdev.codechallengeandroidekt.repository.room.model.PokemonLocal

data class PokemonData(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)

fun PokemonData.getDataLocal() : List<PokemonLocal> {
    return results.map { item
        -> PokemonLocal(
        name=  item.name,
        isFavorite =  false
    ) }.toList()
}