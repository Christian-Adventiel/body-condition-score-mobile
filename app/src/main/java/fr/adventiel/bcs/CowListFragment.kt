package fr.adventiel.bcs

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import fr.adventiel.bcs.adapters.CowAdapter
import fr.adventiel.bcs.databinding.FragmentCowListBinding
import fr.adventiel.bcs.utilities.InjectorUtils
import fr.adventiel.bcs.viewmodels.CowListViewModel


class CowListFragment : Fragment(), SearchView.OnQueryTextListener {

    private val adapter = CowAdapter()

    private val args: CowListFragmentArgs by navArgs()

    private val viewModel: CowListViewModel by viewModels {
        InjectorUtils.provideCowViewModelFactory(this, args.livestockId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCowListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.cowRecyclerView.adapter = adapter
        subscribeUi(adapter)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_cow_list, menu)
        val searchItem = menu.findItem(R.id.search_cow)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }

    private fun subscribeUi(adapter: CowAdapter) {
        viewModel.cows.observe(viewLifecycleOwner) { cows ->
            adapter.submitList(cows)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search_cow -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { viewModel.filter(it) }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        query?.let { viewModel.filter(it) }
        return true
    }
}