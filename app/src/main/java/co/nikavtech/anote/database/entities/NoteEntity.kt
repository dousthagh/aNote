package co.nikavtech.anote.database.entities

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var _id: Int? = null,

    @ColumnInfo(name = "category_id")
    var categoryId: Int? = -1,

    @ColumnInfo(name = "title")
    var _title: String? = null,

    @ColumnInfo(name = "text")
    var _text: String? = null,

    @ColumnInfo(name = "created_date")
    val _insertedDate: Long = System.currentTimeMillis(),

    ):Serializable {
    fun isValidate(): Boolean {
        return _id != null && _title != null && _text != null
    }
}
