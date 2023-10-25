package com.farmingdale.ht_individualproject2

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.farmingdale.ht_individualproject2.composables.LoginNavigationPortrait
import com.farmingdale.ht_individualproject2.ui.theme.HT_IndividualProject2Theme


class MainActivity : ComponentActivity() {
    private val loginViewModel by viewModels<LoginViewModel>()
    //gets shared pref to see user is already registered
    private val sharedPreferences by lazy{
        getSharedPreferences("User", Context.MODE_PRIVATE)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        //will update user to user's data if already registered
        loginViewModel.updateEmail(sharedPreferences.getString("email", ""))
        loginViewModel.updatePassword(sharedPreferences.getString("password", ""))
        loginViewModel.updateFirstName(sharedPreferences.getString("firstName", ""))
        loginViewModel.updateLastName(sharedPreferences.getString("lastName", ""))
        loginViewModel.updateMonth(sharedPreferences.getString("month", ""))
        loginViewModel.updateDay(sharedPreferences.getString("day", ""))
        loginViewModel.updateYear(sharedPreferences.getString("year", ""))

        super.onCreate(savedInstanceState)
        setContent {
            HT_IndividualProject2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //finds current configuration and opens corresponding Navigation Component
                    /*
                    val configuration = LocalConfiguration.current
                    when(configuration.orientation){
                        Configuration.ORIENTATION_LANDSCAPE -> NavigationLandscape()
                        else ->{
                            NavigationPortrait(loginViewModel, quizViewModel)
                        }
                    }

                     */
                    LoginNavigationPortrait(loginViewModel = loginViewModel)
                }
            }
        }
    }
}



