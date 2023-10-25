package com.farmingdale.ht_individualproject2.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.farmingdale.ht_individualproject2.QuizViewModel

//Navigating Quiz and Stats Screens
@Composable
fun GameNavigationPortrait(quizViewModel: QuizViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Quiz"){

        composable("Quiz"){
            QuizScreen(navController = navController, quizViewModel = quizViewModel)
        }
        composable(
            route = "Stats/{final_money}/{correct_answers}",
            arguments = listOf(
                navArgument("final_money"){
                    type = NavType.StringType
                },
                navArgument("correct_answers"){
                    type = NavType.StringType
                }
            )
        )
        {
            val money = it.arguments?.getString("final_money") ?: ""
            val correctAnswers = it.arguments?.getString("correct_answers") ?: ""
            StatsScreen(navController = navController, money = money, correctAnswers = correctAnswers)
        }
    }
}