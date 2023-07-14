package com.example.pfe1.kidsView.ui.components

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.*
import coil.compose.AsyncImage
import com.example.pfe1.R
import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.kidsView.domain.model.Child
import com.example.pfe1.kidsView.ui.ChildEvents
import com.example.pfe1.kidsView.ui.ChildViewModel
import com.example.pfe1.navigation.Screen
import com.example.pfe1.ui.theme.BrandColor
import com.example.pfe1.ui.theme.arialUniCode
import com.example.pfe1.ui.theme.introRustBase


@Composable
fun ChildCard(child: Child, navController: NavController,) {

    val viewModel = viewModel<ChildViewModel>()
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(5.dp)
            .clip(RoundedCornerShape(20))
            .background(BrandColor.orange)
            .clickable { navController.navigate(Screen.ChildHome.route + "?childId=${child.id}") },
        verticalAlignment = Alignment.CenterVertically

    ){
        AsyncImage(
            model = child.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .padding(start = 5.dp, end = 12.dp)
                .size(70.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = child.name,
                modifier = Modifier
                    .padding(bottom = 3.dp),
             //   textAlign = TextAlign.Center,
                fontFamily = introRustBase,
                fontSize = 20.sp,
                color = Color.Black
            )
            Text(
                text = child.schoolYear.toString(),
                modifier = Modifier
                    .padding(bottom = 0.dp),
               // textAlign = TextAlign.Center,
                fontFamily = arialUniCode,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                color = Color.Black
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 5.dp),
            contentAlignment = Alignment.CenterEnd

        ) {
            Text(
                text = "3 tasks" , //todo ( add task number attribute to the child data class )
                modifier = Modifier
                    .padding(bottom = 0.dp),
                // textAlign = TextAlign.Center,
                fontFamily = arialUniCode,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                color = Color.Black
            )
        }

    }


}