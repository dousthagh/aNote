package co.nikavtech.anote.screens.fragments.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.nikavtech.anote.models.NoteDataObject
import co.nikavtech.anote.models.SaveNoteService
import co.nikavtech.anote.services.repository.category.LoadCategoryService

class NoteViewModel : ViewModel() {
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