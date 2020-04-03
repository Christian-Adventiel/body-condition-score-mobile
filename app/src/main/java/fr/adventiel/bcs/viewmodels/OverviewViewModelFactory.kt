package fr.adventiel.bcs.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * TODO: Update according to used repository.
 * Factory for creating a [OverviewViewModel] with a constructor that takes a [Repository].
 */
class OverviewViewModelFactory(
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OverviewViewModel() as T
    }
}