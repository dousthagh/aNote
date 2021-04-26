package co.nikavtech.anote.screens.fragments.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import co.nikavtech.anote.database.NoteDatabase
import co.nikavtech.anote.databinding.FragmentCategoryBinding
import co.nikavtech.anote.screens.adapters.category.CategoryAdapter
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

        prepareRecyclerView()

        binding.fabShowSaveContainer.setOnClickListener {
            val saveCategoryFragment = SaveCategoryFragment(null)
            saveCategoryFragment.show(
                requireNotNull(this.activity).supportFragmentManager,
                saveCategoryFragment.javaClass.simpleName
            )
        }

        return binding.root
    }

    private fun init(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {

        val application = requireNotNull(this.activity).application
        val dataSource = NoteDatabase.getInstance(application)
        val factory = CategoryViewModelFactory(dataSource.categoryDao, application)


        categoryViewModel = ViewModelProvider(activity!!, factory).get(CategoryViewModel::class.java)
        binding = FragmentCategoryBinding.inflate(inflater, container, false)

        loadCategoryService = LoadCategoryService()

    }

    private fun prepareRecyclerView() {
        val linearLayoutManager =
            LinearLayoutManager(requireNotNull(this.activity).applicationContext)
        binding.rvCategories.layoutManager = linearLayoutManager
        binding.rvCategories.setHasFixedSize(true)

        val categoryAdapter = CategoryAdapter(CategoryAdapter.DIFF_CALLBACK)

        categoryViewModel.allCategories?.observe(viewLifecycleOwner, {
            categoryAdapter.submitList(it)
        })

        binding.rvCategories.adapter = categoryAdapter

    }
}