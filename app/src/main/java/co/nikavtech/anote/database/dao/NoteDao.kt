package co.nikavtech.anote.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import co.nikavtech.anote.database.entities.NoteWithCategoryEntity
import co.nikavtech.anote.database.entities.NoteEntity

@Dao
interface NoteDao : BaseDao<NoteEntity> {

    @Transaction
    @Query("select * from notes order by id desc")
    fun getAll(): List<NoteWithCategoryEntity>

    @Query("select * from notes where id = :id")
    fun getNote(id: Long): NoteEntity
}