package com.farmingdale.ht_individualproject2.composables

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.farmingdale.ht_individualproject2.QuizViewModel

//Did my best with the checkbox. Suprisingly hard to manage the checkStates for each individual checkbox
//Still a bug where after question updates, user needs to click twice to change the state
@Composable
fun CustomCheckBoxGroup(
    quizViewModel: QuizViewModel
) {
    Column {
        quizViewModel.getChoices().forEachIndexed() { index, text ->
            var checkState by remember { mutableStateOf(true)}
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = !checkState,
                        onClick = {
                            checkState = quizViewModel.getCheckboxState(index)
                            quizViewModel.toggleCheckboxState(index)

                            if(quizViewModel.getCheckboxState(index)){
                                quizViewModel.addCheckChoices(text)
                            }
                            else{
                                quizViewModel.removeCheckChoices(text)
                            }
                        },
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = !checkState,
                    onCheckedChange = {
                        checkState = quizViewModel.getCheckboxState(index)
                        quizViewModel.toggleCheckboxState(index)
                        Log.d("Check", checkState.toString())
                        if(quizViewModel.getCheckboxState(index)){
                            quizViewModel.addCheckChoices(text)
                        }
                        else{
                            quizViewModel.removeCheckChoices(text)
                        }

                    }
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}