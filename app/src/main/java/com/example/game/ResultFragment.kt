package com.example.game

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.game.com.example.game.GameViewModel
import com.example.game.databinding.FragmentResultBinding
import com.google.android.material.internal.ContextUtils

class ResultFragment : Fragment() {

    lateinit var binding: FragmentResultBinding
    val resultViewModel: GameViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentResultBinding.inflate(layoutInflater, container, false)

        initView()

        return binding.root
    }
    @SuppressLint("SetTextI18n")
    private fun initView() {
        resultViewModel.setMaxScore()
        binding.scoreFinalTxv.text="your score is  ${resultViewModel.score.value.toString()}"
        binding.record .text="max score is  ${resultViewModel.maxScore.value.toString()}"

        binding.exitBtn.setOnClickListener {
            resultViewModel.resetQNumber()
            resultViewModel.resetScore()

            exitApplication()
        }


        binding.replyBtn.setOnClickListener {
            resultViewModel.resetQNumber()
            resultViewModel.resetScore()
            activity?.onBackPressed()
//            val intent= Intent(activity,MainActivity::class.java)
//            startActivity(intent)
        }

    }
    @SuppressLint("RestrictedApi")
    fun exitApplication() {
        ContextUtils.getActivity(activity)?.finishAffinity()
    }

}