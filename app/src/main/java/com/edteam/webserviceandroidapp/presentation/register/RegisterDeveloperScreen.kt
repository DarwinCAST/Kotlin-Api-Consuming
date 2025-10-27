package com.edteam.webserviceandroidapp.presentation.register

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edteam.webserviceandroidapp.R
import com.edteam.webserviceandroidapp.data.Developer
import com.edteam.webserviceandroidapp.data.DeveloperRequest
import com.edteam.webserviceandroidapp.presentation.common.TopAppBar
import com.edteam.webserviceandroidapp.ui.theme.Background

@Composable
fun RegisterDeveloperScreen(
    modifier: Modifier = Modifier, onNavigate: () -> Unit,
    registerDeveloperViewModel: RegisterDeveloperViewModel = viewModel(),
    id: Int
) {

    val state = registerDeveloperViewModel.state.value
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var specialty by remember { mutableStateOf(Specialty.getAllSpecialty()[0]) }

    val title = if(id != -1 ) "Editar" else "Registrar"

    LaunchedEffect(Unit) {
        if(id != -1){
            registerDeveloperViewModel.getDeveloperById(id)
        }
    }

    LaunchedEffect(state.data) {
        state.data?.let{
            name = it.names
            lastName = it.lastName
            specialty = it.specialty
        }
    }

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    if (state.error != null) {
        Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
    }

    if (state.successful != null ) {
        Toast.makeText(
            context,
            "Developer: ${state.successful.names} Registrado correctamente",
            Toast.LENGTH_SHORT
        ).show()
        onNavigate()
    }

    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        TopAppBar(
            onClickIcon = onNavigate,
            title = title, style = TextStyle(
                fontSize = 20.sp
            ), icon = ImageVector.vectorResource(id = R.drawable.ic_arrow_back)
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = {
                    name = it
                },
                label = { Text(text = "Nombres") },
                shape = RoundedCornerShape(6.dp),
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                ),
                colors = TextFieldDefaults.colors(
                    focusedLabelColor = Background,
                    cursorColor = Background,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Background
                )
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text(text = "Apellidos") },
                shape = RoundedCornerShape(6.dp),
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                ),
                colors = TextFieldDefaults.colors(
                    focusedLabelColor = Background,
                    cursorColor = Background,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Background
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Specialty.getAllSpecialty().forEach {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = it == specialty,
                            onClick = {
                                specialty = it
                            }
                        )
                        Text(text = it)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if(id!= -1){
                        registerDeveloperViewModel.updateDeveloper(
                            DeveloperRequest(
                                names = name,
                                lastName = lastName,
                                specialty = specialty,
                                id = id
                            )
                        )
                    } else{
                        registerDeveloperViewModel.createDeveloper(
                            DeveloperRequest(
                                names = name,
                                lastName = lastName,
                                specialty = specialty
                            )
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Background,
                    contentColor = Color.White,
                ),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = title,
                    fontSize = 15.sp
                )
            }
        }

    }
}