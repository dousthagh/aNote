package co.nikavtech.anote.database.dao

import androidx.room.Dao
import androidx.room.Query
import co.nikavtech.anote.database.entities.UserEntity

@Dao
interface UserDao : BaseDao<UserEntity> {

    @Query("select * from users where email = :email and password = :password")
    fun login(email: String, password: String): UserEntity?
}