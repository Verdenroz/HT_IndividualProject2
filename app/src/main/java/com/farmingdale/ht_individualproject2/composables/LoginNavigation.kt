package com.farmingdale.ht_individualproject2.composables

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.farmingdale.ht_individualproject2.LoginViewModel

//Navigating between Login, Registration, and Rules
@Composable
fun LoginNavigationPortrait(loginViewModel: LoginViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Welcome"){

        composable("Welcome"){
            WelcomeScreen(navController = navController, loginViewModel)
        }

        composable("Registration"){
            RegistrationScreen(navController = navController, loginViewModel)
        }

        composable("Rules"){
            RulesScreen()
        }

    }
}