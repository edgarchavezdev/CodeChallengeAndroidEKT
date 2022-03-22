package com.edgarchavezdev.codechallengeandroidekt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edgarchavezdev.CodeChallengeAndroidEKT.home.module.fragment.evolutionchain.repository.model.PokemonEvolution
import com.edgarchavezdev.CodeChallengeAndroidEKT.home.repository.evolutionLinerepository.model.EvolvesTo
import com.edgarchavezdev.codechallengeandroidekt.repository.evolutionchain.repo.EvolutionChainRepository
import com.edgarchavezdev.codechallengeandroidekt.repository.evolutionchain.model.PokemonEvolutionChainView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EvolutionChainViewModel (val repository : EvolutionChainRepository) : ViewModel() {

    private val responseEvolutionChainPokemonMLD = MutableLiveData<List<PokemonEvolutionChainView>>()
    val responseEvolutionChainPokemon : LiveData<List<PokemonEvolutionChainView>>
        get () = responseEvolutionChainPokemonMLD

    private val errorMLD = MutableLiveData<String?>()
    val error : LiveData<String?>
        get () = errorMLD

    @DelicateCoroutinesApi
    fun getEvolutionChainPokemon(url : String){
        GlobalScope.launch {
            val response = repository.getEvolutionChainPokemon(url)
            GlobalScope.launch(Main) {
                if (response != null){
                    responseEvolutionChainPokemonMLD.value = toEvolutionChain(response)
                } else {
                    errorMLD.value = "Ocurrio un error al consultar la cadena evolutiva"
                }
            }
        }
    }
    fun toEvolutionChain(pokemonEvolution : PokemonEvolution) : List<PokemonEvolutionChainView>{
        val listToChainView : MutableList<PokemonEvolutionChainView> = mutableListOf()
        buildEvolutionChain(pokemonEvolution.chain.evolves_to, listToChainView)
        return listToChainView.toList()
    }

    fun buildEvolutionChain(evolutionItem : List<EvolvesTo>, listToChainView : MutableList<PokemonEvolutionChainView>){
        evolutionItem.forEach { item ->
            val poke = PokemonEvolutionChainView(
                item.species.name
            )
            listToChainView.add(poke)
            if(item.evolves_to.isNotEmpty()){
                buildEvolutionChain(item.evolves_to, listToChainView)
            }
        }
    }




}