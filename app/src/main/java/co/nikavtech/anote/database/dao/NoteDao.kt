package co.nikavtech.anote.database.dao

import androidx.room.*
import co.nikavtech.anote.database.entities.NoteEntity

@Dao
interface NoteDao {
    @Insert
    fun insert(noteEntity: NoteEntity)

    @Update
    fun update(noteEntity: NoteEntity)

    @Delete
    fun delete(noteEntity: NoteEntity)

    @Query("select * from notes order by id desc")
    fun getAll() : List<NoteEntity>

    @Query("select * from notes where id = :id")
    fun getNote(id:Long) : NoteEntity
}