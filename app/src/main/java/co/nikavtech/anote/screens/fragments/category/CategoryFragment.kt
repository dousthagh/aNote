package co.nikavtech.anote.screens.fragments.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.nikavtech.anote.databinding.FragmentCategoryBinding
import co.nikavtech.anote.services.repository.category.LoadCategoryService

class CategoryFragment :Fragment(){
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var loadCategoryService: LoadCategoryService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        init(inflater, container)
        Log.d("categories", loadCategoryService.loadAllCategory().toString())

        return binding.root
    }

    private fun init(inflater: LayoutInflater,
                     container: ViewGroup?){
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        loadCategoryService = LoadCategoryService()
    }
}