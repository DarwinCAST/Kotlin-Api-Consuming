package com.edteam.webserviceandroidapp.presentation.list

import android.text.style.IconMarginSpan
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edteam.webserviceandroidapp.presentation.common.TopAppBar
import com.edteam.webserviceandroidapp.R
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.edteam.webserviceandroidapp.data.Developer
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import com.edteam.webserviceandroidapp.presentation.common.AlertDialog
import com.edteam.webserviceandroidapp.presentation.common.CircleWithLetter


@Composable
fun DirectoryScreen(
    modifier: Modifier = Modifier,
    directoryViewModel: DirectoryViewModel = viewModel(),
    onNavigate: (Int?) -> Unit
    ) {
    val context = LocalContext.current
    val state = directoryViewModel.state.value;
    val showDialog = remember { mutableStateOf(false) }
    val developerId = remember { mutableStateOf(0) }

    LaunchedEffect(true) {
        directoryViewModel.getDirectory()
    }

    if (showDialog.value) {
        AlertDialog(showDialog = true, onDelete = {
            directoryViewModel.deleteDirectory(developerId.value)
            showDialog.value = false
        })
    }

    if (state.deleteSuccess != null) {
        Toast.makeText(context, state.deleteSuccess, Toast.LENGTH_SHORT).show()
        directoryViewModel.deleteClearState()
        directoryViewModel.getDirectory()
    }

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(
            title = "Directorio", style = TextStyle(
                fontSize = 20.sp
            ), icon = ImageVector.vectorResource(id = R.drawable.ic_hogar)
        )

    }) { paddingValues ->

        if (state.error != null) {
            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
        }

        if (state.isLoading) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            state.directory?.let { developers ->
                items(developers) { developer ->
                    ItemDeveloper(developer = developer, deleteDeveloper = { id ->
                        developerId.value = id
                        showDialog.value = true
                    }, editDeveloper = {
                        onNavigate(it)
                    })
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, bottom = 26.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            FloatingActionButton(onClick = { onNavigate(null)}) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                    contentDescription = "add item",
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    }
}

@Composable
fun ItemDeveloper(
    modifier: Modifier = Modifier,
    developer: Developer,
    deleteDeveloper: (Int) -> Unit,
    editDeveloper: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Color.Gray, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Box(
                modifier = modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                CircleWithLetter(letter = "${developer.names[0]}${developer.lastName[0]}")
            }

            Column(
                modifier = modifier
                    .fillMaxHeight()
                    .weight(3f)
            ) {
                Text(
                    text = "${developer.names} ${developer.lastName}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text(
                    text = developer.specialty,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.padding(vertical = 5.dp))
                Row {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete),
                        contentDescription = "delete",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                deleteDeveloper(developer.id)
                            }
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_edit),
                        contentDescription = "edit",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                editDeveloper(developer.id)
                            }
                    )
                }
            }
        }
    }
}