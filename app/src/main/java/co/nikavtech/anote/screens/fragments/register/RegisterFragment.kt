package co.nikavtech.anote.screens.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import co.nikavtech.anote.database.NoteDatabase
import co.nikavtech.anote.databinding.FragmentRegisterBinding
import co.nikavtech.anote.database.entities.UserEntity

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        init(inflater, container)

//region observation links
        registerViewModel.isSuccessRegister.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    findNavController().navigate(
                        RegisterFragmentDirections.actionRegisterFragmentToLoginFragment(
                            binding.txtEmail.text.toString(),
                            binding.txtPassword.text.toString()
                        )
                    )
                }
                registerViewModel.resetISuccessRegister()
            }
        })
//endregion

        return binding.root
    }

    private fun init(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding =
            FragmentRegisterBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = NoteDatabase.getInstance(application)
        val viewModelFactory = RegisterViewModelFactory(dataSource.userDao, application)
        registerViewModel = ViewModelProvider(this, viewModelFactory).get(RegisterViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = registerViewModel
        binding.userModel = UserEntity()
    }
}