package co.nikavtech.anote.screens.fragments.note

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.nikavtech.anote.base.BaseViewModel
import co.nikavtech.anote.database.NoteDatabase
import co.nikavtech.anote.database.entities.CategoryEntity
import co.nikavtech.anote.database.entities.NoteEntity
import co.nikavtech.anote.database.entities.NoteWithCategoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class NoteViewModel(val database: NoteDatabase, application: Application) :
    BaseViewModel(application) {

    //region variables
    private val _isSuccessSaveNote = MutableLiveData<Boolean>()
    val isSuccessSaveNote: LiveData<Boolean>
        get() = _isSuccessSaveNote


    val categoryId = MutableLiveData<Int>()
    val noteTitle = MutableLiveData<String>()
    val noteContent = MutableLiveData<String>()
    val noteId = MutableLiveData<Int>()

    var allCategories: LiveData<List<CategoryEntity>>? = null
    //endregion

    init {
        getAllCategories()
        categoryId.value = -1
    }

    fun insertNote() {
        try {
            if (!isValidNoteVariables())
                throw IllegalArgumentException("parameter is null")

            uiScope.launch {
                _isSuccessSaveNote.value = suspendInsertNote(
                    NoteEntity(
                        categoryId = categoryId.value!!,
                        _title = noteTitle.value,
                        _text = noteContent.value,
                    )
                )
                resetNoteWithCategoryEntity()
            }
        } catch (ex: Exception) {
            _isSuccessSaveNote.value = false
        }
    }

    private suspend fun suspendInsertNote(noteEntity: NoteEntity): Boolean {
        return withContext(Dispatchers.IO) {
            database.noteDao.insert(noteEntity)
            true
        }
    }


    fun update() {
        try {
            if (!isValidNoteVariables() || noteId.value == null)
                throw IllegalArgumentException("some parameter is null")
            uiScope.launch {
                _isSuccessSaveNote.value = suspendUpdateNote(
                    NoteEntity(
                        _id = noteId.value,
                        categoryId = categoryId.value!!,
                        _title = noteTitle.value,
                        _text = noteContent.value
                    )
                )
            }
        } catch (ex: Exception) {
            _isSuccessSaveNote.value = false
        }
    }

    private suspend fun suspendUpdateNote(noteEntity: NoteEntity): Boolean {
        return withContext(Dispatchers.IO) {
            database.noteDao.update(noteEntity)
            true
        }
    }

    fun resetISuccessSaveNote() {
        _isSuccessSaveNote.postValue(null)
    }

    fun getAllCategories() {
        allCategories = database.categoryDao.getAll()
    }


    private fun isValidNoteVariables(): Boolean {
        return categoryId.value != -1 && !noteTitle.value.isNullOrEmpty() && !noteContent.value.isNullOrEmpty()
    }

    fun setNoteWithCategoryEntity(noteWithCategoryEntity: NoteWithCategoryEntity) {
        noteId.value = noteWithCategoryEntity.noteEntity._id
        noteTitle.value = noteWithCategoryEntity.noteEntity._title
        noteContent.value = noteWithCategoryEntity.noteEntity._text
        categoryId.value = noteWithCategoryEntity.noteEntity.categoryId
    }
    fun resetNoteWithCategoryEntity(){
        setNoteWithCategoryEntity(
            NoteWithCategoryEntity(
                noteEntity = NoteEntity(
                    _title = null,
                    _text = null,
                    categoryId = -1,
                    _id = null
                ),
                CategoryEntity(
                    id = -1,
                    title = ""
                )
            )
        )
    }
}

