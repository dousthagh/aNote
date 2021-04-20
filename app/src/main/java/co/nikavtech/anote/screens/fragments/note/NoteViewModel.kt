package co.nikavtech.anote.screens.fragments.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.nikavtech.anote.base.BaseViewModel
import co.nikavtech.anote.database.dao.NoteDao
import co.nikavtech.anote.database.entities.NoteDataObject
import co.nikavtech.anote.database.entities.SaveNoteService
import co.nikavtech.anote.services.repository.category.LoadCategoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel(val noteDao: NoteDao, application: Application) : BaseViewModel(application) {
    private val _isSuccessSaveNote = MutableLiveData<Boolean>()
    val isSuccessSaveNote: LiveData<Boolean>
        get() = _isSuccessSaveNote

    init {

    }

    fun saveNote(noteDataObject: NoteDataObject) {
        uiScope.launch{
            _isSuccessSaveNote.value = suspendSaveNote(noteDataObject)
        }
    }

    private suspend fun suspendSaveNote(noteDataObject: NoteDataObject): Boolean? {
        return withContext(Dispatchers.IO){
            noteDao.insert(noteDataObject)
            true
        }
    }


    fun resetISuccessSaveNote(){
        _isSuccessSaveNote.postValue(null)
    }


}