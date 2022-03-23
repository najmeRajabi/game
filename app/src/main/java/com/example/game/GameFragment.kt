package com.example.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.game.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    lateinit var binding: FragmentGameBinding
    var btnArray=ArrayList<Button>()
    var a=0
    var b=0
    var mode=-1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(savedInstanceState!= null){
            binding.aNumberTxv .text = savedInstanceState.getString("A")
            binding.bNumberTxv .text = savedInstanceState.getString("B")
            binding.answer1Btn .text = savedInstanceState.getString("Button1")
            binding.answer2Btn .text = savedInstanceState.getString("Button2")
            binding.answer3Btn .text = savedInstanceState.getString("Button3")
            binding.answer4Btn .text = savedInstanceState.getString("Button4")
            binding.answer1Btn.isEnabled = savedInstanceState.getBoolean("Enable")
            binding.answer2Btn.isEnabled = savedInstanceState.getBoolean("Enable")
            binding.answer3Btn.isEnabled = savedInstanceState.getBoolean("Enable")
            binding.answer4Btn.isEnabled = savedInstanceState.getBoolean("Enable")
            binding.scoreTxv.text= savedInstanceState.getString("Score")
            Storage.questionNumber= savedInstanceState.getInt("questionNumber")
        }




        btnArray = arrayListOf(binding.answer1Btn,binding.answer2Btn
            ,binding.answer3Btn,binding.answer4Btn)
        if(binding.aNumberTxv .text.isBlank()) {
            dice()
        }
        binding.diceBtn.setOnClickListener {
            Storage.questionNumber++
            if (Storage.questionNumber>=6){
                if (Storage.maxScore<Storage.score){
                    Storage.maxScore=Storage.score
                }
                Storage.questionNumber=1
                findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
//                val intent= Intent(activity,Activity2::class.java)
//                startActivity(intent)
            }
            else {
                for (button in btnArray) {
                    activity?.let { it1 -> ContextCompat.getColor(it1, R.color.purple_500) }
                        ?.let { it2 -> button.setBackgroundColor(it2) }
                }
                enableButton()
                dice()
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("A" , binding.aNumberTxv .text.toString())
        outState.putString("B" , binding.bNumberTxv .text.toString())
        outState.putString("Button1" , binding.answer1Btn .text.toString())
        outState.putString("Button2" , binding.answer2Btn .text.toString())
        outState.putString("Button3" , binding.answer3Btn .text.toString())
        outState.putString("Button4" , binding.answer4Btn .text.toString())
        outState.putString("Score" , binding.scoreTxv.text.toString())
        outState.putInt("questionNumber" ,Storage.questionNumber)
        outState.putBoolean("Enable" , binding.answer4Btn.isEnabled)
        for (button in btnArray){
            var s=button.isEnabled
            var x =button.background
        }
        super.onSaveInstanceState(outState)
    }


    private fun setTextButton(number:Int){
        for (i in btnArray.indices){
            if (number == i){
                btnArray[i].text =mode.toString()
            }else{
                btnArray[i].text=Storage.getRandom().toString()
            }

        }
    }


    private fun correctAnswer(button: Button){
        if(button.text==mode.toString()){
            //Toast.makeText(this,"correct",Toast.LENGTH_SHORT).show()
            Storage.score+=5
            binding.scoreTxv.text=Storage.score.toString()
            activity?.let { ContextCompat.getColor(it, R.color.green) }
                ?.let { button.setBackgroundColor(it) }

            disableButton()
        } else{
            //Toast.makeText(this,"incorrect",Toast.LENGTH_SHORT).show()
            Storage.score-=2
            binding.scoreTxv.text=Storage.score.toString()
            activity?.let { ContextCompat.getColor(it, R.color.red) }
                ?.let { button.setBackgroundColor(it) }
            disableButton()
        }

    }

    private fun disableButton() {
        for (button in btnArray){
            button.isEnabled=false
        }
    }

    private fun enableButton() {
        for (button in btnArray){
            button.isEnabled=true
        }
    }

    private fun dice() {
        a=Storage.randomNumberA()
        b=Storage.randomNumberB()
        mode=a%b
        binding.aNumberTxv.text = a.toString()
        binding.bNumberTxv.text = b.toString()
        binding.scoreTxv.text=Storage.score.toString()
        val numRandom = (0..3).random()
        setTextButton(numRandom)

        for (button in btnArray){
            button.setOnClickListener {  }
        }

        binding.answer1Btn.setOnClickListener {
            correctAnswer(binding.answer1Btn)
        }
        binding.answer2Btn.setOnClickListener {
            correctAnswer(binding.answer2Btn)
        }
        binding.answer3Btn.setOnClickListener {
            correctAnswer(binding.answer3Btn)
        }
        binding.answer4Btn.setOnClickListener {
            correctAnswer(binding.answer4Btn)
        }
    }


}