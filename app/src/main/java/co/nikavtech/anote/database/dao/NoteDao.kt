package co.nikavtech.anote.database.dao

import androidx.room.Dao
import androidx.room.Query
import co.nikavtech.anote.database.entities.NoteEntity

@Dao
interface NoteDao : BaseDao<NoteEntity> {

    @Query("select * from notes order by id desc")
    fun getAll(): List<NoteEntity>

    @Query("select * from notes where id = :id")
    fun getNote(id: Long): NoteEntity
}