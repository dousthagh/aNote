package co.nikavtech.anote.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import co.nikavtech.anote.database.entities.CategoryEntity

@Dao
interface CategoryDao : BaseDao<CategoryEntity> {

    @Query("select * from categories order by id desc")
    fun getAll(): LiveData<List<CategoryEntity>>?
}