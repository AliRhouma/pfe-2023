package com.example.pfe1.vocabulary.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pfe1.navigation.Screen
import com.example.pfe1.questions.domain.model.Question
import com.example.pfe1.ui.theme.BrandColor
import com.example.pfe1.ui.theme.introRustBase
import com.example.pfe1.vocabulary.domain.Vocabulary
import org.checkerframework.common.subtyping.qual.Bottom
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun VocabularyTasksScreen(navController: NavController) {
    val viewModel = viewModel<VocabularyTasksViewModel>()
    val taskUiState by viewModel.vocabularyUiState.collectAsState()
    var index by remember { mutableStateOf(1) }

    var fruit = listOf(
        "https://static.vecteezy.com/system/resources/previews/011/997/978/original/cute-and-smile-cartoon-fruit-colorful-character-watermelon-free-png.png",
        "https://clipart-library.com/images/dT9Kjoe6c.png",
        "https://static.vecteezy.com/system/resources/previews/008/506/558/original/kiwi-fruit-split-cartoon-png.png",
        "https://pngfre.com/wp-content/uploads/orange-64-294x300.png"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BrandColor.violet)
    ) {
        Box(modifier = Modifier.weight(2f)) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp), contentAlignment = Alignment.TopCenter
            ) {
                AsyncImage(
                    model = "https://static.vecteezy.com/system/resources/previews/011/997/978/original/cute-and-smile-cartoon-fruit-colorful-character-watermelon-free-png.png",
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 0.dp)
                        .size(170.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp, 0.dp, 20.dp, 20.dp),
                    contentAlignment = Alignment.BottomCenter


                ) {
                    Text(
                        text = "Fruit and \n\nVegetables",
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        fontFamily = introRustBase,
                        fontSize = 34.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center

                    )
                }
            }

        }
        Column(
            modifier = Modifier.weight(3f).clip(RoundedCornerShape(7)),
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .weight(4F)
                    .fillMaxSize()
                    .padding(20.dp, 20.dp, 20.dp, 10.dp,)
                    .clip(RoundedCornerShape(7))
                    .background(Color.White), contentAlignment = Alignment.Center
            ) {
                when (index) {
                    1 -> QuizNames(
                        url = "https://static.vecteezy.com/system/resources/previews/008/506/558/original/kiwi-fruit-split-cartoon-png.png",
                        names = listOf<String>("Orange", "Kiwi", "Apple"),
                        correctAnswer = "Kiwi"
                    )

                    2 -> QuizNames(
                        url = "https://clipart-library.com/images/dT9Kjoe6c.png",
                        names = listOf<String>("Kiwi", "Ornge", "Apple"),
                        correctAnswer = "Apple"
                    )

                    3 -> QuizNames(
                        url = "https://static.vecteezy.com/system/resources/previews/008/506/558/original/kiwi-fruit-split-cartoon-png.png",
                        names = listOf<String>("Apple", "Kiwi", "Orange"),
                        correctAnswer = "Kiwi"
                    )
                }


                when (taskUiState.taskType) {
                    is Question.QuizVocabulary -> {}
                    is Question.QuizOfIcons -> {}
                    is Question.SpealingBeginner -> {}
                    else -> {}
                }
                QuizNames(
                    url = "https://pngfre.com/wp-content/uploads/orange-64-294x300.png",
                    names = listOf<String>("Orange", "Kiwi", "Apple"),
                    correctAnswer = "Orange"
                )
            }
            Box(
                modifier = Modifier
                    .weight(1F)
                    .fillMaxSize()
                    .padding(90.dp, 20.dp, 90.dp, 10.dp,)
                    .clip(RoundedCornerShape(7))
                    .clickable {
                        index = +1
                        if (index == 4) navController.navigate(route = Screen.ClassScreen.route)
                    }
                    .background(BrandColor.Green), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (index < 4) "Next" else "Finish",
                    modifier = Modifier.padding(10.dp),
                    fontFamily = introRustBase,
                    color = Color.White,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center

                )


            }

        }
    }
}

