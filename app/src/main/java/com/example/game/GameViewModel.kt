package com.example.game.com.example.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel:ViewModel() {

    val score = MutableLiveData<Int>(0)
    val questionNumber = MutableLiveData<Int>(1)
    val maxScore = MutableLiveData<Int>(0)

    fun setScore(myScore:Int){
        score.value = myScore
    }
    fun setQuestionNumber(qNumber:Int){
        questionNumber.value = qNumber
    }
    fun addQuestionNumber(){
        questionNumber.value = questionNumber.value?.plus(1)
    }
    fun resetQNumber(){
        questionNumber.value = 1
    }
    fun resetScore(){
        score.value = 0
    }
    fun addScore(){
        score.value = score.value?.plus(5)
    }
    fun removeScore(){
        score.value = score.value?.minus(2)
    }
    fun setMaxScore(){
        if (score.value!! > maxScore.value!!){
            maxScore.value = score.value
        }
    }

    fun randomNumberA():Int{
        return  (1 .. 100).random()
    }
    fun randomNumberB():Int{
        return (1 .. 10).random()

    }
    fun getRandom():Int{
        return (1 .. 10).random()
    }

}