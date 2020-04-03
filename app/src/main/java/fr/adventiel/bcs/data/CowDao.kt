package fr.adventiel.bcs.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * The Data Access Object for the [Cow] class.
 */
@Dao
interface CowDao {
    @Query("SELECT * FROM cow WHERE livestockId=:livestockId ORDER BY nationalNumber")
    fun getCows(livestockId: Long): LiveData<List<Cow>>

    @Query("SELECT * FROM cow WHERE id = :cowId")
    fun getCow(cowId: Long): LiveData<Cow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cows: List<Cow>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cow: Cow): Long
}
