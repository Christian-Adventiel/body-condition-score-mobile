package fr.adventiel.bcs

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import fr.adventiel.bcs.adapters.LivestockAdapter
import fr.adventiel.bcs.databinding.FragmentLivestockListBinding
import fr.adventiel.bcs.utilities.InjectorUtils
import fr.adventiel.bcs.viewmodels.LivestockListViewModel

class LivestockListFragment : Fragment() {

    private val viewModel: LivestockListViewModel by viewModels {
        InjectorUtils.provideLivestockViewModelFactory(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLivestockListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = LivestockAdapter()
        binding.livestockList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_livestock_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search_livestock -> {
                //TODO: Filter livestocks.
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeUi(adapter: LivestockAdapter) {
        viewModel.livestocks.observe(viewLifecycleOwner) { livestocks ->
            adapter.submitList(livestocks)
        }
    }
}