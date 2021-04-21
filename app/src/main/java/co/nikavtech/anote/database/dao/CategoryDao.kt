package co.nikavtech.anote.database.dao

import androidx.room.*
import co.nikavtech.anote.database.entities.CategoryEntity

@Dao
interface CategoryDao {

    @Insert
    fun insert(categoryEntity: CategoryEntity)

    @Update
    fun update(categoryEntity: CategoryEntity)

    @Delete
    fun delete(categoryEntity: CategoryEntity)

    @Query("select * from categories order by id desc")
    fun getAll()
}