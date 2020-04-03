package fr.adventiel.bcs.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "livestock")
data class Livestock(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,
    val name: String,
    val number: String
)