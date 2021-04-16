package co.nikavtech.anote.screens.fragments.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.nikavtech.anote.databinding.FragmentAboutBinding
import co.nikavtech.anote.databinding.FragmentContactBinding

class ContactFragment:Fragment() {
    private lateinit var binding:FragmentContactBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        init(inflater, container)

        return binding.root
    }

    private fun init(inflater: LayoutInflater,
                     container: ViewGroup?) {
        binding = FragmentContactBinding.inflate(inflater, container, false)

    }
}