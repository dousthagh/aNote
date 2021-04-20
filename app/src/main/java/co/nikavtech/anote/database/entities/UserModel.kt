package co.nikavtech.anote.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserModel(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var email: String? = null,
    var password: String? = null
){
    fun isCorrect():Boolean{
        return email != null && password != null
    }
}