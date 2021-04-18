package co.nikavtech.anote.screens.fragments.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.nikavtech.anote.database.dao.UserDao
import co.nikavtech.anote.database.entities.UserModel
import co.nikavtech.anote.services.repository.user.RegisterService

class RegisterViewModel(userDao: UserDao, application: Application) : AndroidViewModel(application) {
    private lateinit var registerService: RegisterService


    private val _isSuccessRegister = MutableLiveData<Boolean>()
    val isSuccessRegister: LiveData<Boolean>
        get() = _isSuccessRegister

    init {
        registerService = RegisterService()

    }

    override fun onCleared() {
        super.onCleared()
    }

    fun doRegister(userModel: UserModel) {
        _isSuccessRegister.value = registerService.register(userModel)
    }

    fun resetISuccessRegister(){
        _isSuccessRegister.postValue(null)
    }
}