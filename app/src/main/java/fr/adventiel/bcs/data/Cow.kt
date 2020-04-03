package fr.adventiel.bcs.data

import androidx.room.*

@Entity(
    tableName = "cow", foreignKeys = [ForeignKey(
        entity = Livestock::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("livestockId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Cow(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,
    val livestockId: Long,
    val name: String,
    val workNumber: String,
    val nationalNumber: String
)