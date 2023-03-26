package com.example.composeexercise2

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composeexercise2.screens.DetailScreen
import com.example.composeexercise2.screens.FavoriteScreen
import com.example.composeexercise2.screens.HomeScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "homescreen"
    ) {
        composable("homescreen") { HomeScreen(navController = navController) }
        composable(
            "detailscreen/{movieId}",   // route with navArgument
            // optional: define Type of argument
            arguments = listOf(navArgument("movieId") {
                type = NavType.StringType
            })
        ) {
            backStackEntry ->
            DetailScreen(navController = navController,
                movieId = backStackEntry.arguments?.getString("movieId"))
        }
        composable("favscreen") { FavoriteScreen(navController = navController) }
    }

}