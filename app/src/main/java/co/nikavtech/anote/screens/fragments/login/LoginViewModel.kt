package co.nikavtech.anote.screens.fragments.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.nikavtech.anote.models.UserModel
import co.nikavtech.anote.services.repository.user.LoginService

class LoginViewModel(ctrUserName: String?, ctrPassword: String?) : ViewModel() {
    private var loginService: LoginService = LoginService()

    //region live data
    private val _loginIsSuccess = MutableLiveData<Boolean>()
    val loginIsSuccess: LiveData<Boolean>
        get() = _loginIsSuccess

    val _userName = MutableLiveData<String>()

    //endregion

    init {
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun doLogin(userModel: UserModel) {
        _loginIsSuccess.value = loginService.execute(userModel)
    }

    fun resetIsSuccessLogin() {
        _loginIsSuccess.postValue(false)
    }

    fun setToNullIsSuccessLogin() {
        _loginIsSuccess.postValue(null)
    }
}