package fr.adventiel.bcs.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import fr.adventiel.bcs.data.Cow
import fr.adventiel.bcs.data.CowRepository

/**
 * The ViewModel for [CowListFragment].
 */
class CowListViewModel internal constructor(
    cowRepository: CowRepository,
    private val livestockId: Long
) : ViewModel() {

    var cows: LiveData<List<Cow>> = cowRepository.getCows(livestockId)

    fun filter(filterString: String) {
        cows = Transformations.map(cows) { it ->
            it.filter {
                it.name.contains(filterString)
            }
        }
    }
}
