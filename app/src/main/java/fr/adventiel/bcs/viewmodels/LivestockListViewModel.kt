package fr.adventiel.bcs.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.adventiel.bcs.data.Livestock
import fr.adventiel.bcs.data.LivestockRepository

/**
 * The ViewModel for [LivestockListFragment].
 */
class LivestockListViewModel internal constructor(
    livestockRepository: LivestockRepository
) : ViewModel() {

    val livestocks: LiveData<List<Livestock>> =
        livestockRepository.getLivestocks()
}
