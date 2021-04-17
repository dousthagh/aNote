package co.nikavtech.anote.database.dao

import androidx.room.*
import co.nikavtech.anote.database.entities.UserModel

@Dao
interface UserDao {
    @Insert
    fun insert(User: UserModel)

    @Update
    fun update(User: UserModel)

    @Query("select * from users where email = :email and password = :password")
    fun login(email:String, password:String)
}