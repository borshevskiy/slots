package com.template

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.template.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private var timer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(layoutInflater,container,false)
        startGame()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun startGame() {
        val listOfEmoji = listOf("\uD83C\uDF4B", "\uD83C\uDF52", "\uD83C\uDF47", "\uD83C\uDF46", "\uD83C\uDF40", "\uD83D\uDCB0", "⭐", "\uD83D\uDD14", "7️⃣")
        timer = object : CountDownTimer(5000L, 100) {
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
                checkResult()
            }
        }.start()
    }

    private fun checkResult() {
        if (binding.oneTv.text == binding.twoTv.text && binding.twoTv.text == binding.threeTv.text) {
            Toast.makeText(requireContext(), "You win big prize!", Toast.LENGTH_SHORT).show()
        }
        if (binding.oneTv.text == binding.twoTv.text || binding.twoTv.text == binding.threeTv.text) {
            Toast.makeText(requireContext(), "You win small prize!", Toast.LENGTH_SHORT).show()
        }
        if (binding.fourTv.text == binding.fiveTv.text && binding.fourTv.text == binding.sixTv.text) {
            Toast.makeText(requireContext(), "You win big prize!", Toast.LENGTH_SHORT).show()
        }
        if (binding.fourTv.text == binding.fiveTv.text || binding.fiveTv.text == binding.sixTv.text) {
            Toast.makeText(requireContext(), "You win small prize!", Toast.LENGTH_SHORT).show()
        }
        if (binding.sevenTv.text == binding.eightTv.text && binding.eightTv.text == binding.nineTv.text) {
            Toast.makeText(requireContext(), "You win big prize!", Toast.LENGTH_SHORT).show()
        }
        if (binding.sevenTv.text == binding.eightTv.text || binding.eightTv.text == binding.nineTv.text) {
            Toast.makeText(requireContext(), "You win small prize!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "You lose!", Toast.LENGTH_SHORT).show()
        }
    }
}