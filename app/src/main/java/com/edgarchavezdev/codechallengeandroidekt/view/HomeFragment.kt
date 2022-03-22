package com.edgarchavezdev.codechallengeandroidekt.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.edgarchavezdev.codechallengeandroidekt.repository.room.instance.DataBaseEKT
import com.edgarchavezdev.codechallengeandroidekt.repository.room.model.PokemonLocal
import com.edgarchavezdev.codechallengeandroidekt.R
import com.edgarchavezdev.codechallengeandroidekt.adapters.PokemonsAdapter
import com.edgarchavezdev.codechallengeandroidekt.repository.home.repo.HomeRepository
import com.edgarchavezdev.codechallengeandroidekt.utils.getViewModel
import com.edgarchavezdev.codechallengeandroidekt.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {


    private val viewModel : HomeViewModel by lazy {
        getViewModel {
            HomeViewModel(
                HomeRepository(
                    DataBaseEKT.getRoomInstance(requireActivity())
                )
            )
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initData()
    }

    private fun initData() {
        viewModel.getAllPokemons()
    }

    private fun initObservers(){
        viewModel.responseLocalAllPokemons.observe(viewLifecycleOwner, resultData())
        viewModel.error.observe(viewLifecycleOwner, resultError())
    }

    private fun resultData() : (List<PokemonLocal>) -> Unit = { data ->
        val adapterData =  PokemonsAdapter(data, ::listenerAdapter)
        rvPokemons.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = adapterData
        }
    }

    private fun listenerAdapter(pokemonLocal: PokemonLocal) {
        val bundle : Bundle = Bundle().apply {
            putString("Name", pokemonLocal.name)
        }
        findNavController(requireView()).navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }

    private fun resultError():(String?) -> Unit = {
            Toast.makeText(requireContext(), "Ocurrio un error", Toast.LENGTH_LONG).show()
    }
}

