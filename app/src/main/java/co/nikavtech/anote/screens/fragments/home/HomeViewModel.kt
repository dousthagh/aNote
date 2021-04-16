package co.nikavtech.anote.screens.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.nikavtech.anote.models.NoteDataObject
import co.nikavtech.anote.services.repository.note.LoadNoteService

class HomeViewModel : ViewModel() {
    private var loadNoteService: LoadNoteService = LoadNoteService()

    private val _notes = MutableLiveData<List<NoteDataObject>>()
    val notes: LiveData<List<NoteDataObject>>
        get() = _notes

    init {
        fetchNotes()
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun fetchNotes() {
        _notes.value = loadNoteService.loadAllNotes()
    }
}