package co.nikavtech.anote.screens.fragments.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.nikavtech.anote.database.NoteDatabase
import co.nikavtech.anote.database.entities.CategoryEntity
import co.nikavtech.anote.databinding.FragmentCategoryBinding
import co.nikavtech.anote.screens.adapters.category.CategoryAdapter

class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var supportFragment:FragmentManager


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
                supportFragment,
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


        supportFragment = requireNotNull(this.activity).supportFragmentManager
        categoryViewModel = ViewModelProvider(activity!!, factory).get(CategoryViewModel::class.java)
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
    }

    private fun prepareRecyclerView() {
        val linearLayoutManager =
            LinearLayoutManager(requireNotNull(this.activity).applicationContext)
        binding.rvCategories.layoutManager = linearLayoutManager
        binding.rvCategories.setHasFixedSize(true)

        val categoryAdapter = CategoryAdapter(CategoryAdapter.DIFF_CALLBACK)

        categoryViewModel.allCategories?.observe(viewLifecycleOwner, {
            categoryAdapter.submitList(it)
            categoryAdapter.notifyDataSetChanged()
        })

        binding.rvCategories.adapter = categoryAdapter

        categoryAdapter.setOnItemClickListener(onCategoryItemClickListener(supportFragment))



        ItemTouchHelper(object : SimpleCallback(0, LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                categoryAdapter.getCategoryAt(viewHolder.adapterPosition)?.let {
                    categoryViewModel.delete(
                        it
                    )
                }
                //todo show snakbar for double check
            }
        }).attachToRecyclerView(binding.rvCategories)

    }


    private class onCategoryItemClickListener(val supportFragmentManager: FragmentManager) : CategoryAdapter.OnItemClickListener{
        override fun onItemClick(category: CategoryEntity?) {
            val saveCategoryFragment = SaveCategoryFragment(category)
            saveCategoryFragment.show(
                supportFragmentManager,
                saveCategoryFragment.javaClass.simpleName
            )
        }
    }

}