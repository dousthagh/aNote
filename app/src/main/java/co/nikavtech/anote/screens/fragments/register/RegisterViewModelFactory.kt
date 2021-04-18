package co.nikavtech.anote.screens.fragments.register

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.nikavtech.anote.database.dao.UserDao

class RegisterViewModelFactory (private val userDao: UserDao, private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RegisterViewModel::class.java)){
            return RegisterViewModel(userDao, application) as T
        }

        throw IllegalArgumentException("ViewModel Type is not correct")
    }
}