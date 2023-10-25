package com.farmingdale.ht_individualproject2.composables

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.farmingdale.ht_individualproject2.QuizViewModel
import com.farmingdale.ht_individualproject2.R

//Creates the Quiz Screen to show questions
@Composable
fun QuizScreen(navController: NavHostController, quizViewModel: QuizViewModel){
    val context = LocalContext.current
    quizViewModel.updateQuestion(context)
    quizViewModel.resetCheckboxStates()

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .offset(y = 50.dp)
    ) {
        //Creates the header to show amount of money earned
        Text(
            text = stringResource(id = R.string.header) + " ${quizViewModel.getTotalMoney()}",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Image(painter = painterResource(id = R.drawable.mainicon), contentDescription = null )
        Divider(modifier = Modifier.padding(top = 25.dp, bottom = 25.dp))
        //Creates the question text
        Text(
            text = quizViewModel.getText(),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(bottom = 25.dp)
                .fillMaxWidth(.8f)
        )

        //Creates the questions
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            if(quizViewModel.getIndex() < 7){
                Spacer(modifier = Modifier.fillMaxWidth(.2f))
                CustomRadioGroup(quizViewModel = quizViewModel)
            }
            else{
                Spacer(modifier = Modifier.fillMaxWidth(.2f))
                quizViewModel.resetCheckChoices()
                CustomCheckBoxGroup(quizViewModel = quizViewModel)
            }
        }


    }
    //For the button to be bottom center
    Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center) {
        Button(
            modifier = Modifier.padding(top = 30.dp),
            onClick = {
                when(quizViewModel.getIndex()){
                    0, 1, 2, 3, 4, 5, 6 -> {
                        //if chosen choice is the answer, increment the index, the money, and the answers
                        if (quizViewModel.getChoice() == quizViewModel.getAnswer()[0]) {
                            quizViewModel.correctAnswer()

                            //if index is less than 10, update question text and choices
                            if (quizViewModel.getIndex() < 10) {
                                quizViewModel.updateQuestion(context = context)
                                //makes the toast to show answer is correct
                                Toast.makeText(
                                    context,
                                    context.getString(R.string.correctAnswerToast) + "$${100 * quizViewModel.getIndex()}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                //if index is 10, navigate to final stats screen
                                navController.navigate("Stats/${quizViewModel.getTotalMoney()}/${quizViewModel.getScore()}")
                                quizViewModel.resetQuestions()
                                quizViewModel.updateQuestion(context)
                            }
                        } else {
                            //increment index no matter if answer is incorrect
                            quizViewModel.incrementIndex()
                            //if index is less than 10, update question text and choices
                            if (quizViewModel.getIndex() < 10) {
                                quizViewModel.updateQuestion(context = context)
                                //makes the toast
                                Toast.makeText(
                                    context,
                                    context.getString(R.string.incorrectAnswerToast),
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                //if index is 10, navigate to final stats screen
                                navController.navigate("Stats/${quizViewModel.getTotalMoney()}/${quizViewModel.getScore()}")
                                quizViewModel.resetQuestions()
                                quizViewModel.updateQuestion(context)
                            }
                        }
                    }
                    7, 8, 9 -> {
                            if(quizViewModel.checkIfArraysContainSameStrings()) {
                                quizViewModel.correctAnswer()
                                quizViewModel.resetCheckChoices()
                                //if index is less than 10, update question text and choices
                                if (quizViewModel.getIndex() < 10) {
                                    quizViewModel.updateQuestion(context = context)
                                    //makes the toast to show answer is correct
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.correctAnswerToast) + "$${100 * quizViewModel.getIndex()}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    //if index is 10, navigate to final stats screen
                                    navController.navigate("Stats/${quizViewModel.getTotalMoney()}/${quizViewModel.getScore()}")
                                    quizViewModel.resetQuestions()
                                    quizViewModel.updateQuestion(context)
                                }
                            }
                        else{
                                quizViewModel.incrementIndex()
                                quizViewModel.resetCheckChoices()
                                //if index is less than 10, update question text and choices
                                if (quizViewModel.getIndex() < 10) {
                                    quizViewModel.updateQuestion(context = context)
                                    //makes the toast
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.incorrectAnswerToast),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    //if index is 10, navigate to final stats screen
                                    navController.navigate("Stats/${quizViewModel.getTotalMoney()}/${quizViewModel.getScore()}")
                                    quizViewModel.resetQuestions()
                                    quizViewModel.updateQuestion(context)
                                }
                            }
                        }
                    }
            }
        ) {
            Text(text = stringResource(id = R.string.confirmButtonText))
        }
    }
}