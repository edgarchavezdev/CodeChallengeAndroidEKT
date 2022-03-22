package com.edgarchavezdev.codechallengeandroidekt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edgarchavezdev.codechallengeandroidekt.repository.home.repo.HomeRepository
import com.edgarchavezdev.codechallengeandroidekt.repository.room.model.PokemonLocal
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel (val repository : HomeRepository) : ViewModel() {

    private val responseLocalAllPokemonsMLD = MutableLiveData<List<PokemonLocal>>()
    val responseLocalAllPokemons : LiveData<List<PokemonLocal>>
        get () = responseLocalAllPokemonsMLD

    private val errorMLD = MutableLiveData<String?>()
    val error : LiveData<String?>
        get () = errorMLD

    @DelicateCoroutinesApi
    fun getAllPokemons(){
        GlobalScope.launch() {
            val response = repository.getAllPokemons()
            GlobalScope.launch(Main) {
                if (response != null ) {
                    responseLocalAllPokemonsMLD.value = response ?: listOf()
                } else {
                    errorMLD.value = null
                }
            }
        }
    }




}