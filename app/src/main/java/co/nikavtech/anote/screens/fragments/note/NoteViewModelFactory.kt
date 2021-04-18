package co.nikavtech.anote.screens.fragments.note

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.nikavtech.anote.database.dao.NoteDao
import java.lang.IllegalArgumentException

class NoteViewModelFactory(private val noteDao: NoteDao, private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NoteViewModel::class.java)){
            return NoteViewModel(noteDao, application) as T
        }

        throw IllegalArgumentException("type is not correct")
    }

}