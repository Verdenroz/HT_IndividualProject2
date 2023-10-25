package com.farmingdale.ht_individualproject2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.farmingdale.ht_individualproject2.composables.GameNavigationPortrait
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameActivity : ComponentActivity() {
    private val quizViewModel by viewModels<QuizViewModel>()
    //Process database
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            HistoryDatabase::class.java,
            "menu.db"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //if activity created from intent, insert historyItem
        val intent = intent
        if(!intent.getStringExtra("ANSWERS").isNullOrBlank()){
            val history = HistoryItem(
                correctAnswers = intent.getStringExtra("ANSWERS").toString(),
                money = intent.getIntExtra("MONEY", 0)
            )
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    database.historydao().saveMenuItem(history)
                }
            }
        }

        setContent {
            GameNavigationPortrait(quizViewModel = quizViewModel)
        }
    }
}