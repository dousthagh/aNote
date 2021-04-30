package co.nikavtech.anote.screens.fragments.note

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import co.nikavtech.anote.R
import co.nikavtech.anote.database.NoteDatabase
import co.nikavtech.anote.database.entities.CategoryEntity
import co.nikavtech.anote.database.entities.NoteWithCategoryEntity
import co.nikavtech.anote.databinding.FragmentNoteBinding
import co.nikavtech.anote.screens.adapters.category.select_categories.SelectCategoryAdapter

class NoteFragment : Fragment() {
    private lateinit var binding: FragmentNoteBinding
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var categoryAdapter: SelectCategoryAdapter
    private var receivedNoteWithCategoryEntity: NoteWithCategoryEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        init(inflater, container)

        prepareMenu()

        prepareRecyclerView()

        prepareInputs()

//region observation links
        noteViewModel.isSuccessSaveNote.observe(viewLifecycleOwner, {
            it?.let {
                if (it) {
                    Toast.makeText(
                        context,
                        getString(R.string.save_note_successfully),
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(context, getString(R.string.save_note_error), Toast.LENGTH_LONG)
                        .show()
                }
                noteViewModel.resetISuccessSaveNote()
            }
        })

        noteViewModel.noteTitle.observe(viewLifecycleOwner, {
            (activity as AppCompatActivity).supportActionBar?.title = it
        })

        noteViewModel.categoryId.observe(viewLifecycleOwner, {
            categoryAdapter.setSelectCategoryItem(it)
            categoryAdapter.notifyDataSetChanged()
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

    }

    private fun showShareIntent() {
        startActivity(getShareIntent())
    }

    private fun prepareMenu() {
        setHasOptionsMenu(true)
    }

    private fun prepareRecyclerView() {
        val linearLayoutManager =
            LinearLayoutManager(requireNotNull(this.activity).applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.rvSelectedCategories.layoutManager = linearLayoutManager

        categoryAdapter = SelectCategoryAdapter(SelectCategoryAdapter.DIFF_CALLBACK)


        noteViewModel.allCategories?.observe(activity!!, {
            categoryAdapter.submitList(it)
            categoryAdapter.notifyDataSetChanged()
        })

        binding.rvSelectedCategories.adapter = categoryAdapter

        categoryAdapter.setOnItemClickListener(object : SelectCategoryAdapter.OnItemClickListener {
            override fun onItemClick(category: CategoryEntity?) {
                if (category?.id != null) {
                    noteViewModel.categoryId.value = category.id!!
                }
            }
        })
    }

    private fun prepareInputs() {
        try {
            receivedNoteWithCategoryEntity = getBundleArguments().noteWithCategory
            if(receivedNoteWithCategoryEntity == null)
                throw IllegalArgumentException("receivedNoteWithCategoryEntity is null")

            receivedNoteWithCategoryEntity?.let {
                noteViewModel.setNoteWithCategoryEntity(it)
            }
        } catch (ex: Exception) {
            noteViewModel.resetNoteWithCategoryEntity()
        }
    }

    private fun save() {
        if(receivedNoteWithCategoryEntity == null)
            noteViewModel.insertNote()
        else{
            noteViewModel.update()
        }
    }

    private fun getBundleArguments(): NoteFragmentArgs {
        return NoteFragmentArgs.fromBundle(arguments!!)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.save_note_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save -> save()
        }

        return NavigationUI.onNavDestinationSelected(
            item,
            view!!.findNavController()
        ) || super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        noteViewModel.getAllCategories()
    }

}