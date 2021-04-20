package co.nikavtech.anote.screens.fragments.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.nikavtech.anote.database.dao.NoteDao
import java.lang.IllegalArgumentException

class HomeViewModelFactory(private val noteDao: NoteDao, private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java))
            return HomeViewModel(noteDao, application) as T

        throw IllegalArgumentException("class type is wrong")
    }
}