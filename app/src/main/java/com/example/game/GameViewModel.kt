package com.example.game.com.example.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.game.Storage

class GameViewModel:ViewModel() {

    val score = MutableLiveData<Int>(0)
    val questionNumber = MutableLiveData<Int>(1)
    val maxScore = MutableLiveData<Int>(0)

    fun setScore(myScore:Int){
        score.value = myScore
        setMaxScore(myScore)
    }
    fun setQuestionNumber(qNumber:Int){
        questionNumber.value = qNumber
    }
    fun addQuestionNumber(){
        questionNumber.value = questionNumber.value?.plus(1)
    }
    fun reset(){
        questionNumber.value = 1
    }
    fun correctAnswer(){
        score.value = score.value?.plus(5)
    }
    fun incorrectAnswer(){
        score.value = score.value?.minus(2)
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