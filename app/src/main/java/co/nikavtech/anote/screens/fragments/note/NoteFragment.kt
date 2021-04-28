package co.nikavtech.anote.screens.fragments.note

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import co.nikavtech.anote.R
import co.nikavtech.anote.database.NoteDatabase
import co.nikavtech.anote.database.entities.CategoryEntity
import co.nikavtech.anote.databinding.FragmentNoteBinding
import co.nikavtech.anote.screens.adapters.category.select_categories.SelectCategoryAdapter

class NoteFragment : Fragment() {
    private lateinit var binding: FragmentNoteBinding
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        init(inflater, container)

        prepareRecyclerView()

//region observation links
        noteViewModel.isSuccessSaveNote.observe(viewLifecycleOwner, {
            it?.let {
                if (it) {
                    Toast.makeText(
                        context,
                        getString(R.string.save_note_successfully),
                        Toast.LENGTH_LONG
                    ).show()
                    binding.txtNote.setText("")
                    binding.txtNoteTitle.setText("")
                } else {
                    Toast.makeText(context, getString(R.string.save_note_error), Toast.LENGTH_LONG)
                        .show()
                }
                noteViewModel.resetISuccessSaveNote()
            }
        })
//endregion


        return binding.root
    }

    private fun getShareIntent(): Intent {
        val text = noteViewModel.noteContent.value
        return ShareCompat.IntentBuilder.from(activity!!)
            .setText(text)
            .setType("text/plain")
            .intent
    }

    private fun init(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = NoteDatabase.getInstance(application)
        val factory = NoteViewModelFactory(dataSource, application)
        noteViewModel = ViewModelProvider(activity!!, factory).get(NoteViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = noteViewModel

        setHasOptionsMenu(true)
    }

    private fun showShareIntent() {
        startActivity(getShareIntent())
    }

    private fun prepareRecyclerView() {
        val linearLayoutManager =
            LinearLayoutManager(requireNotNull(this.activity).applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.rvSelectedCategories.layoutManager = linearLayoutManager

        val categoryAdapter = SelectCategoryAdapter(SelectCategoryAdapter.DIFF_CALLBACK)


        noteViewModel.allCategories?.observe(activity!!, {
            categoryAdapter.submitList(it)
            categoryAdapter.notifyDataSetChanged()
        })

        binding.rvSelectedCategories.adapter = categoryAdapter

        categoryAdapter.setOnItemClickListener(object : SelectCategoryAdapter.OnItemClickListener {
            override fun onItemClick(category: CategoryEntity?) {
                if (category?.id != null) {
                    noteViewModel.note.value?.categoryId = category.id!!
                }
            }
        })
    }

    private fun save() {
        noteViewModel.saveNote()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_note_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save -> save()
            R.id.share -> showShareIntent()
        }

        return true
    }

    override fun onResume() {
        super.onResume()
        noteViewModel.getAllCategories()
    }

}