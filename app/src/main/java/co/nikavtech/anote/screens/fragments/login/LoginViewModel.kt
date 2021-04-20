package co.nikavtech.anote.screens.fragments.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.nikavtech.anote.base.BaseViewModel
import co.nikavtech.anote.database.dao.UserDao
import co.nikavtech.anote.database.entities.UserModel
import kotlinx.coroutines.*

class LoginViewModel(private val userDao: UserDao, application: Application) :
    BaseViewModel(application) {

    //region variables
    private val _loginUserModel = MutableLiveData<UserModel>()
    val loginUserModel: LiveData<UserModel>
        get() = _loginUserModel
    //endregion

    init {
    }


    fun doLogin(userModel: UserModel) {
        if (userModel.isCorrect()) {
            uiScope.launch {
                _loginUserModel.value = suspendLogin(userModel)
            }
        }
    }

    private suspend fun suspendLogin(userModel: UserModel): UserModel? {
        return withContext(Dispatchers.IO) {
            userDao.login(userModel.email!!, userModel.password!!)
        }
    }

}