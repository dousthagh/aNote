package co.nikavtech.anote.screens.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.nikavtech.anote.R
import co.nikavtech.anote.database.NoteDatabase
import co.nikavtech.anote.database.entities.NoteWithCategoryEntity
import co.nikavtech.anote.databinding.FragmentHomeBinding
import co.nikavtech.anote.screens.adapters.note.NoteWithCategoryAdapter

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        init(inflater, container)

        prepareRecyclerView()


        binding.fabAdd.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNoteFragment(null))
        }
        return binding.root
    }

    private fun prepareRecyclerView() {
        binding.rvNoteList.layoutManager = LinearLayoutManager(context)
        binding.rvNoteList.setHasFixedSize(true)

        val noteWithCategoryAdapter = NoteWithCategoryAdapter(NoteWithCategoryAdapter.DIFF_CALLBACK)

        noteWithCategoryAdapter.setOnItemClickListener(object : NoteWithCategoryAdapter.OnItemClickListener{
            override fun onItemClick(noteWithCategory: NoteWithCategoryEntity?) {
                Navigation.findNavController(requireView()).navigate(HomeFragmentDirections.actionHomeFragmentToNoteFragment(noteWithCategory))
            }
        })

        homeViewModel.notesWithCategory?.observe(viewLifecycleOwner, {
            noteWithCategoryAdapter.submitList(it)
            noteWithCategoryAdapter.notifyDataSetChanged()
        })

        binding.rvNoteList.adapter = noteWithCategoryAdapter

    }

    private fun init(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        val application = requireNotNull(this.activity).application
        val dataSource = NoteDatabase.getInstance(application)

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel =
            ViewModelProvider(this, HomeViewModelFactory(dataSource.noteDao, application)).get(
                HomeViewModel::class.java
            )
    }

    private fun toggleBlankListItems(status: Int) {
        binding.imgBlankList.visibility = status
        binding.lblBlankList.visibility = status
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.fetchNotesWithCategory()
    }
}