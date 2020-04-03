package fr.adventiel.bcs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import fr.adventiel.bcs.adapters.OVERVIEW_PAGE_INDEX
import fr.adventiel.bcs.databinding.FragmentOverviewBinding
import fr.adventiel.bcs.utilities.InjectorUtils
import fr.adventiel.bcs.viewmodels.OverviewViewModel

class OverviewFragment : Fragment() {

    private lateinit var binding: FragmentOverviewBinding

    private val viewModel: OverviewViewModel by viewModels {
        InjectorUtils.provideOverviewViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOverviewBinding.inflate(inflater, container, false)
        subscribeUi(binding)

        return binding.root
    }

    private fun subscribeUi(binding: FragmentOverviewBinding) {
        //TODO: Load data.
        binding.nbLivestock = 10
    }

    // TODO: convert to data binding if applicable
    private fun navigateToLivestockListPage() {
        requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem =
            OVERVIEW_PAGE_INDEX
    }
}
