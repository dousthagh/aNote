package co.nikavtech.anote.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteDataObject(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private var _id: Int? = null,
    @ColumnInfo(name = "title")
    private var _title: String? = null,
    @ColumnInfo(name = "text")
    private var _text: String? = null,
    @ColumnInfo(name = "created_date")
    private var _insertedDate: Long = System.currentTimeMillis(),
) {
    var id: Int? = _id
        get() = field
        set(value) {
            field = value
        }
    var title: String? = _title
        get() = field
        set(value) {
            field = value
        }
    var text: String? = _text
        get() = field
        set(value) {
            field = value
        }
    var insertedDate: Long = _insertedDate
        get() = field

}