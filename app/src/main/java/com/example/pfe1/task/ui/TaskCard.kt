package com.example.pfe1.task.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pfe1.navigation.Screen
import com.example.pfe1.task.domain.model.Task

@Composable
fun TaskCard(navController: NavController, task: Task, childId: String, subjectId: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(6.dp)
            .clickable { navController.navigate(Screen.Question.route + "?childId=${childId}&&taskId=${task.id}&&subjectId=${subjectId}") }
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFBED9E4))
            .fillMaxWidth()
            .height(height = 190.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color(0xFFBED9E4)),

            ) {
            Text(
                text = task.name,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1984A7) // Or any other color you prefer
                ),
                //fontFamily = FontFamily.Cursive, // Or any other font you prefer
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .padding(8.dp)

            )
            Text(text = "4 tasks")

            Spacer(modifier = Modifier.height(8.dp))


        }
    }
}
