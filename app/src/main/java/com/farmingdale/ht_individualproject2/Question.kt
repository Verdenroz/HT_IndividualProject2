package com.farmingdale.ht_individualproject2

import android.content.Context

//Holds the question text, the answer, and the choices
class Question() {
    private var text: String = ""
    private var answer: Array<String> = arrayOf()
    private var choices: Array<String> = arrayOf()

    //returns question text
    fun getText(): String{
        return text
    }

    //returns question's answer
    fun getAnswer(): Array<String>{
        return answer
    }

    //returns the question's choices
    fun getChoices(): Array<String>{
        return choices
    }

    //updates the text, answer, and choices depending on the index
    fun updateQuestion(index: Int, context: Context) {
        when(index){
            0 -> {
                text = context.getString(R.string.question_1)
                answer = context.resources.getStringArray(R.array.question_1_answer)
                choices = context.resources.getStringArray(R.array.question_1_choices)
            }
            1 -> {
                text = context.getString(R.string.question_2)
                answer = context.resources.getStringArray(R.array.question_2_answer)
                choices = context.resources.getStringArray(R.array.question_2_choices)
            }
            2 -> {
                text = context.getString(R.string.question_3)
                answer = context.resources.getStringArray(R.array.question_3_answer)
                choices = context.resources.getStringArray(R.array.question_3_choices)
            }
            3 -> {
                text = context.getString(R.string.question_4)
                answer = context.resources.getStringArray(R.array.question_4_answer)
                choices = context.resources.getStringArray(R.array.question_4_choices)
            }
            4 -> {
                text = context.getString(R.string.question_5)
                answer = context.resources.getStringArray(R.array.question_5_answer)
                choices = context.resources.getStringArray(R.array.question_5_choices)
            }
            5 -> {
                text = context.getString(R.string.question_6)
                answer = context.resources.getStringArray(R.array.question_6_answer)
                choices = context.resources.getStringArray(R.array.question_6_choices)
            }
            6 -> {
                text = context.getString(R.string.question_7)
                answer = context.resources.getStringArray(R.array.question_7_answer)
                choices = context.resources.getStringArray(R.array.question_7_choices)
            }
            7 ->{
                text = "Which ones are animals?"
                answer = arrayOf("Zebra", "Lizard")
                choices = arrayOf("Zebra", "Lizard", "Rock", "Cheese")
            }
            8 ->{
                text = "Which countries are in Europe?"
                answer = arrayOf("Spain", "France")
                choices = arrayOf("Australia", "Egypt", "Spain", "France")
            }
            9 ->{
                text = "Which are letters in the English Alphabet??"
                answer = arrayOf("A", "B", "C", "D")
                choices = arrayOf("A", "B", "C", "D")
            }
        }
    }

}