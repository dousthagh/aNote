package co.nikavtech.anote.screens.fragments.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.nikavtech.anote.base.BaseViewModel
import co.nikavtech.anote.database.NoteDatabase
import co.nikavtech.anote.database.dao.NoteDao
import co.nikavtech.anote.database.entities.NoteDataObject
import co.nikavtech.anote.services.repository.note.LoadNoteService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(val noteDao: NoteDao, application: Application) : BaseViewModel(application) {
    private var loadNoteService: LoadNoteService = LoadNoteService()

    private var _notes = MutableLiveData<List<NoteDataObject>>()
    val notes: LiveData<List<NoteDataObject>>
        get() = _notes

    init {
        fetchNotes()
    }

    private fun fetchNotes() {
        uiScope.launch {
            _notes.value = suspendLoadNoteFromDatabase()
            Log.d("asd", _notes.value.toString())
        }
    }

    private suspend fun suspendLoadNoteFromDatabase(): List<NoteDataObject>? {
        return withContext(Dispatchers.IO){
            noteDao.getAll().value
        }
    }

}