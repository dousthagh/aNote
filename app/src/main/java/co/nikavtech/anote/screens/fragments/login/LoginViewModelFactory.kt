package co.nikavtech.anote.screens.fragments.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class LoginViewModelFactory(private val userName:String?, private val password:String?):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(userName, password) as T
        }

        throw IllegalArgumentException("Unknown Class Type ")
    }

}