package co.nikavtech.anote.screens.fragments.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.nikavtech.anote.base.BaseViewModel
import co.nikavtech.anote.database.dao.NoteDao
import co.nikavtech.anote.database.entities.NoteWithCategoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(val noteDao: NoteDao, application: Application) : BaseViewModel(application) {

    private var _notes = MutableLiveData<List<NoteWithCategoryEntity>>()
    val notes: LiveData<List<NoteWithCategoryEntity>>
        get() = _notes

    init {
        fetchNotes()
    }

    fun fetchNotes() {
        uiScope.launch {
            _notes.value = suspendLoadNoteFromDatabase()
        }
    }

    private suspend fun suspendLoadNoteFromDatabase(): List<NoteWithCategoryEntity>? {
        return withContext(Dispatchers.IO){
            noteDao.getAll()
        }
    }

}