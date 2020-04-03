package fr.adventiel.bcs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import fr.adventiel.bcs.adapters.BcsPagerAdapter
import fr.adventiel.bcs.adapters.LIVESTOCK_LIST_PAGE_INDEX
import fr.adventiel.bcs.adapters.OVERVIEW_PAGE_INDEX
import fr.adventiel.bcs.databinding.FragmentViewPagerBinding

class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = BcsPagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            OVERVIEW_PAGE_INDEX -> R.drawable.overview_tab_selector
            LIVESTOCK_LIST_PAGE_INDEX -> R.drawable.livestock_list_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            OVERVIEW_PAGE_INDEX -> getString(R.string.overview_title)
            LIVESTOCK_LIST_PAGE_INDEX -> getString(R.string.livestock_list_title)
            else -> null
        }
    }
}