package com.template

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.template.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    private lateinit var preferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(layoutInflater,container,false)
        preferences = requireActivity().getSharedPreferences("Game_settings", MODE_PRIVATE)
        if (preferences.contains("Money")) {
            binding.amountOfCoins.text = preferences.getInt("Money", 0).toString()
        } else { preferences.edit().putInt("Money", 1000).apply() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            playButton.setOnClickListener { findNavController().navigate(R.id.action_startFragment_to_gameFragment) }
            lotteryButton.setOnClickListener { findNavController().navigate(R.id.action_startFragment_to_lotteryFragment) }
            amountOfCoins.text = String.format(getString(R.string.main_coins),
                preferences.getInt("Money", 0))
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}