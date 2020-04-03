package fr.adventiel.bcs.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import fr.adventiel.bcs.LivestockListFragment
import fr.adventiel.bcs.OverviewFragment

const val OVERVIEW_PAGE_INDEX = 0
const val LIVESTOCK_LIST_PAGE_INDEX = 1

class BcsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        OVERVIEW_PAGE_INDEX to { OverviewFragment() },
        LIVESTOCK_LIST_PAGE_INDEX to { LivestockListFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}