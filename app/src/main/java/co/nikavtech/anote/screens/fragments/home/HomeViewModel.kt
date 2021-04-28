package co.nikavtech.anote.screens.fragments.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.nikavtech.anote.base.BaseViewModel
import co.nikavtech.anote.database.dao.NoteDao
import co.nikavtech.anote.database.entities.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(val noteDao: NoteDao, application: Application) : BaseViewModel(application) {

    private var _notes = MutableLiveData<List<NoteEntity>>()
    val notes: LiveData<List<NoteEntity>>
        get() = _notes

    init {
        fetchNotes()
    }

    fun fetchNotes() {
        uiScope.launch {
            _notes.value = suspendLoadNoteFromDatabase()
        }
    }

    private suspend fun suspendLoadNoteFromDatabase(): List<NoteEntity>? {
        return withContext(Dispatchers.IO){
            noteDao.getAll()
        }
    }

}