package co.nikavtech.anote.screens.fragments.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.nikavtech.anote.database.dao.UserDao
import co.nikavtech.anote.database.entities.UserModel
import co.nikavtech.anote.services.repository.user.LoginService

class LoginViewModel(userDao: UserDao, application: Application) : ViewModel() {
    private var loginService: LoginService = LoginService()

    //region live data
    private val _loginIsSuccess = MutableLiveData<Boolean>()
    val loginIsSuccess: LiveData<Boolean>
        get() = _loginIsSuccess
    //endregion

    init {
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun doLogin(userModel: UserModel) {
        userModel.email = userModel.email
        _loginIsSuccess.value = loginService.execute(userModel)
    }

    fun resetIsSuccessLogin() {
        _loginIsSuccess.postValue(false)
    }

    fun setToNullIsSuccessLogin() {
        _loginIsSuccess.postValue(null)
    }
}