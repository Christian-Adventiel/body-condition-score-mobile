package fr.adventiel.bcs.utilities

import android.content.Context
import androidx.fragment.app.Fragment
import fr.adventiel.bcs.data.AppDatabase
import fr.adventiel.bcs.data.CowRepository
import fr.adventiel.bcs.data.LivestockRepository
import fr.adventiel.bcs.viewmodels.CowListViewModelFactory
import fr.adventiel.bcs.viewmodels.LivestockListViewModelFactory
import fr.adventiel.bcs.viewmodels.OverviewViewModelFactory

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {

    // Repositories.
    private fun getLivestockRepository(context: Context): LivestockRepository {
        return LivestockRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).livestockDao()
        )
    }

    private fun getCowRepository(context: Context): CowRepository {
        return CowRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).cowDao()
        )
    }

    // Providers.
    fun provideLivestockViewModelFactory(fragment: Fragment): LivestockListViewModelFactory {
        val repository = getLivestockRepository(fragment.requireContext())
        return LivestockListViewModelFactory(repository)
    }

    fun provideCowViewModelFactory(fragment: Fragment, livestockId: Long): CowListViewModelFactory {
        val repository = getCowRepository(fragment.requireContext())
        return CowListViewModelFactory(repository, livestockId)
    }


    fun provideOverviewViewModelFactory(
        context: Context
    ): OverviewViewModelFactory {
        //TODO: Inject needed repository.
        return OverviewViewModelFactory()
    }
}
