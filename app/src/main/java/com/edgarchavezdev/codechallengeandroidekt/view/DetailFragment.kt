package com.edgarchavezdev.codechallengeandroidekt.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.edgarchavezdev.codechallengeandroidekt.repository.detail.model.PokemonDetailView
import com.edgarchavezdev.codechallengeandroidekt.repository.detail.repo.DetailRepository
import com.edgarchavezdev.codechallengeandroidekt.R
import com.edgarchavezdev.codechallengeandroidekt.databinding.FragmentDetailBinding
import com.edgarchavezdev.codechallengeandroidekt.utils.getViewModel
import com.edgarchavezdev.codechallengeandroidekt.viewmodel.DetailViewModel

class DetailFragment : Fragment() {

    lateinit var binding : FragmentDetailBinding

    private val viewModel : DetailViewModel by lazy {
        getViewModel {
            DetailViewModel(
                DetailRepository()
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActions()
        initObservers()
        initData()
    }

    private fun initData() {
        val data = requireArguments().getString("Name") ?: ""
        viewModel.getDetailPokemon(data)
    }

    private fun initObservers() {
        viewModel.responseDetailPokemon.observe(viewLifecycleOwner, handleDetail())
    }

    private fun handleDetail() : (PokemonDetailView) -> Unit = {
        binding.apply {
            item = it
        }
    }

    private fun initActions(){
        binding.apply {
            btnAbilities.setOnClickListener(::navigationToAbilities)
            btnEvolutionChain.setOnClickListener(::navigationToEvolutionChain)
        }
    }

    private fun navigationToAbilities(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_detailFragment_to_abilitiesFragment, requireArguments())
    }

    private fun navigationToEvolutionChain(view: View) {
        val bundle : Bundle = Bundle().apply {
            putString("EvolutionUrl" , binding.item?.evolutionUrl ?: "")
            putString("Name" , binding.item?.name ?: "")
        }
        Navigation.findNavController(view).navigate(R.id.action_detailFragment_to_evolutionChainFragment, bundle)
    }
}