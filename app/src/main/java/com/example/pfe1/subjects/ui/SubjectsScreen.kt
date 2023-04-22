package com.example.pfe1.subjects.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pfe1.subjects.domain.Subject

@Composable
fun SubjectsScreen() {
    val viewModel = viewModel<SubjectsViewModel>()
    val state by viewModel.uiState.collectAsState()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            }
            else if (state.isFailure) {
                Text(text = "Error")
            }
            else {
                state.subjects.forEach { subject ->
                    Text(text = "Subject: ${subject.name}")
                }
            }

            Button(onClick = { viewModel.onEvent(SubjectsEvent.GetSubjects) }) {

            }
        }
    }
}

/*@Composable
fun HiHHH(
    event1: () -> Unit,
    event2: () -> Unit,
    event3: () -> Unit,
    event4: () -> Unit,
) {



}*/

/*package com.example.pfe1.subjects.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.pfe1.R
import javax.security.auth.Subject
@Composable
fun SubjectsScreen(){
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Subjects") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { SubjectsEvent.addEvent() }) {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),"this is add icon")
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                repeat(10) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = 4.dp,
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(text = "Card $it")
                            Text(text = "This is card $it description.")
                        }
                    }
                }
            }
        }
    )
}
*/