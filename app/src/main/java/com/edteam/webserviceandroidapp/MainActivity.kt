package com.edteam.webserviceandroidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.edteam.webserviceandroidapp.navigation.SetUpNavigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            SetUpNavigation()

//            LaunchedEffect(Unit) {
//                GlobalScope.launch {
//                    val response = withContext(Dispatchers.IO) {
//                        Api.api.getPokemon()
//                    }
//
//                    if (response.isSuccessful) {
//                        val body = response.body()
//                    }
//                }
//            }

        }
    }
}
