package co.nikavtech.anote.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

interface BaseDao<in T> {
    @Insert
    fun insert(t: T)

    @Update
    fun update(t: T)

    @Delete
    fun delete(t: T)

}