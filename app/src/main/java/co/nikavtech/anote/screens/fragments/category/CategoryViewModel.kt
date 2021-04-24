package co.nikavtech.anote.screens.fragments.category

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.nikavtech.anote.base.BaseViewModel
import co.nikavtech.anote.database.dao.CategoryDao
import co.nikavtech.anote.database.entities.CategoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryViewModel(private val categoryDao: CategoryDao, application: Application) :
    BaseViewModel(application) {

    var allCategories: LiveData<List<CategoryEntity>>? = null

    init {
        getAll()
    }

    fun insert(categoryEntity: CategoryEntity) {
        uiScope.launch {
            suspendInsertToCategoryTable(categoryEntity)
        }
    }

    private suspend fun suspendInsertToCategoryTable(categoryEntity: CategoryEntity) {
        withContext(Dispatchers.IO) {
            categoryDao.insert(categoryEntity)
        }
    }

    fun update(categoryEntity: CategoryEntity) {
        uiScope.launch {
            suspendUpdateCategoryTable(categoryEntity)
        }
    }

    private suspend fun suspendUpdateCategoryTable(categoryEntity: CategoryEntity) {
        withContext(Dispatchers.IO) {
            categoryDao.update(categoryEntity)
        }
    }

    private fun getAll() {
        uiScope.launch {
//            _allCategories.value = suspendGetAll()
            allCategories = suspendGetAll()!!
        }
    }

    private suspend fun suspendGetAll(): LiveData<List<CategoryEntity>>? {
        return withContext(Dispatchers.IO) {
            categoryDao.getAll()
        }
    }
}