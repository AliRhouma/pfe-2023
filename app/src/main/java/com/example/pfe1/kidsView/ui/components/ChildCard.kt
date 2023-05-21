package com.example.pfe1.kidsView.ui.components

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.*
import coil.compose.AsyncImage
import com.example.pfe1.R
import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.kidsView.ui.ChildEvents
import com.example.pfe1.kidsView.ui.ChildViewModel
import com.example.pfe1.navigation.Screen


@Composable
fun ChildCard(childName : String,  childSchoolYear: SchoolYear, imageUrl: String, id: String, navController: NavController,) {

    val viewModel = viewModel<ChildViewModel>()

    val bundle = Bundle().apply {
        putString("id", id)
    }

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable { navController.navigate(Screen.ChildHome.route + "?childId=${id}")}
            .height(height = 120.dp),

    ) {
        Row(
            modifier = Modifier
                .background(color = Color(0xFFBED9E4))
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 0.dp)
                    .size(110.dp)
                    .clip(CircleShape)
                    .weight(3f),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .padding(top = 6.dp)
                    .weight(6f)
            ) {
                Text(
                    text = childName,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "School year: ${childSchoolYear.ordinal}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.delete),
                contentDescription = null,
                tint = Color(0xFF1984A7),
                modifier = Modifier
                    .weight(1f)
                    .size(40.dp)
                    .clickable {
                        viewModel.onEvent(ChildEvents.Delete(id))
                    },

            )
        }
    }

}