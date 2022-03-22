package com.edgarchavezdev.codechallengeandroidekt.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.edgarchavezdev.codechallengeandroidekt.repository.evolutionchain.model.PokemonEvolutionChainView
import com.edgarchavezdev.codechallengeandroidekt.repository.evolutionchain.repo.EvolutionChainRepository
import com.edgarchavezdev.codechallengeandroidekt.adapters.EvolutionChainAdapter
import com.edgarchavezdev.codechallengeandroidekt.databinding.FragmentEvolutionChainBinding
import com.edgarchavezdev.codechallengeandroidekt.utils.getViewModel
import com.edgarchavezdev.codechallengeandroidekt.viewmodel.EvolutionChainViewModel

class EvolutionChainFragment : Fragment() {

    private lateinit var binding : FragmentEvolutionChainBinding

    private val  viewModel : EvolutionChainViewModel by lazy {
        getViewModel {
            EvolutionChainViewModel(
                EvolutionChainRepository()
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEvolutionChainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
        initData()
    }

    private fun initView() {
        binding.apply {
            titleEvolutionChain = requireArguments().getString("Name", "")
        }
    }

    private fun initObservers() {
        viewModel.responseEvolutionChainPokemon.observe(viewLifecycleOwner, handleEvolutionChain())
    }

    private fun initData() {
        val url = requireArguments().getString("EvolutionUrl", "")
        viewModel.getEvolutionChainPokemon(url)
    }

    private fun handleEvolutionChain(): (List<PokemonEvolutionChainView>) -> Unit = { data ->
        val adapterData =  EvolutionChainAdapter(data, ::listenerAdapter)
        binding.rvEvolutionChain.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = adapterData
        }
    }

    private fun listenerAdapter(item: PokemonEvolutionChainView) {
        //TODO execute save favorite
        println("Click -> $item")
    }
}