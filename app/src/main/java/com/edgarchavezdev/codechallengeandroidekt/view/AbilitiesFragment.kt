package com.edgarchavezdev.codechallengeandroidekt.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.edgarchavezdev.codechallengeandroidekt.adapters.AbilitiesAdapter
import com.edgarchavezdev.codechallengeandroidekt.repository.abilities.model.AbilityPokemonView
import com.edgarchavezdev.codechallengeandroidekt.repository.abilities.repo.AbilitiesRepository
import com.edgarchavezdev.codechallengeandroidekt.databinding.FragmentAbilitiesBinding
import com.edgarchavezdev.codechallengeandroidekt.utils.getViewModel
import com.edgarchavezdev.codechallengeandroidekt.viewmodel.AbilitiesViewModel

class AbilitiesFragment : Fragment() {

    private lateinit var binding: FragmentAbilitiesBinding

    private val viewModel : AbilitiesViewModel by lazy {
        getViewModel {
            AbilitiesViewModel(
                AbilitiesRepository()
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAbilitiesBinding.inflate(inflater, container, false)
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
            titleAbilities = requireArguments().getString("Name", "")
        }
    }

    private fun initData() {
        val pokemonName = requireArguments().getString("Name", "")
        viewModel.getAbilitiesPokemon(pokemonName)
    }

    private fun initObservers() {
        viewModel.responseAbilityPokemon.observe(viewLifecycleOwner, handleAbilities())
    }

    private fun handleAbilities():  (List<AbilityPokemonView>) -> Unit = { data ->
        val adapterData =  AbilitiesAdapter(data, ::listenerAdapter)
        binding.apply {
            rvAbilities.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = adapterData
            }
        }
    }

    private fun listenerAdapter(abilityPokemonView: AbilityPokemonView) {}
}