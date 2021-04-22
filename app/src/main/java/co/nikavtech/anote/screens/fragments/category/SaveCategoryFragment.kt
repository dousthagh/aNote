package co.nikavtech.anote.screens.fragments.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import co.nikavtech.anote.R
import co.nikavtech.anote.database.NoteDatabase
import co.nikavtech.anote.database.entities.CategoryEntity
import co.nikavtech.anote.databinding.FragmentSaveCategoryBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SaveCategoryFragment(val categoryEntity: CategoryEntity?) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentSaveCategoryBinding
    private lateinit var viewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        init(inflater, container)

        prepareView()

        binding.btnSave.setOnClickListener {
            val newCategoryEntity = setupCategoryEntity()
            if (categoryEntity != null)
                newCategoryEntity?.let { it1 -> viewModel.update(it1) }
            else
                newCategoryEntity?.let { it1 -> viewModel.insert(it1) }

        }
        return binding.root
    }

    private fun init(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentSaveCategoryBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = NoteDatabase.getInstance(application)
        val factory = CategoryViewModelFactory(dataSource.categoryDao, application)
        viewModel = ViewModelProvider(this, factory).get(CategoryViewModel::class.java)
    }

    private fun prepareView() {
        if (categoryEntity != null) {
            binding.tvCategoryTitle.setText(categoryEntity.title)
        }
    }

    private fun setupCategoryEntity(): CategoryEntity? {
        val title = binding.tvCategoryTitle.text.toString()
        if (title.isEmpty()) {
            Toast.makeText(
                context,
                requireNotNull(context).getString(R.string.error_category_title_is_empty),
                Toast.LENGTH_LONG
            ).show()
            return null
        }

        return if (categoryEntity != null) {
            categoryEntity.title = title
            categoryEntity
        } else {
            CategoryEntity(
                title = title,
            )
        }

    }

}