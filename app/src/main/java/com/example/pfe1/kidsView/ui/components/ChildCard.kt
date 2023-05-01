package com.example.pfe1.kidsView.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.pfe1.R
import com.example.pfe1.kidsView.ui.ChildEvents
import com.example.pfe1.kidsView.ui.ChildViewModel

@Composable
fun ChildCard(childName : String,  childSchoolYear: String, imageUrl: String, id: String) {

    val viewModel = viewModel<ChildViewModel>()


    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
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
                    style = MaterialTheme.typography.h6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "School year: $childSchoolYear",
                    style = MaterialTheme.typography.body2
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
                    }



            )
        }
    }

}