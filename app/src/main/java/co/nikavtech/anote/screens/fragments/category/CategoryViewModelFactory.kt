package co.nikavtech.anote.screens.fragments.category

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.nikavtech.anote.database.dao.CategoryDao
import java.lang.IllegalArgumentException

internal class CategoryViewModelFactory(val categoryDao: CategoryDao, val application: Application) :ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CategoryViewModel::class.java)){
            return CategoryViewModel(categoryDao, application) as T
        }

        throw IllegalArgumentException("ViewModel Type Is Wrong")
    }
}