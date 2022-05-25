package com.template

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.template.Constants.Companion.COUNTDOWN_INTERVAL
import com.template.Constants.Companion.DEFAULT
import com.template.Constants.Companion.EMOJI_1
import com.template.Constants.Companion.EMOJI_2
import com.template.Constants.Companion.EMOJI_3
import com.template.Constants.Companion.EMOJI_4
import com.template.Constants.Companion.EMOJI_5
import com.template.Constants.Companion.EMOJI_6
import com.template.Constants.Companion.EMOJI_7
import com.template.Constants.Companion.EMOJI_8
import com.template.Constants.Companion.EMOJI_9
import com.template.Constants.Companion.FROM_DOUBLE
import com.template.Constants.Companion.FROM_INT
import com.template.Constants.Companion.GAME_SETTINGS
import com.template.Constants.Companion.MONEY
import com.template.Constants.Companion.NECESSARY_COIN_TO_BET
import com.template.Constants.Companion.ONE
import com.template.Constants.Companion.TIME_IN_MILLIS
import com.template.Constants.Companion.UNTIL_DOUBLE
import com.template.Constants.Companion.UNTIL_INT
import com.template.databinding.FragmentGameBinding
import kotlin.random.Random

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private var timer: CountDownTimer? = null
    private lateinit var preferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(layoutInflater,container,false)
        preferences = requireActivity().getSharedPreferences(GAME_SETTINGS, Context.MODE_PRIVATE)
        checkCoinsToBet()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            amountOfCoins.text = String.format(getString(R.string.main_coins), preferences.getInt(
                MONEY, DEFAULT))
            betButton.setOnClickListener {
                betButton.isEnabled = false
                betSet.visibility = View.VISIBLE
                spinButton.isEnabled = true
            }
            spinButton.setOnClickListener {
                spinButton.isEnabled = false
                betSet.visibility = View.INVISIBLE
                startGame() }
            fab.setOnClickListener {
                timer?.cancel()
                findNavController().navigate(R.id.action_gameFragment_to_startFragment)
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun startGame() {
        val listOfEmoji = listOf(EMOJI_1, EMOJI_2, EMOJI_3, EMOJI_4, EMOJI_5, EMOJI_6, EMOJI_7, EMOJI_8, EMOJI_9)
        timer = object : CountDownTimer(TIME_IN_MILLIS, COUNTDOWN_INTERVAL) {

            override fun onTick(millisUntilFinished: Long) {
                binding.oneTv.text = listOfEmoji.shuffled()[0]
                binding.twoTv.text = listOfEmoji.shuffled()[1]
                binding.threeTv.text = listOfEmoji.shuffled()[2]
                binding.fourTv.text = listOfEmoji.shuffled()[3]
                binding.fiveTv.text = listOfEmoji.shuffled()[4]
                binding.sixTv.text = listOfEmoji.shuffled()[5]
                binding.sevenTv.text = listOfEmoji.shuffled()[6]
                binding.eightTv.text = listOfEmoji.shuffled()[7]
                binding.nineTv.text = listOfEmoji.shuffled()[8]
            }

            override fun onFinish() {
                checkCoinsToBet()
                checkResult()
            }
        }.start()
    }

    private fun checkResult() {
        val result = Random.nextInt(FROM_INT,UNTIL_INT)
        if (result == ONE) {
            val winCoins = NECESSARY_COIN_TO_BET * Random.nextDouble(FROM_DOUBLE, UNTIL_DOUBLE) + preferences.getInt(MONEY, DEFAULT)
            editAmountOfCoins(winCoins.toInt())
            Toast.makeText(requireContext(), getString(R.string.win), Toast.LENGTH_SHORT).show()
        } else {
            val newCoins = preferences.getInt(MONEY, DEFAULT) - NECESSARY_COIN_TO_BET
            editAmountOfCoins(newCoins)
            Toast.makeText(requireContext(), getString(R.string.lose), Toast.LENGTH_SHORT).show()
        }
    }

    private fun editAmountOfCoins(coins: Int) {
        binding.amountOfCoins.text = String.format(getString(R.string.main_coins), coins)
        preferences.edit().putInt(MONEY, coins).apply()
    }

    private fun checkCoinsToBet() {
        binding.betButton.isEnabled = preferences.getInt(MONEY, DEFAULT) >= NECESSARY_COIN_TO_BET
    }
}