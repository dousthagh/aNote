package co.nikavtech.anote.screens.fragments.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.nikavtech.anote.base.BaseViewModel
import co.nikavtech.anote.database.dao.UserDao
import co.nikavtech.anote.database.entities.UserEntity
import kotlinx.coroutines.*

class LoginViewModel(private val userDao: UserDao, application: Application) :
    BaseViewModel(application) {

    //region variables
    private val _loginUserModel = MutableLiveData<UserEntity>()
    val loginUserModel: LiveData<UserEntity>
        get() = _loginUserModel
    //endregion

    init {
    }


    fun doLogin(userEntity: UserEntity) {
        if (userEntity.isCorrect()) {
            uiScope.launch {
                _loginUserModel.value = suspendLogin(userEntity)
            }
        }
    }

    private suspend fun suspendLogin(userEntity: UserEntity): UserEntity? {
        return withContext(Dispatchers.IO) {
            userDao.login(userEntity.email!!, userEntity.password!!)
        }
    }

}