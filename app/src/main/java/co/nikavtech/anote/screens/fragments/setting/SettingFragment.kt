package co.nikavtech.anote.screens.fragments.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.nikavtech.anote.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        init(inflater, container)

        return binding.root
    }

    private fun init(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentSettingBinding.inflate(inflater, container, false)

    }
}