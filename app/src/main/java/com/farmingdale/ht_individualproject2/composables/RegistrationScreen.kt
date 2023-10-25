package com.farmingdale.ht_individualproject2.composables

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.farmingdale.ht_individualproject2.LoginViewModel
import com.farmingdale.ht_individualproject2.R

//For Registration
@Composable
fun RegistrationScreen(navController: NavHostController, loginViewModel: LoginViewModel){
    val context = LocalContext.current
    val sharedPreferences by lazy{
        context.getSharedPreferences("User", Context.MODE_PRIVATE)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally) {
        InputTextField(
            text = loginViewModel.getFN(),
            onValueChanged = {loginViewModel.updateFirstName(it)},
            label = stringResource(id = R.string.first_name),
            showError = !loginViewModel.getValidityFN(),
            errorMessage = "Invalid",
            modifier = Modifier
        )

        InputTextField(
            text = loginViewModel.getLN(),
            onValueChanged = {loginViewModel.updateLastName(it)},
            label = stringResource(id = R.string.last_name),
            showError = !loginViewModel.getValidityLN(),
            errorMessage = "Invalid",
            modifier = Modifier
        )

        InputTextField(
            text = loginViewModel.getMail(),
            onValueChanged = {loginViewModel.updateEmail(it)},
            label = stringResource(id = R.string.email_label),
            showError = !loginViewModel.getValidityEmail(),
            errorMessage = "Invalid",
            modifier = Modifier
        )

        InputTextField(
            text = loginViewModel.getPswd(),
            onValueChanged = {loginViewModel.updatePassword(it)},
            label = stringResource(id = R.string.password_label),
            showError = !loginViewModel.getValidityPswd(),
            errorMessage = "Invalid",
            modifier = Modifier,
            isPassword = true
        )

        InputTextField(
            text = loginViewModel.getBirthMonth(),
            onValueChanged = {loginViewModel.updateMonth(it)},
            label = stringResource(id = R.string.month_of_birth),
            showError = !loginViewModel.getValidityMonth(),
            errorMessage = "Invalid",
            modifier = Modifier,
        )

        InputTextField(
            text = loginViewModel.getBirthDay(),
            onValueChanged = {loginViewModel.updateDay(it)},
            label = stringResource(id = R.string.day_of_birth),
            showError = !loginViewModel.getValidityDay(),
            errorMessage = "Invalid",
            modifier = Modifier,
        )

        InputTextField(
            text = loginViewModel.getBirthYear(),
            onValueChanged = {loginViewModel.updateYear(it)},
            label = stringResource(id = R.string.year_of_birth),
            showError = !loginViewModel.getValidityYear(),
            errorMessage = "Invalid",
            modifier = Modifier,
        )

        Button(onClick = {
            if(loginViewModel.validateData()){
                Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
                sharedPreferences.edit(commit = true) {
                    putString("email", loginViewModel.getMail())
                    putString("password", loginViewModel.getPswd())
                    putString("firstName", loginViewModel.getFN())
                    putString("lastName", loginViewModel.getLN())
                    putString("month", loginViewModel.getBirthMonth())
                    putString("day", loginViewModel.getBirthDay())
                    putString("year", loginViewModel.getBirthYear())
                }
                navController.navigate("Welcome")
            }
        }) {
            Text(text = stringResource(id = R.string.register))
        }
    }
}

@Composable
@Preview
fun RegistrationScreenPreview(){
    RegistrationScreen(navController = rememberNavController(), loginViewModel = LoginViewModel)
}

//Custom TextField
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextField(text: String,
                   onValueChanged: (String) -> Unit,
                   label: String,
                   showError: Boolean,
                   errorMessage: String,
                   modifier: Modifier,
                   isPassword: Boolean = false){

    OutlinedTextField(
        value = text,
        onValueChange = {onValueChanged(it)},
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(.8f),
        label = { Text(text = label) },
        isError = showError,
        visualTransformation =
        when{
            isPassword -> PasswordVisualTransformation()
            else -> VisualTransformation.None
        },
        singleLine = true,
    )
    //if data validation sees an error, shows a red caption
    if(showError){
        Text(
            text = errorMessage,
            color = Color.Red,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .padding(start = 8.dp)
                .offset(y = (-8).dp)
                .fillMaxWidth(.8f)
        )
    }
}