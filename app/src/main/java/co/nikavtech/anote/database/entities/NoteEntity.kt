package co.nikavtech.anote.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var _id: Int? = null,
    @ColumnInfo(name = "title")
    var _title: String? = null,
    @ColumnInfo(name = "text")
    var _text: String? = null,
    @ColumnInfo(name = "created_date")
    var _insertedDate: Long = System.currentTimeMillis(),
)
