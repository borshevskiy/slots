package com.template

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.template.databinding.FragmentLotteryBinding
import com.template.databinding.FragmentStartBinding

class LotteryFragment : Fragment() {

    private var _binding: FragmentLotteryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLotteryBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        binding.playButton.setOnClickListener {
//            findNavController().navigate(R.id.action_startFragment_to_chooseLevelFragment)
//        }
//        binding.lotteryButton.setOnClickListener {
//            findNavController().navigate(R.id.action_startFragment_to_chooseLevelFragment)
//        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}