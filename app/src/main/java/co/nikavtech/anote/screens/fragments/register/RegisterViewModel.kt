package co.nikavtech.anote.screens.fragments.register

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.nikavtech.anote.base.BaseViewModel
import co.nikavtech.anote.database.dao.UserDao
import co.nikavtech.anote.database.entities.UserEntity
import kotlinx.coroutines.*

class RegisterViewModel(val userDao: UserDao, application: Application) : BaseViewModel(application) {
    //region variables

    private val _isSuccessRegister = MutableLiveData<Boolean>()
    val isSuccessRegister: LiveData<Boolean>
        get() = _isSuccessRegister

    //endregion


    fun doRegister(userEntity: UserEntity) {
        uiScope.launch {
            _isSuccessRegister.value = suspendDoRegister(userEntity)
        }

    }

    private suspend fun suspendDoRegister(userEntity: UserEntity): Boolean {
        return withContext(Dispatchers.IO){
            userDao.insert(userEntity)
            true
        }
    }

    fun resetISuccessRegister(){
        _isSuccessRegister.postValue(null)
    }
}