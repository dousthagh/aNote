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

class HomeViewModel(private val noteDao: NoteDao, application: Application) : BaseViewModel(application) {

    var notesWithCategory: LiveData<List<NoteWithCategoryEntity>>? = null

    init {
        fetchNotesWithCategory()
    }

    fun fetchNotesWithCategory() {
        notesWithCategory = noteDao.getAllWithCategories()
    }



}