package fr.adventiel.bcs.data

/**
 * Repository module for handling data operations.
 */
class LivestockRepository private constructor(private val livestockDao: LivestockDao) {

    fun getLivestocks() = livestockDao.getLivestocks()

    fun getLivestock(livestockId: Long) = livestockDao.getLivestock(livestockId)

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: LivestockRepository? = null

        fun getInstance(livestockDao: LivestockDao) =
            instance ?: synchronized(this) {
                instance ?: LivestockRepository(livestockDao).also { instance = it }
            }
    }
}
