package com.example.pfe1.childHome.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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

@Composable
fun ChildHomeCard(Text: String, IconUrl: String, route: String, navController: NavController ) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(6.dp)
            .clickable { navController.navigate(route) }
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFBED9E4))
            .fillMaxWidth()
            .height(height = 490.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color(0xFFBED9E4)),

        ) {
            Text(
                text = Text,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1984A7) // Or any other color you prefer
                ),
                //fontFamily = FontFamily.Cursive, // Or any other font you prefer
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .padding(8.dp)

            )

            Spacer(modifier = Modifier.height(8.dp))


        }
    }
}

