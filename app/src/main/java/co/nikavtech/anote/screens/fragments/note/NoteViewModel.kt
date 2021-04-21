package co.nikavtech.anote.screens.fragments.note

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.nikavtech.anote.base.BaseViewModel
import co.nikavtech.anote.database.dao.NoteDao
import co.nikavtech.anote.database.entities.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel(val noteDao: NoteDao, application: Application) : BaseViewModel(application) {
    private val _isSuccessSaveNote = MutableLiveData<Boolean>()
    val isSuccessSaveNote: LiveData<Boolean>
        get() = _isSuccessSaveNote

    init {

    }

    fun saveNote(noteEntity: NoteEntity) {
        uiScope.launch{
            _isSuccessSaveNote.value = suspendSaveNote(noteEntity)
        }
    }

    private suspend fun suspendSaveNote(noteEntity: NoteEntity): Boolean? {
        return withContext(Dispatchers.IO){
            noteDao.insert(noteEntity)
            true
        }
    }


    fun resetISuccessSaveNote(){
        _isSuccessSaveNote.postValue(null)
    }


}