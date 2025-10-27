package com.edteam.webserviceandroidapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.edteam.webserviceandroidapp.presentation.list.DirectoryScreen
import com.edteam.webserviceandroidapp.presentation.register.RegisterDeveloperScreen

@Composable
fun SetUpNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "directory_screen") {

        composable(route = "directory_screen") {
            DirectoryScreen(
                onNavigate = { id ->
                    navController.navigate("register_developer_screen/?id=$id")
                }
            )
        }

        composable(
            route = "register_developer_screen/?id={id}",
            arguments = listOf(navArgument("id"){defaultValue = -1})
        ) {
            val id = it.arguments?.getInt("id")
            RegisterDeveloperScreen(onNavigate = {
                navController.popBackStack()
            }, id = id ?: -1)
        }
    }
}