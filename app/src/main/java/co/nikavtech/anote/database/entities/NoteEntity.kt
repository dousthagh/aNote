package co.nikavtech.anote.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var _id: Int? = null,
    @ColumnInfo(name = "category_id")
    var categoryId: Int,
    @ColumnInfo(name = "title")
    var _title: String? = null,
    @ColumnInfo(name = "text")
    var _text: String? = null,
    @ColumnInfo(name = "created_date")
    val _insertedDate: Long = System.currentTimeMillis(),
){
    fun isValidate() : Boolean{
        return _id != null && _title != null && _text != null
    }
}
