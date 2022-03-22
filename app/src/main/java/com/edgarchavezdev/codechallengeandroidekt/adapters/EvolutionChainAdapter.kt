package com.edgarchavezdev.codechallengeandroidekt.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edgarchavezdev.codechallengeandroidekt.repository.evolutionchain.model.PokemonEvolutionChainView
import com.edgarchavezdev.codechallengeandroidekt.databinding.ItemEvolutionChainPokemonBinding

class EvolutionChainAdapter(
    private val EvolutionChainList: List<PokemonEvolutionChainView>,
    private val listener: (PokemonEvolutionChainView) -> Unit
) :
    RecyclerView.Adapter<EvolutionChainAdapter.PokemonHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEvolutionChainPokemonBinding.inflate(inflater, parent, false)
        return PokemonHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        holder.bind(EvolutionChainList[position], listener)
    }

    override fun getItemCount(): Int = EvolutionChainList.size

    class PokemonHolder(val binding: ItemEvolutionChainPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: PokemonEvolutionChainView,
            listener: (PokemonEvolutionChainView) -> Unit,
        ) {
            binding.item = item

            binding.root.setOnClickListener {
                listener(item)
            }
        }
    }
}