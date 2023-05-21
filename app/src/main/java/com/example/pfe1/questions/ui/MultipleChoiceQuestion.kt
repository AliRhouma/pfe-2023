package com.example.pfe1.questions.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pfe1.questions.domain.model.Question
import com.example.pfe1.ui.theme.BlueTheme

@Composable
fun MultipleChoiceQuestion(question: Question.MultipleChoice) {
    var selectedAnswer by remember { mutableStateOf(-1) }
    Card(
        modifier = Modifier
            .background(color = BlueTheme.darkBlue)
            .padding(16.dp)
            .fillMaxSize(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = RoundedCornerShape(16.dp),


        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color(0xFFBED9E4))

        ){
            Text(
                text = question.text,
                modifier = Modifier.padding(20.dp),
                textAlign = TextAlign.Center,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
            Row(modifier = Modifier.padding(16.dp)) {
                question.answers.forEachIndexed { index, answer ->
                    Column(
                        modifier = Modifier
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        RadioButton(
                            selected = selectedAnswer == index,
                            onClick = {
                                selectedAnswer = index
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(0xFF1984A7),
                                unselectedColor = Color(0xFFA7A7A7)
                            ),
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = answer,
                            color = Color.Black,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}