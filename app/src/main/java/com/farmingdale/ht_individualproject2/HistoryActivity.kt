package com.farmingdale.ht_individualproject2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryActivity : ComponentActivity() {
    //build database
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            HistoryDatabase::class.java,
            "menu.db"
        ).build()
    }

    //clears history of database
    fun clearHistory(){
        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                database.historydao().deleteAllHistoryItems()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //if activity created from intent, save data from intent
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
            val historyItems by database.historydao()
                .getAllHistoryItems()
                .observeAsState(emptyList())

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Creates History Menu
                if (historyItems.isEmpty()) {
                    Text(
                        modifier = Modifier
                            .fillMaxHeight(.3f)
                            .padding(16.dp),
                        text = "The menu is empty"
                    )
                    Image(painter = painterResource(id = R.drawable.mainicon), contentDescription = null )
                } else {
                    Image(painter = painterResource(id = R.drawable.mainicon), contentDescription = null)
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxHeight(.8f)
                            .padding(16.dp)
                    ) {
                        items(
                            items = historyItems,
                            itemContent = { items ->
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {

                                    Text(text = "Attempt ${items.id}")
                                    Text(text = "Total Correct: " + items.correctAnswers)
                                    Text(text = "Money Earned: "+ items.money.toString())
                                    Divider()
                                }
                            }
                        )
                    }
                }
                //Navigation buttons
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Button(onClick = { clearHistory() }) {
                        Text(text = "CLEAR HISTORY")
                    }
                    Button(onClick = {
                        startActivity(Intent(applicationContext, GameActivity::class.java))
                    }) {
                        Text(text = "PLAY")
                    }
                }


            }
        }
    }
}