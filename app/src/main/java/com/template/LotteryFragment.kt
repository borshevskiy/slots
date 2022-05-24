package com.template

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        preferences = requireActivity().getSharedPreferences("Game_settings", Context.MODE_PRIVATE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val rewardCoins = Random.nextInt(100,500)
        binding.question1.setOnClickListener {
            showDialogAndCoins(rewardCoins)
        }
        binding.question2.setOnClickListener {
            showDialogAndCoins(rewardCoins)
        }
        binding.question3.setOnClickListener {
            showDialogAndCoins(rewardCoins)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showDialogAndCoins(rewardCoins: Int) {
        RewardCoinsDialog(rewardCoins).show(parentFragmentManager, "dialog")
        with(binding) {
            amountOfCoins.visibility = View.VISIBLE
            amountOfCoins.text = String.format(getString(R.string.reward), rewardCoins + preferences.getInt("Money", 0))
        }
    }
}