package co.nikavtech.anote.screens.fragments.profile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.nikavtech.anote.database.dao.UserDao

class ProfileViewModelFactory(val userDao: UserDao, val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            return ProfileViewModel(userDao, application) as T
        }

        throw IllegalArgumentException("parameter type is wrong")
    }
}