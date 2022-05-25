package com.template

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.template.Constants.Companion.DEFAULT
import com.template.Constants.Companion.DIALOG
import com.template.Constants.Companion.GAME_SETTINGS
import com.template.Constants.Companion.MONEY
import com.template.Constants.Companion.NECESSARY_COIN_TO_BET
import com.template.Constants.Companion.START_MONEY
import com.template.databinding.FragmentStartBinding
import kotlin.random.Random

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    private lateinit var preferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(layoutInflater,container,false)
        preferences = requireActivity().getSharedPreferences(GAME_SETTINGS, MODE_PRIVATE)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            if (preferences.contains(MONEY)) {
                setCoinsText()
            } else { preferences.edit().putInt(MONEY, START_MONEY).apply() }
            playButton.setOnClickListener {
                if (preferences.getInt(MONEY, DEFAULT) < NECESSARY_COIN_TO_BET) {
                    StartCoinsDialog(Random.nextInt(NECESSARY_COIN_TO_BET,START_MONEY)).show(parentFragmentManager, DIALOG)
                    setCoinsText()
                } else { findNavController().navigate(R.id.action_startFragment_to_gameFragment) }
            }
            lotteryButton.setOnClickListener { findNavController().navigate(R.id.action_startFragment_to_lotteryFragment) }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setCoinsText() {
        binding.amountOfCoins.text = String.format(getString(R.string.main_coins), preferences.getInt(MONEY, DEFAULT))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}