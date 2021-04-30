package co.nikavtech.anote.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var email: String? = null,
    var password: String? = null,

    @ColumnInfo(name = "first_name")
    var firstName: String? = null,

    @ColumnInfo(name = "last_name")
    var lastName: String? = null,

    @ColumnInfo(name = "mobile")
    var mobile: String? = null,

){
    fun isCorrect():Boolean{
        return email != null && password != null
    }
}