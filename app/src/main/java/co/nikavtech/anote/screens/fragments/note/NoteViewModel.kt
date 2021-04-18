package co.nikavtech.anote.screens.fragments.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.nikavtech.anote.database.dao.NoteDao
import co.nikavtech.anote.database.entities.NoteDataObject
import co.nikavtech.anote.database.entities.SaveNoteService
import co.nikavtech.anote.services.repository.category.LoadCategoryService

class NoteViewModel(noteDao: NoteDao, application: Application) : AndroidViewModel(application) {
    private var saveNoteServiceService: SaveNoteService = SaveNoteService()
    private lateinit var loadCategoryService: LoadCategoryService

    private val _isSuccessSaveNote = MutableLiveData<Boolean>()
    val isSuccessSaveNote: LiveData<Boolean>
        get() = _isSuccessSaveNote

    init {

    }

    override fun onCleared() {
        super.onCleared()
    }

    fun saveNote(noteDataObject: NoteDataObject) {
        _isSuccessSaveNote.value = saveNoteServiceService.execute(noteDataObject)
    }

    fun resetISuccessSaveNote(){
        _isSuccessSaveNote.postValue(null)
    }


}