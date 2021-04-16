package co.nikavtech.anote.screens.fragments.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import co.nikavtech.anote.databinding.FragmentLoginBinding
import co.nikavtech.anote.models.UserModel
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

        loginViewModel.loginIsSuccess.observe(this, Observer {
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


        loginViewModel = ViewModelProvider(
            this,
            LoginViewModelFactory(userName, password)
        ).get(LoginViewModel::class.java)

        binding.userModel = UserModel(email = userName, password = password)
        binding.viewModel = loginViewModel
        binding.lifecycleOwner = this
    }

    private fun getBundleArguments(): LoginFragmentArgs {
        return LoginFragmentArgs.fromBundle(arguments!!)
    }
}