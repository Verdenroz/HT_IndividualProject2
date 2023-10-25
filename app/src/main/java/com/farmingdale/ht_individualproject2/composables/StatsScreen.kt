package com.farmingdale.ht_individualproject2.composables

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.farmingdale.ht_individualproject2.GameActivity
import com.farmingdale.ht_individualproject2.HistoryActivity
import com.farmingdale.ht_individualproject2.R

//the final screen to show the the stats - total money earned and # questions right
@Composable
fun StatsScreen(navController: NavHostController, money: String, correctAnswers: String){
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        //Displays total money earned
        Text(
            text = stringResource(id = R.string.statsHeader) + "\n $$money",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Divider(modifier = Modifier.padding(top = 25.dp, bottom = 25.dp))

        //Displays total questions answered correctly
        Text(
            text = stringResource(id = R.string.statsCorrectAnswersp1) + " $correctAnswers " + stringResource(id = R.string.statsCorrectAnswersp2))

        //Restart button
        Button(
            modifier = Modifier.padding(top = 30.dp),
            onClick = {
                val intent = Intent(context, GameActivity::class.java).apply {
                    putExtra("MONEY", money.toInt())
                    putExtra("ANSWERS", correctAnswers)
                }
                context.startActivity(intent)
            }
        ) {
            Text(text = stringResource(id = R.string.restartButton))
        }

        Button(
            modifier = Modifier.padding(top = 30.dp),
            onClick = {
                val intent = Intent(context, HistoryActivity::class.java).apply {
                    putExtra("MONEY", money.toInt())
                    putExtra("ANSWERS", correctAnswers)
                }
                context.startActivity(intent)
            }
        ) {
            Text(text = "HISTORY")
        }


    }
}

@Preview
@Composable
fun PreviewStatsScreen(){
    StatsScreen(navController = rememberNavController(), money = "1000", "5")
}