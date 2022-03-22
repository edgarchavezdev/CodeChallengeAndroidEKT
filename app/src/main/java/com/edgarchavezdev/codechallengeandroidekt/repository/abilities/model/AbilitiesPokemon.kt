package com.edgarchavezdev.CodeChallengeAndroidEKT.home.module.fragment.abilities.repository.model

import com.edgarchavezdev.codechallengeandroidekt.repository.abilities.model.AbilityPokemonView

data class AbilitiesPokemon(
    val abilities: List<Ability>
)

fun AbilitiesPokemon.toAbilitiesView() : List<AbilityPokemonView> =
    this.abilities.map { item -> AbilityPokemonView(item.ability.name) }.toList()
