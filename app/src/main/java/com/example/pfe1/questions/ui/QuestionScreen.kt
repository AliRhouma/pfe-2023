package com.example.pfe1.questions.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pfe1.navigation.Screen
import com.example.pfe1.questions.domain.model.Question
import com.example.pfe1.ui.theme.BlueTheme


@Composable
fun QuestionScreen(
    navController: NavController,
    childId: String,
    taskId: String,
    subjectId: String
) {
    val viewModel = viewModel<QuestionViewModel>()

    val state by viewModel.uiState.collectAsState()
    val addState by viewModel.addQuestionUiState.collectAsState()

    var questionPageState by remember { mutableStateOf(0) }

    Scaffold { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = BlueTheme.darkBlue)
                .padding(paddingValues)
                .padding(20.dp)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            }
            // is Failure
            if (state.isFailure) {
            }

            // is Success
            state.questions.getOrNull(questionPageState)?.let { question ->
                Column(modifier = Modifier.weight(3f)) {
                    when (question) {
                        is Question.TrueFalse -> {
                            TrueFalseQuestions(question)

                            // Do something with TrueFalse question data
                        }

                        is Question.MultipleChoice -> {
                            MultipleChoiceQuestion(question)


                        }

                        is Question.Unknown -> {
                            val id = question.id
                            val taskId = question.taskId
                            val text = question.text

                        }
                    }
                }
            }

            Button(onClick = {
                if (questionPageState < state.questions.size - 1) {
                    questionPageState += 1
                } else {
                    navController.popBackStack(Screen.Tasks.route, false)
                    // navController.navigate(Screen.Tasks.route + "?childId=${childId}&&subjectId=${subjectId}")
                }
            }) {
                Text(text = if (questionPageState == state.questions.size - 1) "Finish" else "Next")
            }
        }
    }
}


