package co.nikavtech.anote.screens.fragments.category

import android.app.Application
import co.nikavtech.anote.base.BaseViewModel
import co.nikavtech.anote.database.dao.CategoryDao

class CategoryViewModel(val categoryDao: CategoryDao, application: Application): BaseViewModel(application){

}