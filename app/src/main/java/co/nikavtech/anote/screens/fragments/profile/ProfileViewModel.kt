package co.nikavtech.anote.screens.fragments.profile

import android.app.Application
import co.nikavtech.anote.base.BaseViewModel
import co.nikavtech.anote.database.dao.UserDao

class ProfileViewModel(private val userDao: UserDao, application: Application) : BaseViewModel(application) {

}