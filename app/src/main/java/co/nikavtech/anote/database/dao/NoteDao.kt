package co.nikavtech.anote.database.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import co.nikavtech.anote.database.entities.NoteDataObject

@Dao
interface NoteDao {
    @Insert
    fun insert(noteDataObject: NoteDataObject)

    @Update
    fun update(noteDataObject: NoteDataObject)

    @Delete
    fun delete(noteDataObject: NoteDataObject)

    @Query("select * from notes order by id desc")
    fun getAll() : List<NoteDataObject>

    @Query("select * from notes where id = :id")
    fun getNote(id:Long) : NoteDataObject
}