package fr.adventiel.bcs.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.adventiel.bcs.data.CowRepository

/**
 * Factory for creating a [CowListViewModel] with a constructor that takes a [CowRepository].
 */
class CowListViewModelFactory(
    private val cowRepository: CowRepository,
    private val livestockId: Long
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CowListViewModel(cowRepository, livestockId) as T
    }
}