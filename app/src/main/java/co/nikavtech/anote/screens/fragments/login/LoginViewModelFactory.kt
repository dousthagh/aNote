package co.nikavtech.anote.screens.fragments.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.nikavtech.anote.database.dao.UserDao
import java.lang.IllegalArgumentException

class LoginViewModelFactory(private val userDao: UserDao, private val application: Application):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(userDao, application) as T
        }

        throw IllegalArgumentException("Unknown Class Type ")
    }

}