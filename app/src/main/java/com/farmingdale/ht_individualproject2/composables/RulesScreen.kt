package com.farmingdale.ht_individualproject2.composables

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.farmingdale.ht_individualproject2.GameActivity
import com.farmingdale.ht_individualproject2.HistoryActivity
import com.farmingdale.ht_individualproject2.R

//Displays rules and navigates between History and Quiz
@Composable
fun RulesScreen(){
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = 50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

        Image(painter = painterResource(id = R.drawable.mainicon), contentDescription = null )
        Divider(modifier = Modifier.padding(top = 25.dp, bottom = 25.dp))

        Text(
            text = "THE RULES",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(25.dp),
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = stringResource(id = R.string.rules),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(end = 15.dp, bottom = 20.dp, start = 15.dp)
        )

        Row {
            Button(onClick = {
                val intent = Intent(context, HistoryActivity::class.java)
                context.startActivity(intent)
            },
                modifier = Modifier.padding(15.dp)) {
                Text(text = "HISTORY")
            }

            Button(onClick = {
                val intent = Intent(context, GameActivity::class.java)
                context.startActivity(intent)
            },
                modifier = Modifier.padding(15.dp)) {
                Text(text = "PLAY")
            }
        }
    }
}
@Preview
@Composable
fun RulesScreenPreview(){
    RulesScreen()
}