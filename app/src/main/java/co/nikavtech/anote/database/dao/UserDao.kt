package co.nikavtech.anote.database.dao

import androidx.room.*
import co.nikavtech.anote.database.entities.UserEntity

@Dao
interface UserDao {
    @Insert
    fun insert(user: UserEntity)

    @Update
    fun update(user: UserEntity)

    @Query("select * from users where email = :email and password = :password")
    fun login(email:String, password:String) : UserEntity?
}