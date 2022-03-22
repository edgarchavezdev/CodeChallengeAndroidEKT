package com.edgarchavezdev.codechallengeandroidekt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edgarchavezdev.CodeChallengeAndroidEKT.home.module.fragment.abilities.repository.model.AbilitiesPokemon
import com.edgarchavezdev.codechallengeandroidekt.repository.abilities.repo.AbilitiesRepository
import com.edgarchavezdev.codechallengeandroidekt.repository.abilities.model.AbilityPokemonView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AbilitiesViewModel (val repository : AbilitiesRepository) : ViewModel() {

    private val responseAbilityPokemonMLD = MutableLiveData<List<AbilityPokemonView>>()
    val responseAbilityPokemon : LiveData<List<AbilityPokemonView>>
        get () = responseAbilityPokemonMLD

    private val errorMLD = MutableLiveData<String?>()
    val error : LiveData<String?>
        get () = errorMLD

    @DelicateCoroutinesApi
    fun getAbilitiesPokemon(namePokemon : String){
        GlobalScope.launch {
            val response = repository.getAbilitiesPokemon(namePokemon)
            GlobalScope.launch(Main) {
                if (response != null) {
                        responseAbilityPokemonMLD.value = toAbilitiesView(response)

                } else {
                    errorMLD.value = "Ocurrio un error al consultar la habilidad"
                }
            }
        }
    }

    fun toAbilitiesView(abilityPokemon : AbilitiesPokemon) : List<AbilityPokemonView> =
        abilityPokemon.abilities.map { item -> AbilityPokemonView(item.ability.name) }.toList()




}