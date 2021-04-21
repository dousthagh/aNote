package co.nikavtech.anote.screens.fragments.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import co.nikavtech.anote.database.NoteDatabase
import co.nikavtech.anote.databinding.FragmentCategoryBinding
import co.nikavtech.anote.services.repository.category.LoadCategoryService

class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var loadCategoryService: LoadCategoryService
    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        init(inflater, container)
        Log.d("categories", loadCategoryService.loadAllCategory().toString())

        return binding.root
    }

    private fun init(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {

        val application = requireNotNull(this.activity).application
        val dataSource = NoteDatabase.getInstance(application)
        val factory = CategoryViewModelFactory(dataSource.categoryDao, application)

        categoryViewModel = ViewModelProvider(this, factory).get(CategoryViewModel::class.java)
        binding = FragmentCategoryBinding.inflate(inflater, container, false)

        loadCategoryService = LoadCategoryService()
    }
}