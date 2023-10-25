package com.farmingdale.ht_individualproject2.composables

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.farmingdale.ht_individualproject2.LoginViewModel
import com.farmingdale.ht_individualproject2.R

//Opening Screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(navController: NavHostController, loginViewModel: LoginViewModel){
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {
        Image(
            painter = painterResource(id = R.drawable.mainicon),
            contentDescription = null,
            modifier = Modifier.padding(bottom = 50.dp)
            )

        Text(text = "LOGIN", style = MaterialTheme.typography.headlineLarge)

        OutlinedTextField(
            value = loginViewModel.getMailInput(),
            onValueChange = {loginViewModel.updateEmailInput(it)},
            modifier = Modifier
                .fillMaxWidth(.7f)
                .padding(vertical = 10.dp),
            label = { Text(text = stringResource(id = R.string.email_label))},
            )

        OutlinedTextField(
            value = loginViewModel.getPswdInput(),
            onValueChange = {loginViewModel.updatePswdInput(it)},
            modifier = Modifier
                .fillMaxWidth(.7f)
                .padding(vertical = 10.dp),
            label = { Text(text = stringResource(id = R.string.password_label))},
            visualTransformation = PasswordVisualTransformation()
        )

        Button(
            onClick = {
                      if(loginViewModel.validateLogin()){
                          Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                          navController.navigate("Rules")
                      }
                else{
                          Toast.makeText(context, "Incorrect email or password", Toast.LENGTH_SHORT).show()
                      }
                      },
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Text(text = stringResource(id = R.string.login), style = MaterialTheme.typography.titleMedium)
        }

        Divider(modifier = Modifier.padding(20.dp))

        Text(text = stringResource(id = R.string.registerInvite))
        Button(
            onClick = {
                loginViewModel.clearData()
                navController.navigate("Registration")
                      },
            modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
        ) {
            Text(text = stringResource(id = R.string.register), style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun WelcomeScreenPreview(){
    WelcomeScreen(navController = rememberNavController(), loginViewModel = LoginViewModel)
}
