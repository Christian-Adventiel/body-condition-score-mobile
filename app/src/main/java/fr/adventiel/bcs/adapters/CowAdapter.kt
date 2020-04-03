package fr.adventiel.bcs.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.adventiel.bcs.data.Cow
import fr.adventiel.bcs.databinding.ListItemCowBinding

/**
 * Adapter for the [RecyclerView] in [CowListFragment].
 */
class CowAdapter : ListAdapter<Cow, RecyclerView.ViewHolder>(CowDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CowViewHolder(
            ListItemCowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val cow = getItem(position)
        (holder as CowViewHolder).bind(cow)
    }

    class CowViewHolder(
        private val binding: ListItemCowBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.cow?.let { cow ->
                    navigateToNotationList(cow, it)
                }
            }
        }

        private fun navigateToNotationList(
            cow: Cow,
            view: View
        ) {
            //TODO: Navigate to notations list.
//            val direction =
//                HomeViewPagerFragmentDirections.actionViewPagerFragmentToPlantDetailFragment(
//                    cow.cowId
//                )
//            view.findNavController().navigate(direction)
        }

        fun bind(item: Cow) {
            binding.apply {
                cow = item
                executePendingBindings()
            }
        }
    }
}

//TODO: Callback into constructor.
private class CowDiffCallback : DiffUtil.ItemCallback<Cow>() {
    override fun areItemsTheSame(oldItem: Cow, newItem: Cow): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Cow, newItem: Cow): Boolean {
        return oldItem == newItem
    }
}