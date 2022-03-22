package com.edgarchavezdev.codechallengeandroidekt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edgarchavezdev.CodeChallengeAndroidEKT.home.module.fragment.detail.repository.model.PokemonDetail
import com.edgarchavezdev.codechallengeandroidekt.repository.detail.model.PokemonDetailView
import com.edgarchavezdev.codechallengeandroidekt.repository.detail.repo.DetailRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailViewModel (val repository : DetailRepository) : ViewModel() {

    private val responseDetailPokemonMLD = MutableLiveData<PokemonDetailView>()
    val responseDetailPokemon : LiveData<PokemonDetailView>
        get () = responseDetailPokemonMLD

    private val errorMLD = MutableLiveData<String?>()
    val error : LiveData<String?>
        get () = errorMLD

    @DelicateCoroutinesApi
    fun getDetailPokemon(namePokemon : String){
        GlobalScope.launch {
            val response = repository.getDetailPokemon(namePokemon)
            GlobalScope.launch(Main) {
                if (response != null) {
                    responseDetailPokemonMLD.value = toView(response)
                } else {
                    errorMLD.value = "Ocurrio un error al consultar el detalle"
                }
            }
        }
    }

    fun toView(pokemonDetail : PokemonDetail) : PokemonDetailView {
        val eggGroupBuffer = StringBuffer()
        if(pokemonDetail.egg_groups.isNotEmpty()) {
            pokemonDetail.egg_groups.forEachIndexed { index, eggGroup ->
                if (index < pokemonDetail.egg_groups.size - 1) eggGroupBuffer.append("${eggGroup.name}, ") else eggGroupBuffer.append(
                    eggGroup.name
                )
            }
        }
        return PokemonDetailView(
            name = pokemonDetail.name,
            baseHappiness = pokemonDetail.base_happiness.toString(),
            color = pokemonDetail.color.name,
            captureRate = pokemonDetail.capture_rate.toString(),
            eggGroup = eggGroupBuffer.toString(),
            evolutionUrl = pokemonDetail.evolution_chain.url
        )
    }




}