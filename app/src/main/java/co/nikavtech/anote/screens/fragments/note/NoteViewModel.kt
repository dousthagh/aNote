package co.nikavtech.anote.screens.fragments.note

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.nikavtech.anote.base.BaseViewModel
import co.nikavtech.anote.database.NoteDatabase
import co.nikavtech.anote.database.entities.CategoryEntity
import co.nikavtech.anote.database.entities.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException

class NoteViewModel(val database: NoteDatabase, application: Application) :
    BaseViewModel(application) {

    //region variables
    private val _isSuccessSaveNote = MutableLiveData<Boolean>()
    val isSuccessSaveNote: LiveData<Boolean>
        get() = _isSuccessSaveNote

    private val _note = MutableLiveData<NoteEntity>()
    val note: LiveData<NoteEntity>
        get() = _note

    val categoryId = MutableLiveData<Int>()
    val noteTitle = MutableLiveData<String>()
    val noteContent = MutableLiveData<String>()

    var allCategories: LiveData<List<CategoryEntity>>? = null
    //endregion

    init {
        getAllCategories()
        categoryId.value = -1
    }

    fun saveNote() {
        try {
            if(!isValidNoteVariables())
                throw IllegalArgumentException("parameter is null")

            uiScope.launch {
                _isSuccessSaveNote.value = suspendSaveNote(
                    NoteEntity(
                        categoryId = categoryId.value!!,
                        _title = noteTitle.value,
                        _text = noteContent.value,
                    )
                )
            }
        } catch (ex: Exception) {
            _isSuccessSaveNote.value = false
        }
    }

    private suspend fun suspendSaveNote(noteEntity: NoteEntity): Boolean? {
        return withContext(Dispatchers.IO) {
            database.noteDao.insert(noteEntity)
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
        return categoryId.value != null && noteTitle.value != null && noteContent.value != null
    }
}


