package com.edteam.webserviceandroidapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edteam.webserviceandroidapp.presentation.list.DirectoryScreen
import com.edteam.webserviceandroidapp.presentation.register.RegisterDeveloperScreen

@Composable
fun SetUpNavigation(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "directory_screen"){

        composable(route = "directory_screen"){
            DirectoryScreen()
        }

        composable(route = "register_developer_screen"){
            RegisterDeveloperScreen()
        }
    }
}