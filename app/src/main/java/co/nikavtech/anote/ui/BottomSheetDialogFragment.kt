package co.nikavtech.anote.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import co.nikavtech.anote.R
import co.nikavtech.anote.services.repository.category.LoadCategoryService
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetDialogFragment:BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(
            R.layout.fragment_bottom_sheet_dialog,
            container, false
        )
        val list = view.findViewById<ListView>(R.id.lst_category)
        val adapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, getCategoryTitleArray())
        list.adapter = adapter
        return view
    }

    private fun getCategoryTitleArray(): ArrayList<String> {
        val loadCategoryService = LoadCategoryService()
        val result = arrayListOf<String>()
        loadCategoryService.loadAllCategory().forEach {
            result.add(it.title)
        }
        return result
    }
}