package co.nikavtech.anote.screens.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.nikavtech.anote.R
import co.nikavtech.anote.databinding.FragmentHomeBinding
import co.nikavtech.anote.models.NoteDataObject
import co.nikavtech.anote.screens.fragments.home.listAdapter.NoteItemEvent
import co.nikavtech.anote.screens.fragments.home.listAdapter.NoteListAdapter

class HomeFragment : Fragment(), NoteItemEvent {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var noteListAdapter: NoteListAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        init(inflater, container)

        settingUpRecyclerView()

        homeViewModel.notes.observe(this, Observer { notes ->
            if (notes.size > 0) {
                toggleBlankListItems(View.GONE)
                noteListAdapter.setAllNoteItem(notes)
            } else {
                toggleBlankListItems(View.VISIBLE)
            }
        })

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.noteFragment)
        }
        return binding.root
    }

    private fun settingUpRecyclerView() {
        binding.rvNoteList.layoutManager = LinearLayoutManager(context)
        noteListAdapter = NoteListAdapter(this)
        binding.rvNoteList.adapter = noteListAdapter

    }

    private fun init(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    private fun toggleBlankListItems(status: Int) {
        binding.imgBlankList.visibility = status
        binding.lblBlankList.visibility = status
    }

    override fun onDeleteClicked(noteDataObject: NoteDataObject) {
        TODO("Not yet implemented")
    }

    override fun onViewClicked(noteDataObject: NoteDataObject) {
        TODO("Not yet implemented")
    }
}