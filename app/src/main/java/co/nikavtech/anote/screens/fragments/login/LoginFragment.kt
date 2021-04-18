package co.nikavtech.anote.screens.fragments.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import co.nikavtech.anote.database.NoteDatabase
import co.nikavtech.anote.database.entities.UserModel
import co.nikavtech.anote.databinding.FragmentLoginBinding
import co.nikavtech.anote.screens.activities.Main.MainActivity

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        init(inflater, container)

        loginViewModel.loginIsSuccess.observe(viewLifecycleOwner, {
            it?.let {
                if (it) {
                    startActivity(Intent(activity?.applicationContext, MainActivity::class.java))
                    activity?.finish()
                } else {
                    Toast.makeText(
                        context,
                        "نام کاربری و کلمه عبور اشتباه می باشد.",
                        Toast.LENGTH_LONG
                    ).show()
                }
                loginViewModel.setToNullIsSuccessLogin()
            }
        })


        binding.gotoRegister.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
        return binding.root
    }

    private fun init(inflater: LayoutInflater, container: ViewGroup?) {
        var userName: String? = null
        var password: String? = null

        binding = FragmentLoginBinding.inflate(inflater, container, false)

        try {
            userName = getBundleArguments().userName
            password = getBundleArguments().password
        } catch (e: Exception) {
        }


        binding.userModel = UserModel(email = userName, password = password)
        val application = requireNotNull(this.activity).application
        val dataSource = NoteDatabase.getInstance(application)

        loginViewModel = ViewModelProvider(
            this,
            LoginViewModelFactory(dataSource.userDao, application)
        ).get(LoginViewModel::class.java)

        binding.viewModel = loginViewModel
        binding.lifecycleOwner = this
    }

    private fun getBundleArguments(): LoginFragmentArgs {
        return LoginFragmentArgs.fromBundle(arguments!!)
    }
}