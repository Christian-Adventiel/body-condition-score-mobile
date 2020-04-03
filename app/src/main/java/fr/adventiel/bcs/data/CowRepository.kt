package fr.adventiel.bcs.data

/**
 * Repository module for handling data operations.
 */
class CowRepository private constructor(private val cowDao: CowDao) {

    fun getCows(livestockId: Long) = cowDao.getCows(livestockId)

    fun getCow(cowId: Long) = cowDao.getCow(cowId)

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: CowRepository? = null

        fun getInstance(cowDao: CowDao) =
            instance ?: synchronized(this) {
                instance ?: CowRepository(cowDao).also { instance = it }
            }
    }
}
