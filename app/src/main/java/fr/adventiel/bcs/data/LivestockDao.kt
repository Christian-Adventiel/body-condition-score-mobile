package fr.adventiel.bcs.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * The Data Access Object for the [Livestock] class.
 */
@Dao
interface LivestockDao {
    @Query("SELECT * FROM livestock ORDER BY number")
    fun getLivestocks(): LiveData<List<Livestock>>

    @Query("SELECT * FROM livestock WHERE id = :liveStockId")
    fun getLivestock(liveStockId: Long): LiveData<Livestock>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(livestocks: List<Livestock>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(livestock: Livestock): Long
}
