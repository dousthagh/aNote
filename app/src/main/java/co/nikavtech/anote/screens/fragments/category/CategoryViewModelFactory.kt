package co.nikavtech.anote.screens.fragments.category

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.nikavtech.anote.database.dao.CategoryDao

internal class CategoryViewModelFactory(categoryDao: CategoryDao, application: Application) :ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom())
    }
}