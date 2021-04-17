package co.nikavtech.anote.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserModel(
    @PrimaryKey(autoGenerate = true)
    private var id: Long? = null,
    private var email: String? = null,
    private var password: String? = null
) {
    var _id: Long? = id
        get() = field
        set(value) {
            field = value
        }

    var _email: String? = email
        get() = field
        set(value) {
            field = value
        }

    var _password: String? = password
        get() = field
        set(value) {
            field = value
        }

}
