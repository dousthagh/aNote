package co.nikavtech.anote.screens.fragments.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.nikavtech.anote.base.BaseViewModel
import co.nikavtech.anote.database.dao.UserDao
import co.nikavtech.anote.database.entities.UserModel
import kotlinx.coroutines.*

class RegisterViewModel(val userDao: UserDao, application: Application) : BaseViewModel(application) {
    //region variables

    private val _isSuccessRegister = MutableLiveData<Boolean>()
    val isSuccessRegister: LiveData<Boolean>
        get() = _isSuccessRegister

    //endregion


    fun doRegister(userModel: UserModel) {
        uiScope.launch {
            _isSuccessRegister.value = suspendDoRegister(userModel)
        }

    }

    private suspend fun suspendDoRegister(userModel: UserModel): Boolean {
        return withContext(Dispatchers.IO){
            userDao.insert(userModel)
            true
        }
    }

    fun resetISuccessRegister(){
        _isSuccessRegister.postValue(null)
    }
}