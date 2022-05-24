package com.template

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.template.databinding.RewardCoinsLayoutBinding

class RewardCoinsDialog(private val rewardCoins: Int) : DialogFragment() {

    private lateinit var binding: RewardCoinsLayoutBinding
    private lateinit var preferences: SharedPreferences

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = RewardCoinsLayoutBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        preferences = requireActivity().getSharedPreferences("Game_settings", Context.MODE_PRIVATE)
        binding.rewardText.text = String.format(getString(R.string.reward), rewardCoins)
        binding.claimButton.setOnClickListener {
            claimMoney(preferences.getInt("Money", 0) + rewardCoins)
            dismiss()
            findNavController().navigate(R.id.action_lotteryFragment_to_startFragment)
        }
        return builder.create()
    }

    private fun claimMoney(newCoins: Int) {
        preferences.edit().putInt("Money", newCoins).apply()
    }
}