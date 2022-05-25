package com.template

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.template.Constants.Companion.DEFAULT
import com.template.Constants.Companion.DIALOG
import com.template.Constants.Companion.GAME_SETTINGS
import com.template.Constants.Companion.MONEY
import com.template.Constants.Companion.NECESSARY_COIN_TO_BET
import com.template.Constants.Companion.START_MONEY
import com.template.databinding.FragmentLotteryBinding
import kotlin.random.Random

class LotteryFragment : Fragment() {

    private var _binding: FragmentLotteryBinding? = null
    private val binding get() = _binding!!

    private lateinit var preferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLotteryBinding.inflate(layoutInflater,container,false)
        preferences = requireActivity().getSharedPreferences(GAME_SETTINGS, Context.MODE_PRIVATE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val rewardCoins = Random.nextInt(NECESSARY_COIN_TO_BET,START_MONEY)
        with(binding) {
            question1.setOnClickListener {
                showDialogAndCoins(rewardCoins)
            }
            question2.setOnClickListener {
                showDialogAndCoins(rewardCoins)
            }
            question3.setOnClickListener {
                showDialogAndCoins(rewardCoins)
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showDialogAndCoins(rewardCoins: Int) {
        LotteryCoinsDialog(rewardCoins).show(parentFragmentManager, DIALOG)
        with(binding) {
            amountOfCoins.visibility = View.VISIBLE
            amountOfCoins.text = String.format(getString(R.string.reward), rewardCoins + preferences.getInt(MONEY, DEFAULT))
        }
    }
}