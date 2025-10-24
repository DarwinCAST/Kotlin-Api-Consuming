package com.edteam.webserviceandroidapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.edteam.webserviceandroidapp.ui.theme.Background
import kotlin.math.round

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    style: TextStyle,
    icon: ImageVector
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(text = title, style = style)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Background,
            titleContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = icon, contentDescription = "Icon Navigation", tint = Color.White,
                )
            }
        }
    )
}

@Composable
fun CircleWithLetter(modifier: Modifier = Modifier, letter: String) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .background(color = Background, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(text = letter, color = Color.White, fontSize = 40.sp)
    }
}

@Composable
fun AlertDialog(
    modifier: Modifier = Modifier,
    showDialog: Boolean,
    onCancel: () -> Unit = {},
    onDelete: () -> Unit = {}
) {
    if (showDialog) {
        Dialog(onDismissRequest = {  }) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Confirmacion",
                    textAlign = TextAlign.Center,
                    modifier = modifier.fillMaxWidth(),
                )
                Spacer(modifier = modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = onDelete) {
                        Text(text = "Eliminar")
                    }
                    Spacer(modifier = modifier.width(16.dp))

                    Button(onClick = onCancel) {
                        Text(text = "Cancelar")
                    }
                }
            }
        }
    }
}