package com.example.pfe1.questions.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun TrueFalseQuestions(question: Question.TrueFalse) {
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
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                        .border(3.dp, BlueTheme.darkBlue, RoundedCornerShape(3.dp))
                        .fillMaxWidth(),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = BlueTheme.lightBlue,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "True")
                }
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                        .fillMaxWidth()
                        .border(3.dp, BlueTheme.darkBlue, RoundedCornerShape(3.dp)),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = BlueTheme.lightBlue,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(text = "False")
                }
            }
        }
    }
}