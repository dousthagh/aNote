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

class NoteViewModel(val database: NoteDatabase, application: Application) :
    BaseViewModel(application) {

    //region variables
    private val _isSuccessSaveNote = MutableLiveData<Boolean>()
    val isSuccessSaveNote: LiveData<Boolean>
        get() = _isSuccessSaveNote

    private val _isSuccessRemoveNote = MutableLiveData<Boolean>()
    val isSuccessRemoveNote: LiveData<Boolean>
        get() = _isSuccessRemoveNote


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
                    createNoteEntity(false)
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
                    createNoteEntity(true)
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


    fun delete() {
        try {
            uiScope.launch {
                _isSuccessRemoveNote.value = suspendDeleteNote(createNoteEntity(true))
            }
        } catch (exception: Exception) {
            _isSuccessRemoveNote.value = false
        }
    }

    private suspend fun suspendDeleteNote(noteEntity: NoteEntity):Boolean {
        return withContext(Dispatchers.IO) {
            database.noteDao.delete(noteEntity)
            true
        }
    }


    fun resetISuccessSaveNote() {
        _isSuccessSaveNote.postValue(null)
    }

    fun resetISuccessRemoveNote() {
        _isSuccessRemoveNote.postValue(null)
    }

    fun getAllCategories() {
        allCategories = database.categoryDao.getAll()
    }


    fun makeNoteWithCategoryEntity(noteWithCategoryEntity: NoteWithCategoryEntity) {
        noteId.value = noteWithCategoryEntity.noteEntity._id
        noteTitle.value = noteWithCategoryEntity.noteEntity._title
        noteContent.value = noteWithCategoryEntity.noteEntity._text
        categoryId.value = noteWithCategoryEntity.noteEntity.categoryId
    }

    fun resetNoteWithCategoryEntity() {
        makeNoteWithCategoryEntity(
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

    private fun isValidNoteVariables(): Boolean {
        return categoryId.value != -1 && !noteTitle.value.isNullOrEmpty() && !noteContent.value.isNullOrEmpty()
    }

    private fun createNoteEntity(withId: Boolean = true): NoteEntity {
        return NoteEntity(
            _id = if (withId) noteId.value else null,
            _text = noteContent.value,
            _title = noteTitle.value,
            categoryId = categoryId.value
        )
    }

}

