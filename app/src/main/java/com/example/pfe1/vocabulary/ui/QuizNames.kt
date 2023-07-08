package com.example.pfe1.vocabulary.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pfe1.ui.theme.BrandColor
import com.example.pfe1.ui.theme.introRustBase

@Composable
fun QuizNames(url: String,names : List<String>, correctAnswer: String){
    var correct by remember { mutableStateOf(false) }
    var incorrect by remember { mutableStateOf(false) }
    var still by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxSize().border(
        if(still) 0.dp else 10.dp,
        if (correct) BrandColor.Green else Color.Red,
        RoundedCornerShape(7.dp)
    ), horizontalAlignment = Alignment.CenterHorizontally) {



    Box(modifier = Modifier
        .weight(3f)
        .padding(10.dp), contentAlignment = Alignment.TopCenter){
        AsyncImage(
            model = url,
            contentDescription = null,
            modifier = Modifier
                .padding(0.dp, 40.dp, 0.dp, 0.dp,)
                .size(150.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )}
        Box(modifier = Modifier
            .weight(1f)
            .padding(10.dp), contentAlignment = Alignment.Center) {

        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
            for (i in names){
            Box(
                modifier= Modifier
                    .padding(10.dp)
                    .size(90.dp)
                    .background(
                         Color.White
                    )
                    .clip(RoundedCornerShape(7) )
                    .border(2.dp, BrandColor.Gray, RoundedCornerShape(7))
                    .clickable { correct = i==correctAnswer
                    incorrect = !correct
                        still = false
                    },
                contentAlignment = Alignment.Center

            ) {
                Text(
                    text = i,
                    fontFamily = introRustBase,
                    fontSize = 20.sp

                )
            }

            }
        }
        }
    }
}