package com.edgarchavezdev.CodeChallengeAndroidEKT.home.repository.evolutionLinerepository.model

data class EvolvesTo(
    val evolution_details: List<EvolutionDetail>,
    val evolves_to: List<EvolvesTo>,
    val is_baby: Boolean,
    val species: SpeciesX
)