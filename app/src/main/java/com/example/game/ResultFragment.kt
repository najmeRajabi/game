package com.example.game

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.game.databinding.FragmentResultBinding
import com.google.android.material.internal.ContextUtils
import kotlin.system.exitProcess

class ResultFragment : Fragment() {

    lateinit var binding: FragmentResultBinding

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
        binding.scoreFinalTxv.text="your score is  ${Storage.score.toString()}"
        binding.record .text="most score is  ${Storage.maxScore.toString()}"

        binding.exitBtn.setOnClickListener {
            Storage.questionNumber=1
            Storage.score=0

            exitApplication()
        }


        binding.replyBtn.setOnClickListener {
            Storage.questionNumber=1
            Storage.score=0
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