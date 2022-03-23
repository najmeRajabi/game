package com.example.game.com.example.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.game.Storage

class GameViewModel:ViewModel() {

    val score = MutableLiveData<Int>()
    val questionNumber = MutableLiveData<Int>()
    val maxScore = MutableLiveData<Int>(0)

    fun setScore(myScore:Int){
        score.value = myScore
        setMaxScore(myScore)
    }
    fun setQuestionNumber(qNumber:Int){
        questionNumber.value = qNumber
    }
    private fun setMaxScore(myScore: Int){
        if (myScore > maxScore.value!!){
            maxScore.value = myScore
        }
    }

    var a =-1
    var b =-1
    fun randomNumberA():Int{
        a = (1 .. 100).random()
        return a
    }
    fun randomNumberB():Int{

        b = (1 .. 10).random()
        return b
    }
    fun getRandom():Int{
        return (1 .. 10).random()
    }
    fun answer():Int{
        return a % b
    }
}