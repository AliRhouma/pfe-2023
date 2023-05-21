package com.example.pfe1.subjects.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.enumClass.Subjects
import com.example.pfe1.subjects.domain.Subject
import com.example.pfe1.ui.theme.BlueTheme

@Composable
fun SubjectsScreen(
    navController: NavController,
    childId : String,
) {
    val viewModel = viewModel<SubjectsViewModel>()

    val state by viewModel.uiState.collectAsState()

    Scaffold (

    ){ paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .background(BlueTheme.darkBlue)
        ) {

            if (state.isLoading) {
                item {
                    CircularProgressIndicator()
                }
            }
            else if (state.isFailure) {
                item {
                    Text(text = "Error")
                }
            }
            else {

                state.subjects.forEachIndexed { index , subject ->
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                ) {
                                    SubjectCard(navController, subject, childId, subject.id)
                                }
                            }
                        }
                    item {
                        Spacer(modifier = Modifier.width(6.dp))
                    }
                    }

                }

item { Text(text = "")  }
            item {
                 //Button(onClick = { viewModel.onEvent(SubjectsEvent.GetSubjectsBySchoolYear(childSchoolYear)) }) {}
                 //Button(onClick = { viewModel.onEvent(SubjectsEvent.AddSubject("Math 3eme",SchoolYear.GRADE_2, type = Subjects.ENGLISH)) }) {}
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
import androidx.compose.material3.*
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