package co.nikavtech.anote.screens.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import co.nikavtech.anote.database.NoteDatabase
import co.nikavtech.anote.databinding.FragmentCategoryBinding
import co.nikavtech.anote.databinding.FragmentProfileBinding

class ProfileFragment :Fragment(){
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        init(inflater, container)

        return binding.root
    }

    private fun init(inflater: LayoutInflater,
                     container: ViewGroup?){
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = NoteDatabase.getInstance(application)
        val factory = ProfileViewModelFactory(dataSource.userDao, application)
        val profileViewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)
    }
}