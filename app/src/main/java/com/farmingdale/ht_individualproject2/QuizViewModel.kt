package com.farmingdale.ht_individualproject2

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

//handles the login behind the quiz
object QuizViewModel: ViewModel() {

    private var questionText by mutableStateOf("")
    private var questionAnswer by mutableStateOf(arrayOf(String()))
    private var questionChoices by mutableStateOf(arrayOf(String()))
    private var chosenChoice by  mutableStateOf("")
    private var questionIndex by mutableStateOf(0)
    private var money by mutableStateOf(0)
    private var correctAnswers by mutableStateOf(0)
    private var checkedChoices by mutableStateOf(arrayListOf(String()))
    private var checkboxStates by mutableStateOf(BooleanArray(4))

    fun updateQuestion(context: Context){
        val currentQuestion = Question()
        currentQuestion.updateQuestion(questionIndex, context)
        questionText = currentQuestion.getText()
        questionAnswer = currentQuestion.getAnswer()
        questionChoices = currentQuestion.getChoices()
    }
    fun getText(): String{
        return questionText
    }
    fun getAnswer(): Array<String>{
        return questionAnswer
    }
    fun getChoices(): Array<String>{
        return questionChoices
    }

    //will hold the current choice selected by the user
    fun setChoice(chose: String){
        chosenChoice = chose
    }

    fun getChoice(): String{
        return chosenChoice
    }

    fun getBoxStates(): BooleanArray {
        return checkboxStates
    }

    fun resetCheckboxStates() {
        checkboxStates = BooleanArray(4) { false }
    }

    fun toggleCheckboxState(index: Int) {
        checkboxStates[index] = !checkboxStates[index]
        Log.d("Toggle", checkboxStates.joinToString(","))
    }

    fun getCheckboxState(index: Int): Boolean {
        return checkboxStates[index]
    }


    fun addCheckChoices(text: String){
        checkedChoices.add(text)
        Log.d("ListAdd", checkedChoices.joinToString(","))
    }
    fun removeCheckChoices(text: String){
        checkedChoices.remove(text)
        Log.d("ListRemove", checkedChoices.joinToString(","))
    }

    fun resetCheckChoices(){
        checkedChoices.clear()
    }

    fun checkIfArraysContainSameStrings(): Boolean {
        // Check if the arrays have the same size.
        if (checkedChoices.size != questionAnswer.size) {
            return false
        }

        // Check if the arrays contain the same elements in the same order.
        for (i in 0 until checkedChoices.size) {
            Log.d("Check", checkedChoices[i])
            Log.d("Answer", questionAnswer[i])
            if (checkedChoices[i] != questionAnswer[i]) {
                return false
            }
        }

        // If we reach this point, then the arrays must contain the same strings in the same order.
        return true
    }

    fun getIndex(): Int{
        return questionIndex
    }
    fun resetIndex(){
        questionIndex = 0
    }
    fun incrementIndex(){
        questionIndex++
    }

    fun getTotalMoney(): Int{
        return money
    }

    fun addMoney(money: Int){
        this.money += money
    }

    fun resetMoney(){
        money = 0
    }

    fun getScore(): Int{
        return correctAnswers
    }

    fun resetScore(){
        correctAnswers = 0
    }

    fun incrementScore(){
        correctAnswers++
    }

    fun correctAnswer(){
        incrementIndex()
        addMoney(100 * getIndex())
        incrementScore()
    }

    fun resetQuestions(){
        resetIndex()
        resetMoney()
        resetScore()
    }
}