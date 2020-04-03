package fr.adventiel.bcs.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.adventiel.bcs.HomeViewPagerFragmentDirections
import fr.adventiel.bcs.data.Livestock
import fr.adventiel.bcs.databinding.ListItemLivestockBinding

/**
 * Adapter for the [RecyclerView] in [LivestockListFragment].
 */
class LivestockAdapter : ListAdapter<Livestock, RecyclerView.ViewHolder>(LivestockDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LivestockViewHolder(
            ListItemLivestockBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val livestock = getItem(position)
        (holder as LivestockViewHolder).bind(livestock)
    }

    class LivestockViewHolder(
        private val binding: ListItemLivestockBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.livestock?.let { livestock ->
                    navigateToCowList(livestock, it)
                }
            }
        }

        private fun navigateToCowList(
            livestock: Livestock,
            view: View
        ) {
            val direction =
                HomeViewPagerFragmentDirections.actionHomeViewPagerFragmentToCowListFragment(
                    livestock.id
                )
            view.findNavController().navigate(direction)
        }

        fun bind(item: Livestock) {
            binding.apply {
                livestock = item
                executePendingBindings()
            }
        }
    }
}

//TODO: Callback into constructor.
private class LivestockDiffCallback : DiffUtil.ItemCallback<Livestock>() {

    override fun areItemsTheSame(oldItem: Livestock, newItem: Livestock): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Livestock, newItem: Livestock): Boolean {
        return oldItem == newItem
    }
}