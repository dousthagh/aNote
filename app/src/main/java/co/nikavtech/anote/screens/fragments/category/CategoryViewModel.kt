package co.nikavtech.anote.screens.fragments.category

import android.app.Application
import android.util.Log
import co.nikavtech.anote.base.BaseViewModel
import co.nikavtech.anote.database.dao.CategoryDao
import co.nikavtech.anote.database.entities.CategoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryViewModel(val categoryDao: CategoryDao, application: Application): BaseViewModel(application){

    fun insert(categoryEntity: CategoryEntity){
        uiScope.launch {
            suspendInsertToCategoryTable(categoryEntity)
        }
    }

    private suspend fun suspendInsertToCategoryTable(categoryEntity: CategoryEntity) {
        withContext(Dispatchers.IO){
            categoryDao.insert(categoryEntity)
        }
    }

   fun update(categoryEntity: CategoryEntity){
        uiScope.launch {
            suspendUpdateCategoryTable(categoryEntity)
        }
    }

    private suspend fun suspendUpdateCategoryTable(categoryEntity: CategoryEntity) {
        withContext(Dispatchers.IO){
            categoryDao.update(categoryEntity)
        }
    }

}