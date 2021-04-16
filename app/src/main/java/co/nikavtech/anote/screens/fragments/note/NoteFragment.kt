package co.nikavtech.anote.screens.fragments.note

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import co.nikavtech.anote.R
import co.nikavtech.anote.databinding.FragmentNoteBinding
import co.nikavtech.anote.models.NoteDataObject
import co.nikavtech.anote.ui.BottomSheetDialogFragment

class NoteFragment : Fragment() {
    private lateinit var binding: FragmentNoteBinding
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        init(inflater, container)

//region observation links
        noteViewModel.isSuccessSaveNote.observe(this, Observer {
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

        binding.fabShare.setOnClickListener {
            showShareIntent()
        }


        binding.fabCategory.setOnClickListener {
            BottomSheetDialogFragment().show(
                activity?.supportFragmentManager!!,
                "category_list_bottom_dialog"
            )
        }

        return binding.root
    }

    private fun getShareIntent(): Intent {
        val text = binding.noteModel!!.text
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
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        binding.viewModel = noteViewModel
        binding.noteModel = NoteDataObject()

        if (getShareIntent().resolveActivity(activity!!.packageManager) == null) {
            binding.fabShare.visibility = View.GONE
        }
    }

    private fun showShareIntent() {
        startActivity(getShareIntent())
    }

}