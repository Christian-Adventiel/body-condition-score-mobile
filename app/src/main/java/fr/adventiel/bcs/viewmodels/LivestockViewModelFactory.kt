package fr.adventiel.bcs.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.adventiel.bcs.data.LivestockRepository

/**
 * Factory for creating a [LivestockListViewModel] with a constructor that takes a [LivestockRepository].
 */
class LivestockListViewModelFactory(
    private val livestockRepository: LivestockRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LivestockListViewModel(livestockRepository) as T
    }
}